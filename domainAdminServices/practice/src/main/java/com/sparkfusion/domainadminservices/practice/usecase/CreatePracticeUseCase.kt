package com.sparkfusion.domainadminservices.practice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portpractice.IPracticeRepository
import com.sparkfusion.domainadminservices.practice.mapper.CreatePracticeModelMapper
import com.sparkfusion.portdomainservices.admin.portpractice.model.CreatePracticeModel
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.ICreatePracticeUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.io.File
import javax.inject.Inject

@ViewModelScoped
class CreatePracticeUseCase @Inject constructor(
    private val practiceRepository: IPracticeRepository,
    private val createPracticeModelMapper: CreatePracticeModelMapper
): ICreatePracticeUseCase {

    override suspend fun createPractice(practice: CreatePracticeModel, image: File): Answer<Unit> {
        return practiceRepository.createPractice(createPracticeModelMapper.map(practice), image)
    }
}






















