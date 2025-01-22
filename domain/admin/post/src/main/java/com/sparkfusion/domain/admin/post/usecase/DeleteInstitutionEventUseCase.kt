package com.sparkfusion.domain.admin.post.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portinstitutionevent.IAdminInstitutionEventRepository
import com.sparkfusion.domain.admin.port.portpost.IDeleteInstitutionEventUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeleteInstitutionEventUseCase @Inject constructor(
    private val adminInstitutionEventRepository: IAdminInstitutionEventRepository
): IDeleteInstitutionEventUseCase {

    override suspend fun deleteInstitutionEventById(id: Int): Answer<Unit> {
        return adminInstitutionEventRepository.deleteEventById(id)
    }
}





























