package com.nidhinek.ottminiframe.ui

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.example.DataModel
import com.nidhinek.ottminiframe.R
import com.nidhinek.ottminiframe.data.BaseModel
import com.nidhinek.ottminiframe.ui.base.BaseWidget

data class CarousalImageData(
    var model: BaseModel,
    var itemPosition: Int,
    val onClicked: () -> Unit
)

class CarousalImageViewItem(context: Context) : BaseWidget<CarousalImageData>(context = context) {
    private lateinit var imageView: ImageView
    override fun getLayoutId(): Int {
        return R.layout.carousal_image_view
    }

    override fun bindData(data: CarousalImageData, viewPosition: Int) {
        imageView = widgetView.findViewById(R.id.imageView)
        Glide.with(imageView).load((data.model as DataModel).image?.imageUrl).into(imageView)
    }
}
