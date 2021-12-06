package com.nidhinek.ottminiframe.data

import com.example.example.DataModel
import com.google.gson.annotations.SerializedName

data class ImdbSearchResponse(
    @SerializedName("d") var data: List<DataModel> = arrayListOf(),
    @SerializedName("q") var query: String? = null,
    @SerializedName("v") var version: Int? = null,
    var railTitle: String? = null
):BaseModel(railTitle)
