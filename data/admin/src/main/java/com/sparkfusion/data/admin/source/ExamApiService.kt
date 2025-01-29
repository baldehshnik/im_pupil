package com.sparkfusion.data.admin.source

import com.sparkfusion.data.admin.entity.ExamDataEntity
import com.sparkfusion.dataport.admin.portexam.AddExamEntity
import com.sparkfusion.dataport.admin.portexam.DeleteExamsEntity
import com.sparkfusion.dataport.admin.portexam.UpdateExamEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

interface ExamApiService {

    @GET("/education/exam/all")
    suspend fun readExams(
        @Query("groupId") groupId: Int,
        @Query("date") date: LocalDate
    ): Response<List<ExamDataEntity>>

    @GET("/education/exam/{id}")
    suspend fun readExamById(
        @Path("id") id: Int
    ): Response<ExamDataEntity>

    @POST("/education/exam")
    suspend fun deleteExams(
        @Body examsIds: DeleteExamsEntity
    ): Response<Unit>

    @POST("/education/exam/create")
    suspend fun createExam(
        @Body exam: AddExamEntity
    ): Response<Unit>

    @POST("/education/exam/update")
    suspend fun updateExam(
        @Body exam: UpdateExamEntity
    ): Response<Unit>
}



























