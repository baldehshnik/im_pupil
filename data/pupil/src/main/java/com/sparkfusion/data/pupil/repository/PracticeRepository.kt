package com.sparkfusion.data.pupil.repository

import android.util.Log
import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.practice.PracticeInfoDataEntityMapper
import com.sparkfusion.data.pupil.mapper.practice.PracticeListDataEntityMapper
import com.sparkfusion.data.pupil.source.PracticeApiService
import com.sparkfusion.dataport.pupil.portpractice.IPracticeRepository
import com.sparkfusion.dataport.pupil.portpractice.PracticeInfoEntity
import com.sparkfusion.dataport.pupil.portpractice.PracticeListEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PracticeRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val practiceApiService: PracticeApiService,
    private val practiceListDataEntityMapper: PracticeListDataEntityMapper,
    private val practiceInfoDataEntityMapper: PracticeInfoDataEntityMapper
) : IPracticeRepository {

    override suspend fun readPractices(): Answer<List<PracticeListEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(practiceApiService.readPractices())
                .handleFetchedData()
                .suspendMap { list ->
                    list.map {
                        practiceListDataEntityMapper.map(it)
                    }
                }
        }

    override suspend fun readPracticeById(id: Int): Answer<PracticeInfoEntity> =
        safeApiCall(ioDispatcher) {
            Log.d("TAGTAG", "NEW ID - $id")
            ApiResponseHandler(practiceApiService.readPracticeById(id))
                .handleFetchedData()
                .suspendMap {
                    practiceInfoDataEntityMapper.map(it)
                }
        }
}




























