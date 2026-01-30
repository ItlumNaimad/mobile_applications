package pbs.edu.project.data

import kotlinx.coroutines.flow.Flow
import pbs.edu.project.model.PhotoEntry

class PhotoRepository(private val photoDao: PhotoDao) {

    val allPhotos: Flow<List<PhotoEntry>> = photoDao.getAllPhotos()

    suspend fun insertPhoto(photo: PhotoEntry) {
        photoDao.insert(photo)
    }

    suspend fun deletePhoto(photo: PhotoEntry) {
        photoDao.delete(photo)
    }

    suspend fun getPhotoById(id: Int): PhotoEntry? {
        return photoDao.getPhotoById(id)
    }
}
