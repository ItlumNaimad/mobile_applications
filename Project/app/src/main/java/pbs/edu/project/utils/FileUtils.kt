package pbs.edu.project.utils

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FileUtils {
    private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

    fun createUniqueImageFile(context: Context): File {
        val timeStamp = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())
        val storageDir = context.getExternalFilesDir(null) // Zapis w katalogu prywatnym aplikacji (nie wymaga WRITE_EXTERNAL_STORAGE od API 29)
        
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }
}
