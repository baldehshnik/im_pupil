package com.sparkfusion.dataport.admin.portexam

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.data.commonentity.exam.CommonExamDataEntity
import java.time.LocalDate

interface IExamRepository {

    suspend fun readGroupByNamePart(
        institutionId: Int,
        namePart: String
    ): Answer<List<CommonGroupDataEntity>>

    suspend fun readExams(
        groupId: Int,
        date: LocalDate
    ): Answer<List<CommonExamDataEntity>>

    suspend fun readExamById(id: Int): Answer<CommonExamDataEntity>
    suspend fun deleteExams(examsIds: DeleteExamsEntity): Answer<Unit>
    suspend fun createExam(exam: AddExamEntity): Answer<Unit>
    suspend fun updateExam(exam: UpdateExamEntity): Answer<Unit>
}




























