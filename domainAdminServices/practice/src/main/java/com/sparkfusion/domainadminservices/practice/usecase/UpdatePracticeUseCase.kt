package com.sparkfusion.domainadminservices.practice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portpractice.IPracticeRepository
import com.sparkfusion.domainadminservices.practice.mapper.UpdatePracticeModelMapper
import com.sparkfusion.portdomainservices.admin.portpractice.model.UpdatePracticeModel
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IUpdatePracticeUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.io.File
import javax.inject.Inject

@ViewModelScoped
internal class UpdatePracticeUseCase @Inject constructor(
    private val practiceRepository: IPracticeRepository,
    private val updatePracticeModelMapper: UpdatePracticeModelMapper
) : IUpdatePracticeUseCase {

    override suspend fun updatePractice(practice: UpdatePracticeModel, image: File?): Answer<Unit> {
        return practiceRepository.updatePractice(updatePracticeModelMapper.map(practice), image)
    }
}


























