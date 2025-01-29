package com.sparkfusion.domainadminservices.exam.usecase

import com.sparkfusion.core.common.exception.ImPupilException
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataport.admin.portexam.IExamRepository
import com.sparkfusion.domainadminservices.exam.mapper.GroupEntityMapper
import com.sparkfusion.portdomainservices.admin.portexam.model.GroupModel
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IReadGroupByNamePartUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadGroupByNamePartUseCase @Inject constructor(
    private val examRepository: IExamRepository,
    private val institutionRepository: IAdminInstitutionRepository,
    private val groupEntityMapper: GroupEntityMapper
) : IReadGroupByNamePartUseCase {

    override suspend fun readGroupByNamePart(namePart: String): Answer<List<GroupModel>> {
        var error: ImPupilException? = null
        val result = institutionRepository.readInstitutionOfAdmin()
            .onFailure { error = it }

        if (error != null) return Answer.Failure(error!!)
        return examRepository.readGroupByNamePart(result.unwrap().id, namePart)
            .suspendMap { list -> list.map { groupEntityMapper.map(it) } }
    }
}






















