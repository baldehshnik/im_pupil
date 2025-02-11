package com.sparkfusion.domain.admin.account.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.domain.admin.account.mapper.InstitutionEntityMapper
import com.sparkfusion.domain.admin.port.portaccount.IReadInstitutionUseCase
import com.sparkfusion.domain.admin.port.portaccount.InstitutionModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
internal class ReadInstitutionUseCase @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val institutionRepository: IAdminInstitutionRepository,
    private val institutionEntityMapper: InstitutionEntityMapper
): IReadInstitutionUseCase {

    override suspend fun readInstitution(): Answer<InstitutionModel> = withContext(ioDispatcher) {
        institutionRepository.readInstitutionOfAdmin()
            .suspendMap { institutionEntityMapper.map(it) }
    }
}


















