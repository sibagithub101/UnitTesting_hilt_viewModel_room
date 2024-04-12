package com.example.unittesting_course_1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unittesting_course_1.remote.ImageApiResponseModel
import com.example.unittesting_course_1.remote.repo.ArtRepoInterface
import com.example.unittesting_course_1.roomdb.ArtEntity
import com.example.unittesting_course_1.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(private val repository:ArtRepoInterface) :ViewModel() {

    private val images = MutableLiveData<Resource<ImageApiResponseModel>>()
    val imageList: LiveData<Resource<ImageApiResponseModel>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
     val selectedImageUrl: LiveData<String>
        get() = selectedImage

    val artList = repository.getArt()

    //Art details fragment

    private var insertArtMsg = MutableLiveData<Resource<ArtEntity>>()
    val insertArtMessage: LiveData<Resource<ArtEntity>>
        get() = insertArtMsg

    fun resetInsertArtMsg() {
        insertArtMsg = MutableLiveData<Resource<ArtEntity>>()
    }

    fun setSelectedImage(url: String) {
        selectedImage.postValue(url)
    }

    fun deleteArt(artEntity: ArtEntity) = viewModelScope.launch {
        repository.deleteArt(artEntity)
    }

    fun insertArt(artEntity: ArtEntity) = viewModelScope.launch {
        repository.insertArt(artEntity)
    }

    fun makeArt(name: String, artistName: String, year: String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            insertArtMsg.postValue(Resource.error("Enter name,Artist Name and Year", null))
            return
        }

        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            insertArtMsg.postValue(Resource.error("Year should be number", null))
            return
        }

        val art = ArtEntity(
            name = name,
            artistName = artistName,
            year = yearInt,
            imageUrl = selectedImageUrl.value ?: ""
        )
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))

        fun searchForImage(searchString: String) {
            if (searchString.isEmpty()) {
                return
            }
            images.value = Resource.loading(null)
            viewModelScope.launch {
                val response = repository.searchImage(searchString)
                images.value = response
            }

        }
    }
}