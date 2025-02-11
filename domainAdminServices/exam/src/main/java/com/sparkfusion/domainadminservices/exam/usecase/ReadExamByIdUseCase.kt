package com.sparkfusion.domainadminservices.exam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portexam.IExamRepository
import com.sparkfusion.domainadminservices.exam.mapper.ReadExamEntityMapper
import com.sparkfusion.portdomainservices.admin.portexam.model.ReadExamModel
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IReadExamByIdUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadExamByIdUseCase @Inject constructor(
    private val examRepository: IExamRepository,
    private val readExamEntityMapper: ReadExamEntityMapper
): IReadExamByIdUseCase {

    override suspend fun readExamById(id: Int): Answer<ReadExamModel> {
        return examRepository.readExamById(id)
            .suspendMap { readExamEntityMapper.map(it) }
    }
}

























