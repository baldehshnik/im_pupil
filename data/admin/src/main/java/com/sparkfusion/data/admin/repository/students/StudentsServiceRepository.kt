package com.sparkfusion.data.admin.repository.students

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.GroupApiService
import com.sparkfusion.data.admin.source.GroupMemberApiService
import com.sparkfusion.data.admin.source.InstitutionApiService
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.dataport.admin.portstudents.entity.CreateGroupEntity
import com.sparkfusion.dataport.admin.portstudents.entity.FacultyEntity
import com.sparkfusion.dataport.admin.portstudents.entity.ReadGroupMemberEntity
import com.sparkfusion.dataport.admin.portstudents.entity.ReadGroupWithMembersEntity
import com.sparkfusion.dataport.admin.portstudents.entity.SpecialityEntity
import com.sparkfusion.dataport.admin.portstudents.entity.UpdateGroupEntity
import com.sparkfusion.dataport.admin.portstudents.repository.IStudentsServiceRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentsServiceRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val institutionApiService: InstitutionApiService,
    private val groupMemberApiService: GroupMemberApiService,
    private val groupApiService: GroupApiService
) : IStudentsServiceRepository {

    override suspend fun readFaculties(): Answer<List<FacultyEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(institutionApiService.readFaculties())
            .handleFetchedData()
    }

    override suspend fun readSpecialitiesByFaculty(facultyId: Int): Answer<List<SpecialityEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(institutionApiService.readSpecialitiesByFaculty(facultyId))
                .handleFetchedData()
        }

    override suspend fun readGroupMembers(groupId: Int): Answer<List<ReadGroupMemberEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(groupMemberApiService.readGroupMembers(groupId))
                .handleFetchedData()
        }

    override suspend fun readGroupMemberById(id: Int): Answer<ReadGroupMemberEntity> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(groupMemberApiService.readGroupMemberById(id))
                .handleFetchedData()
        }

    override suspend fun makeStudentAPrefect(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(groupMemberApiService.makeStudentAPrefect(id))
            .handleFetchedData()
    }

    override suspend fun readGroupBySpeciality(specialityId: Int): Answer<List<CommonGroupDataEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(groupApiService.readGroupBySpeciality(specialityId))
            .handleFetchedData()
    }

    override suspend fun readGroupWithMembersById(id: Int): Answer<ReadGroupWithMembersEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(groupApiService.readGroupWithMembersById(id))
            .handleFetchedData()
    }

    override suspend fun createGroup(createInstitutionGroupDto: CreateGroupEntity): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(groupApiService.createGroup(createInstitutionGroupDto))
            .handleFetchedData()
    }

    override suspend fun updateGroup(updateInstitutionGroupDto: UpdateGroupEntity): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(groupApiService.updateGroup(updateInstitutionGroupDto))
            .handleFetchedData()
    }

    override suspend fun deleteGroupById(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(groupApiService.deleteGroupById(id))
            .handleFetchedData()
    }
}
























