package com.sparkfusion.data.pupil.repository

import android.util.Log
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.account.ReadPupilAccountDataEntityMapper
import com.sparkfusion.data.pupil.source.AccountApiService
import com.sparkfusion.dataport.pupil.portaccount.IAccountRepository
import com.sparkfusion.dataport.pupil.portaccount.entity.ReadPupilAccountEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AccountRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val accountApiService: AccountApiService,
    private val readPupilAccountDataEntityMapper: ReadPupilAccountDataEntityMapper
): IAccountRepository {

    override suspend fun readPupilAccount(): Answer<ReadPupilAccountEntity?> = safeApiCall(ioDispatcher) {
        Log.d("TAGTAG", ApiResponseHandler(accountApiService.readPupilAccount())
            .handleFetchedData().unwrap().toString())
        ApiResponseHandler(accountApiService.readPupilAccount())
            .handleFetchedData()
            .suspendMap { it?.let { m -> readPupilAccountDataEntityMapper.map(m) } }
    }
}
























