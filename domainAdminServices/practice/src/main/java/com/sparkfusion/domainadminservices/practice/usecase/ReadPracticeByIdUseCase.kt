package com.sparkfusion.domainadminservices.practice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portpractice.IPracticeRepository
import com.sparkfusion.domainadminservices.practice.mapper.ReadPracticeEntityMapper
import com.sparkfusion.portdomainservices.admin.portpractice.model.ReadPracticeModel
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IReadPracticeByIdUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadPracticeByIdUseCase @Inject constructor(
    private val practiceRepository: IPracticeRepository,
    private val readPracticeEntityMapper: ReadPracticeEntityMapper
): IReadPracticeByIdUseCase {

    override suspend fun readPracticeById(id: Int): Answer<ReadPracticeModel> {
        return practiceRepository.readPracticeById(id)
            .suspendMap { readPracticeEntityMapper.map(it) }
    }
}
























