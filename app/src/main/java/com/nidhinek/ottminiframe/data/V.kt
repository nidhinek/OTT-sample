package com.example.example

import com.google.gson.annotations.SerializedName


data class V (

    @SerializedName("i"  ) var i  : ImageInfo?      = ImageInfo(),
    @SerializedName("id" ) var id : String? = null,
    @SerializedName("l"  ) var l  : String? = null,
    @SerializedName("s"  ) var s  : String? = null

)
