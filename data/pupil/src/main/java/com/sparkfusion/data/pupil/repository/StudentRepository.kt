package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.students.GroupMemberDataEntityMapper
import com.sparkfusion.data.pupil.source.GroupMemberApiService
import com.sparkfusion.dataport.pupil.portstudents.GroupMemberEntity
import com.sparkfusion.dataport.pupil.portstudents.IStudentRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class StudentRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val groupMemberApiService: GroupMemberApiService,
    private val groupMemberDataEntityMapper: GroupMemberDataEntityMapper
) : IStudentRepository {

    override suspend fun readGroupMembers(): Answer<List<GroupMemberEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(groupMemberApiService.readGroupMembers())
                .handleFetchedData()
                .suspendMap { list ->
                    list.map {
                        groupMemberDataEntityMapper.map(it)
                    }
                }
        }

    override suspend fun readGroupMemberById(id: Int): Answer<GroupMemberEntity> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(groupMemberApiService.readGroupMemberById(id))
                .handleFetchedData()
                .suspendMap { groupMemberDataEntityMapper.map(it) }
        }
}




















