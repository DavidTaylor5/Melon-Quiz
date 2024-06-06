package com.example.melonquiz.Model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MelonQuestion(@StringRes val melonName: Int, @DrawableRes val melonImage: Int)