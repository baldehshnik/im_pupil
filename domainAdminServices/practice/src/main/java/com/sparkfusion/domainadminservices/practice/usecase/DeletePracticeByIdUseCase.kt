package com.sparkfusion.domainadminservices.practice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portpractice.IPracticeRepository
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IDeletePracticeByIdUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeletePracticeByIdUseCase @Inject constructor(
    private val practiceRepository: IPracticeRepository
): IDeletePracticeByIdUseCase {

    override suspend fun deletePracticeById(id: Int): Answer<Unit> {
        return practiceRepository.deletePracticeById(id)
    }
}






















