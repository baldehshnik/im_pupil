package com.sparkfusion.domain.admin.post.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portinstitutionevent.IAdminInstitutionEventRepository
import com.sparkfusion.domain.admin.port.portpost.AddInstitutionEventModel
import com.sparkfusion.domain.admin.port.portpost.IAddInstitutionEventUseCase
import com.sparkfusion.domain.admin.post.mapper.AddInstitutionEventModelMapper
import dagger.hilt.android.scopes.ViewModelScoped
import java.io.File
import javax.inject.Inject

@ViewModelScoped
class AddInstitutionEventUseCase @Inject constructor(
    private val institutionEventRepository: IAdminInstitutionEventRepository,
    private val addInstitutionEventModelMapper: AddInstitutionEventModelMapper
): IAddInstitutionEventUseCase {

    override suspend fun addInstitutionEvent(
        model: AddInstitutionEventModel,
        image: File
    ): Answer<Unit> {
        return institutionEventRepository.addEvent(
            addInstitutionEventModelMapper.map(model),
            image
        )
    }
}















