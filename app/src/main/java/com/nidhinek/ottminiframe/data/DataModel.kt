package com.example.example

import com.google.gson.annotations.SerializedName
import com.nidhinek.ottminiframe.data.BaseModel


data class DataModel (
  @SerializedName("i"  ) var image  : ImageInfo?      = ImageInfo(),
  @SerializedName("id" ) var id : String? = null,
  @SerializedName("l"  ) var title  : String? = null,
  @SerializedName("s"  ) var stars  : String? = null,
  @SerializedName("v"  ) var v  : List<V> = arrayListOf(),
  @SerializedName("vt" ) var vt : Int?    = null,
  var railTitle: String? = null
):BaseModel(railTitle)
