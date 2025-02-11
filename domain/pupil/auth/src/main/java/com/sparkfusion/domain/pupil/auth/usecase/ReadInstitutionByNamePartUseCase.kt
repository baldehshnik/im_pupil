package com.sparkfusion.domain.pupil.auth.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portauth.IAuthRepository
import com.sparkfusion.domain.pupil.auth.mapper.EducationalInstitutionEntityMapper
import com.sparkfusion.domain.pupil.port.portauth.model.EducationalInstitutionModel
import com.sparkfusion.domain.pupil.port.portauth.usecase.IReadInstitutionByNamePartUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadInstitutionByNamePartUseCase @Inject constructor(
    private val repository: IAuthRepository,
    private val educationalInstitutionEntityMapper: EducationalInstitutionEntityMapper
) : IReadInstitutionByNamePartUseCase {

    override suspend fun readInstitutionByNamePart(namePart: String): Answer<List<EducationalInstitutionModel>> {
        return repository.readInstitutionByNamePart(namePart)
            .suspendMap { list ->
                list.map {
                    educationalInstitutionEntityMapper.map(it)
                }
            }
    }
}





























