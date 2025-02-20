package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.magazine.GroupMemberWithPassDataEntity
import com.sparkfusion.data.pupil.entity.magazine.UpdatePassStatusDataEntity
import com.sparkfusion.data.pupil.entity.magazine.UpdatePassesStatusDataEntity
import com.sparkfusion.data.pupil.entity.magazine.WeekDayPassDataEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDate

internal interface MagazineApiService {

    @GET("/education/schedule/lesson/passes/week")
    suspend fun readWeekStatistics(
        @Query("groupMemberId") groupMemberId: Int,
        @Query("date") date: LocalDate
    ): Response<List<WeekDayPassDataEntity>>

    @GET("/pupil/schedule/lesson/passes")
    suspend fun readPasses(
        @Query("lessonId") lessonId: Int,
        @Query("date") date: LocalDate
    ): Response<List<GroupMemberWithPassDataEntity>>

    @GET("/pupil/schedule/lesson/pass")
    suspend fun readPassOfGroupMember(
        @Query("groupMemberId") groupMemberId: Int,
        @Query("lessonId") lessonId: Int,
        @Query("date") date: LocalDate
    ): Response<GroupMemberWithPassDataEntity>

    @POST("/pupil/schedule/lesson/pass/update")
    suspend fun updatePassOfGroupMember(
        @Body updatePassStatus: UpdatePassStatusDataEntity
    ): Response<Unit>

    @POST("/pupil/schedule/lesson/passes/update")
    suspend fun updatePasses(
        @Body updatePassesStatus: UpdatePassesStatusDataEntity
    ): Response<Unit>
}


























