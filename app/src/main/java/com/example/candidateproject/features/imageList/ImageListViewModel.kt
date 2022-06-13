package com.example.candidateproject.features.imageList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.candidateproject.network.HttpManager
import com.example.candidateproject.services.responds.ImageListRespond
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class ImageListViewModel : ViewModel() {
    var imageListData = MutableLiveData<MutableList<ImageListRespond>>()

    fun getImageList(page: Int) {
        HttpManager().imageService.getImageListByPage(page,30)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { data ->
                imageListData.value = ((imageListData.value ?: listOf()) + data).toMutableList()
            }
    }
}