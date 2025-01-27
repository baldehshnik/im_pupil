package com.sparkfusion.domainadminservices.about.usecase

import com.sparkfusion.core.common.exception.ImPupilException
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataport.common.portabout.IAboutRepository
import com.sparkfusion.domainadminservices.about.mapper.ReadAboutEntityMapper
import com.sparkfusion.portdomainservices.admin.portabout.AboutModel
import com.sparkfusion.portdomainservices.admin.portabout.IReadAboutBlocksUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadAboutBlocksUseCase @Inject constructor(
    private val aboutRepository: IAboutRepository,
    private val adminInstitutionRepository: IAdminInstitutionRepository,
    private val readAboutEntityMapper: ReadAboutEntityMapper
) : IReadAboutBlocksUseCase {

    override suspend fun readAboutBlock(): Answer<List<AboutModel>> {
        var error: ImPupilException? = null
        val result = adminInstitutionRepository.readInstitutionOfAdmin()
            .onFailure { error = it }

        if (error != null) return Answer.Failure(error!!)
        return aboutRepository.readAboutBlocksOfInstitution(result.unwrap().id)
            .suspendMap { it.map { model -> readAboutEntityMapper.map(model) } }
    }
}












