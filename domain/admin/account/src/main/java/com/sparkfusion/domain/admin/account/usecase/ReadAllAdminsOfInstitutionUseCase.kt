package com.sparkfusion.domain.admin.account.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portaccount.IAdminAccountRepository
import com.sparkfusion.domain.admin.account.mapper.InstitutionAdminEntityMapper
import com.sparkfusion.domain.admin.port.portaccount.IReadAllAdminsOfInstitutionUseCase
import com.sparkfusion.domain.admin.port.portaccount.InstitutionAdminModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ReadAllAdminsOfInstitutionUseCase @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val institutionAdminEntityMapper: InstitutionAdminEntityMapper,
    private val adminAccountRepository: IAdminAccountRepository
): IReadAllAdminsOfInstitutionUseCase {

    override suspend fun readAdminsOfInstitution(): Answer<List<InstitutionAdminModel>> = withContext(ioDispatcher) {
        adminAccountRepository.readAdminsOfInstitution()
            .suspendMap {
                it.map { model -> institutionAdminEntityMapper.map(model) }
            }
    }
}

















