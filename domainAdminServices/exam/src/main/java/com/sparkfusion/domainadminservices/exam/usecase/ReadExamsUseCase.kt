package com.sparkfusion.domainadminservices.exam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portexam.IExamRepository
import com.sparkfusion.domainadminservices.exam.mapper.ReadExamEntityMapper
import com.sparkfusion.portdomainservices.admin.portexam.model.ReadExamModel
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IReadExamsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
class ReadExamsUseCase @Inject constructor(
    private val examRepository: IExamRepository,
    private val readExamEntityMapper: ReadExamEntityMapper
) : IReadExamsUseCase {

    override suspend fun readExams(groupId: Int): Answer<List<ReadExamModel>> {
        return examRepository.readExams(groupId, LocalDate.now())
            .suspendMap { list -> list.map { readExamEntityMapper.map(it) } }
    }
}

























