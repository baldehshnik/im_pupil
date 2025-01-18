package com.sparkfusion.domain.common.news.usecase

import android.util.Log
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.common.portnews.INewsRepository
import com.sparkfusion.domain.common.news.mapper.NewsInfoEntityMapper
import com.sparkfusion.domain.common.portnews.IReadNewsInfoUseCase
import com.sparkfusion.domain.common.portnews.NewsInfoModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ReadNewsInfoUseCase @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val newsRepository: INewsRepository,
    private val newsInfoEntityMapper: NewsInfoEntityMapper
) : IReadNewsInfoUseCase {

    override suspend fun readNewsInfoById(id: Int): Answer<NewsInfoModel> =
        withContext(ioDispatcher) {
            newsRepository.loadNewsById(id)
                .suspendMap {
                    Log.d("TAGTAG", "use case --- $it")
                    newsInfoEntityMapper.map(it)
                }
        }
}


















