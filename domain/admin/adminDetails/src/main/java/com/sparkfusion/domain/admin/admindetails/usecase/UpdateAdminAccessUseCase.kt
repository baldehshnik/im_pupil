package com.sparkfusion.domain.admin.admindetails.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portadmindetails.IAdminDetailsRepository
import com.sparkfusion.domain.admin.port.portadmindetails.IUpdateAdminAccessUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class UpdateAdminAccessUseCase @Inject constructor(
    private val adminDetailsRepository: IAdminDetailsRepository
): IUpdateAdminAccessUseCase {

    override suspend fun updateAdminAccess(id: Int): Answer<Unit> {
        return adminDetailsRepository.updateAccessOfAdmin(id)
    }
}