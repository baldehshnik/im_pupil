package com.sparkfusion.features.common.news.viewmodel

import com.sparkfusion.core.common.viewmodel.DefaultViewModel
import com.sparkfusion.core.common.viewmodel.Intent
import com.sparkfusion.core.common.viewmodel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(

): DefaultViewModel<NewsViewModel.S, NewsViewModel.I>() {

    class S : State
    class I : Intent

    override fun initialState(): S {
        TODO("Not yet implemented")
    }

    override fun handleIntent(intent: I) {
        TODO("Not yet implemented")
    }
}