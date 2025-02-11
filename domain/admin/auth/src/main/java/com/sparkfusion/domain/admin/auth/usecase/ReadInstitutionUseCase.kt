package com.sparkfusion.domain.admin.auth.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portAuth.repository.IAdminAuthRepository
import com.sparkfusion.domain.admin.auth.mapper.EducationInstitutionEntityMapper
import com.sparkfusion.domain.admin.port.portauth.IReadInstitutionUseCase
import com.sparkfusion.domain.admin.port.portauth.InstitutionModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
internal class ReadInstitutionUseCase @Inject constructor(
    @IODispatcher private  val ioDispatcher: CoroutineDispatcher,
    private val adminAuthRepository: IAdminAuthRepository,
    private val educationInstitutionEntityMapper: EducationInstitutionEntityMapper
): IReadInstitutionUseCase {

    override suspend fun readInstitutionByNamePart(namePart: String): Answer<List<InstitutionModel>> = withContext(ioDispatcher) {
        adminAuthRepository.readInstitutionByNamePart(namePart)
            .suspendMap { list -> list.map { educationInstitutionEntityMapper.map(it) } }
    }
}


















