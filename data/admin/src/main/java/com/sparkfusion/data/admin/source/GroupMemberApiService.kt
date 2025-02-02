package com.sparkfusion.data.admin.source

import com.sparkfusion.dataport.admin.portstudents.entity.ReadGroupMemberEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupMemberApiService {

    @GET("/education/group/member/all")
    suspend fun readGroupMembersForMagazine(
        @Query("groupId") groupId: Int
    ): Response<List<com.sparkfusion.dataport.admin.portmagazine.entity.ReadGroupMemberEntity>>

    @GET("/education/group/member/all")
    suspend fun readGroupMembersForStatistics(
        @Query("groupId") groupId: Int
    ): Response<List<com.sparkfusion.dataport.admin.portstatistics.entity.ReadGroupMemberEntity>>

    @GET("/education/group/member/all")
    suspend fun readGroupMembers(
        @Query("groupId") groupId: Int
    ): Response<List<ReadGroupMemberEntity>>

    @GET("/education/group/member/{id}")
    suspend fun readGroupMemberById(
        @Path("id") id: Int
    ): Response<ReadGroupMemberEntity>

    @POST("/education/group/member/prefect/{id}")
    suspend fun makeStudentAPrefect(
        @Path("id") id: Int
    ): Response<Unit>
}
























