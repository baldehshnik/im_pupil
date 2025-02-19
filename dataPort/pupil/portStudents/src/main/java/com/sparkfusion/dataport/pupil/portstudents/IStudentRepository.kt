package com.sparkfusion.dataport.pupil.portstudents

import com.sparkfusion.core.common.result.Answer

interface IStudentRepository {

    suspend fun readGroupMembers(): Answer<List<GroupMemberEntity>>

    suspend fun readGroupMemberById(id: Int): Answer<GroupMemberEntity>
}
















