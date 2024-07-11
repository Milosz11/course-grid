package com.example.coursesgrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicId: Int,
    val numClasses: Int,
    @DrawableRes val imageId: Int
)
