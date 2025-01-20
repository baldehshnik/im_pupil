package com.sparkfusion.domain.admin.admindetails.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portadmindetails.IAdminDetailsRepository
import com.sparkfusion.domain.admin.admindetails.mapper.AdminDetailsEntityMapper
import com.sparkfusion.domain.admin.port.portadmindetails.AdminDetailsModel
import com.sparkfusion.domain.admin.port.portadmindetails.IReadAdminDetailsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadAdminDetailsUseCase @Inject constructor(
    private val readIAdminDetailsRepository: IAdminDetailsRepository,
    private val adminDetailsEntityMapper: AdminDetailsEntityMapper
): IReadAdminDetailsUseCase {

    override suspend fun readAdminDetailsById(id: Int): Answer<AdminDetailsModel> {
        return readIAdminDetailsRepository.readAdminDetails(id)
            .suspendMap { adminDetailsEntityMapper.map(it) }
    }
}
























