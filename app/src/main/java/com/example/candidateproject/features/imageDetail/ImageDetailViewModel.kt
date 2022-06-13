package com.example.candidateproject.features.imageDetail

import androidx.lifecycle.ViewModel
import com.example.candidateproject.services.responds.ImageListRespond

class ImageDetailViewModel : ViewModel() {

    fun getImageUrlMode(data: ImageListRespond, mode: String): String{
        return if (mode.isNotEmpty()){
            "${data?.download_url}/?${mode}"
        }else{
            data?.download_url
        }
    }
}