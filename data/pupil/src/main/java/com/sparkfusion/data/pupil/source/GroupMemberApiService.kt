package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.students.GroupMemberDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupMemberApiService {

    @GET("/pupil/group/member")
    suspend fun readGroupMembers(): Response<List<GroupMemberDataEntity>>

    @GET("/pupil/group/member/{id}")
    suspend fun readGroupMemberById(@Path("id") id: Int): Response<GroupMemberDataEntity>
}



















