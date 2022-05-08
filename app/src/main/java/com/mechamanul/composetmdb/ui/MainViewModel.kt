package com.mechamanul.composetmdb.ui

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import com.bumptech.glide.RequestBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val requestBuilder: RequestBuilder<Drawable>) : ViewModel()