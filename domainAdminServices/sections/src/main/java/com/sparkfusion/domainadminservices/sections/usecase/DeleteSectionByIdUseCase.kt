package com.sparkfusion.domainadminservices.sections.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portsections.ISectionsRepository
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IDeleteSectionByIdUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeleteSectionByIdUseCase @Inject constructor(
    private val sectionsRepository: ISectionsRepository
): IDeleteSectionByIdUseCase {

    override suspend fun deleteSectionById(id: Int): Answer<Unit> {
        return sectionsRepository.deleteSectionById(id)
    }
}





















