package com.example.candidateproject.services.request

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
class ImageListRequest :PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelImageListRequest.CREATOR
    }
    var page: Int = 0
    var limit: Int = 0
}