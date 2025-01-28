package com.sparkfusion.data.admin.source

import com.sparkfusion.dataport.admin.portstudents.entity.CreateGroupEntity
import com.sparkfusion.dataport.admin.portstudents.entity.GroupEntity
import com.sparkfusion.dataport.admin.portstudents.entity.ReadGroupWithMembersEntity
import com.sparkfusion.dataport.admin.portstudents.entity.UpdateGroupEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupApiService {

    @GET("/education/group/all")
    suspend fun readGroupBySpeciality(
        @Query("specialityId") specialityId: Int
    ): Response<List<GroupEntity>>

    @GET("/education/group/{id}")
    suspend fun readGroupWithMembersById(
        @Path("id") id: Int
    ): Response<ReadGroupWithMembersEntity>

    @POST("/education/group/create")
    suspend fun createGroup(
        @Body createInstitutionGroupDto: CreateGroupEntity
    ): Response<Unit>

    @POST("/education/group/update")
    suspend fun updateGroup(
        @Body updateInstitutionGroupDto: UpdateGroupEntity
    ): Response<Unit>

    @DELETE("/education/group/{id}")
    suspend fun deleteGroupById(
        @Path("id") id: Int
    ): Response<Unit>
}
































