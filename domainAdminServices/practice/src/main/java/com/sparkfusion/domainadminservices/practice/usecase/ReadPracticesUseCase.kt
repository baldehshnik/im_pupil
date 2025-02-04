package com.sparkfusion.domainadminservices.practice.usecase

import com.sparkfusion.core.common.exception.ImPupilException
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataport.admin.portpractice.IPracticeRepository
import com.sparkfusion.domainadminservices.practice.mapper.ReadListPracticeEntityMapper
import com.sparkfusion.portdomainservices.admin.portpractice.model.ReadListPracticeModel
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IReadPracticesUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadPracticesUseCase @Inject constructor(
    private val practiceRepository: IPracticeRepository,
    private val institutionRepository: IAdminInstitutionRepository,
    private val readListPracticeEntityMapper: ReadListPracticeEntityMapper
) : IReadPracticesUseCase {

    override suspend fun readPractices(): Answer<List<ReadListPracticeModel>> {
        var error: ImPupilException? = null
        val result = institutionRepository.readInstitutionOfAdmin()
            .onFailure { error = it }

        if (error != null) return Answer.Failure(error!!)
        return practiceRepository.readPractices(result.unwrap().id)
            .suspendMap { list -> list.map { readListPracticeEntityMapper.map(it) } }
    }
}




















