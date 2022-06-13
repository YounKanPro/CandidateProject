package com.example.candidateproject.services

import com.example.candidateproject.services.responds.ImageListRespond
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageService {

    @GET("v2/list")
    fun getImageListByPage(@Query("page") page: Int, @Query("limit") limit: Int): Observable<List<ImageListRespond>>

    @GET("id/{id}/200/300")
    fun getImageByUrl(@Path("id") id: Int): Observable<String>

    @GET("id/{id}/200/300?grayscale")
    fun getImageGrayscaleByUrl(@Path("id") id: Int): Observable<String>

    @GET("id/{id}/200/300?blur")
    fun getImageBlurByUrl(@Path("id") id: Int): Observable<String>

}