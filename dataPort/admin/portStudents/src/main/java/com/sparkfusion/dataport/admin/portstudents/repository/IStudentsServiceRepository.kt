package com.sparkfusion.dataport.admin.portstudents.repository

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.dataport.admin.portstudents.entity.CreateGroupEntity
import com.sparkfusion.dataport.admin.portstudents.entity.FacultyEntity
import com.sparkfusion.dataport.admin.portstudents.entity.ReadGroupMemberEntity
import com.sparkfusion.dataport.admin.portstudents.entity.ReadGroupWithMembersEntity
import com.sparkfusion.dataport.admin.portstudents.entity.SpecialityEntity
import com.sparkfusion.dataport.admin.portstudents.entity.UpdateGroupEntity

interface IStudentsServiceRepository {

    suspend fun readFaculties(): Answer<List<FacultyEntity>>
    suspend fun readSpecialitiesByFaculty(facultyId: Int): Answer<List<SpecialityEntity>>

    suspend fun readGroupMembers(groupId: Int): Answer<List<ReadGroupMemberEntity>>
    suspend fun readGroupMemberById(id: Int): Answer<ReadGroupMemberEntity>
    suspend fun makeStudentAPrefect(id: Int): Answer<Unit>

    suspend fun readGroupBySpeciality(specialityId: Int): Answer<List<CommonGroupDataEntity>>
    suspend fun readGroupWithMembersById(id: Int): Answer<ReadGroupWithMembersEntity>
    suspend fun createGroup(createInstitutionGroupDto: CreateGroupEntity): Answer<Unit>
    suspend fun updateGroup(updateInstitutionGroupDto: UpdateGroupEntity): Answer<Unit>
    suspend fun deleteGroupById(id: Int): Answer<Unit>
}




























