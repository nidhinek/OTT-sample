package com.example.example

import com.google.gson.annotations.SerializedName


data class ImageInfo (

  @SerializedName("height"   ) var height   : Int?    = null,
  @SerializedName("imageUrl" ) var imageUrl : String? = null,
  @SerializedName("width"    ) var width    : Int?    = null

)
