package com.example.candidateproject.services.responds

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
class ImageListRespond :PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelImageListRespond.CREATOR
    }
    @SerializedName("id")
    var id: String = ""
    @SerializedName("author")
    var author: String = ""
    @SerializedName("width")
    var width: Int = 0
    @SerializedName("height")
    var height: Int = 0
    @SerializedName("url")
    var url: String = ""
    @SerializedName("download_url")
    var download_url: String = ""
}