package com.sparkfusion.data.admin.repository.exam

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.ExamApiService
import com.sparkfusion.data.admin.source.GroupApiService
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.data.commonentity.exam.CommonExamDataEntity
import com.sparkfusion.dataport.admin.portexam.AddExamEntity
import com.sparkfusion.dataport.admin.portexam.DeleteExamsEntity
import com.sparkfusion.dataport.admin.portexam.IExamRepository
import com.sparkfusion.dataport.admin.portexam.UpdateExamEntity
import kotlinx.coroutines.CoroutineDispatcher
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ExamRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val groupApiService: GroupApiService,
    private val examApiService: ExamApiService
): IExamRepository {

    override suspend fun readGroupByNamePart(
        institutionId: Int,
        namePart: String
    ): Answer<List<CommonGroupDataEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(groupApiService.readGroupByNamePart(institutionId, namePart))
            .handleFetchedData()
    }

    override suspend fun readExams(
        groupId: Int,
        date: LocalDate
    ): Answer<List<CommonExamDataEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(examApiService.readExams(groupId, date))
            .handleFetchedData()
    }

    override suspend fun readExamById(id: Int): Answer<CommonExamDataEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(examApiService.readExamById(id))
            .handleFetchedData()
    }

    override suspend fun createExam(exam: AddExamEntity): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(examApiService.createExam(exam))
            .handleFetchedData()
    }

    override suspend fun updateExam(exam: UpdateExamEntity): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(examApiService.updateExam(exam))
            .handleFetchedData()
    }

    override suspend fun deleteExams(examsIds: DeleteExamsEntity): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(examApiService.deleteExams(examsIds))
            .handleFetchedData()
    }
}



























