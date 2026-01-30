package pbs.edu.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pbs.edu.project.data.PhotoRepository
import pbs.edu.project.model.PhotoEntry

class HomeViewModel(private val repository: PhotoRepository) : ViewModel() {

    // 1. & 3. Stan listy wpisów i ładowanie danych.
    // Używamy StateFlow podłączonego do Bazy Danych.
    // "loadEntries" dzieje się tutaj automatycznie i reaktywnie.
    val photos: StateFlow<List<PhotoEntry>> = repository.allPhotos
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // 2. Dodawanie wpisu
    fun addEntry(title: String, uri: String, lat: Double, lng: Double) {
        viewModelScope.launch {
            val newEntry = PhotoEntry(
                uri = uri,
                title = title,
                latitude = lat,
                longitude = lng,
                timestamp = System.currentTimeMillis()
            )
            repository.insertPhoto(newEntry)
        }
    }

    // 4. Usuwanie wpisu
    fun deleteEntry(photo: PhotoEntry) {
        viewModelScope.launch {
            repository.deletePhoto(photo)
        }
    }

    // Factory pattern helper - potrzebny, aby przekazać Repository do konstruktora ViewModelu
    companion object {
        fun provideFactory(repository: PhotoRepository): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(repository)
            }
        }
    }
}
