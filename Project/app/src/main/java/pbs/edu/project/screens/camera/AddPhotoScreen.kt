package pbs.edu.project.screens.camera

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pbs.edu.project.utils.FileUtils
import pbs.edu.project.utils.LocationService
import pbs.edu.project.viewmodel.HomeViewModel
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Composable
fun AddPhotoScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    locationService: LocationService
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    // CameraX setup
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val preview = remember { Preview.Builder().build() }
    val imageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Podgląd z kamery
        AndroidView(
            factory = { ctx ->
                PreviewView(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    scaleType = PreviewView.ScaleType.FILL_CENTER
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { previewView ->
                val cameraProvider = cameraProviderFuture.get()
                // Odpinamy wszystko przed ponownym przypięciem
                cameraProvider.unbindAll()
                
                // Łączymy preview z widokiem
                preview.setSurfaceProvider(previewView.surfaceProvider)

                try {
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )
                } catch (exc: Exception) {
                    Log.e("CameraX", "Use case binding failed", exc)
                }
            }
        )

        // 2. Przycisk zrobienia zdjęcia
        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    takePhotoAndSave(
                        context,
                        imageCapture,
                        viewModel,
                        locationService,
                        navController
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
                .size(72.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Default.Done, contentDescription = "Capture")
        }
    }
}

private suspend fun takePhotoAndSave(
    context: Context,
    imageCapture: ImageCapture,
    viewModel: HomeViewModel,
    locationService: LocationService,
    navController: NavController
) {
    // 1. Tworzymy plik
    val photoFile = FileUtils.createUniqueImageFile(context)
    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    // 2. Robimy zdjęcie (suspend wrapper na callback)
    val savedUri = suspendCoroutine<Uri?> { continuation ->
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e("CameraX", "Photo capture failed: ${exc.message}", exc)
                    continuation.resume(null)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    continuation.resume(savedUri)
                }
            }
        )
    }

    if (savedUri != null) {
        // 3. Pobieramy lokalizację
        val location = locationService.getCurrentLocation()
        val lat = location?.latitude ?: 0.0
        val lng = location?.longitude ?: 0.0

        // 4. Zapisujemy w bazie
        viewModel.addEntry(
            title = "New Photo", // Można dodać input tekstowy, tu upraszczamy
            uri = savedUri.toString(),
            lat = lat,
            lng = lng
        )
        
        Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        
        // 5. Wracamy do Home
        navController.popBackStack()
    } else {
        Toast.makeText(context, "Failed to capture photo", Toast.LENGTH_SHORT).show()
    }
}
