package com.sparkfusion.domain.admin.admindetails.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portadmindetails.IAdminDetailsRepository
import com.sparkfusion.domain.admin.port.portadmindetails.IDeleteAdminUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeleteAdminUseCase @Inject constructor(
    private val adminDetailsRepository: IAdminDetailsRepository
): IDeleteAdminUseCase {

    override suspend fun deleteAdminById(id: Int): Answer<Unit> {
        return adminDetailsRepository.deleteAdmin(id)
    }
}



















