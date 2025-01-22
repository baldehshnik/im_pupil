package com.sparkfusion.domain.admin.post.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portinstitutionevent.IAdminInstitutionEventRepository
import com.sparkfusion.domain.admin.port.portpost.IUpdateInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.portpost.UpdateInstitutionEventModel
import com.sparkfusion.domain.admin.post.mapper.UpdateInstitutionEventModelMapper
import dagger.hilt.android.scopes.ViewModelScoped
import java.io.File
import javax.inject.Inject

@ViewModelScoped
class UpdateInstitutionEventUseCase @Inject constructor(
    private val institutionEventRepository: IAdminInstitutionEventRepository,
    private val updateInstitutionEventModelMapper: UpdateInstitutionEventModelMapper
): IUpdateInstitutionEventUseCase {

    override suspend fun updateInstitutionEvent(
        model: UpdateInstitutionEventModel,
        image: File?
    ): Answer<Unit> {
        return institutionEventRepository.updateEvent(
            updateInstitutionEventModelMapper.map(model),
            image
        )
    }
}


























