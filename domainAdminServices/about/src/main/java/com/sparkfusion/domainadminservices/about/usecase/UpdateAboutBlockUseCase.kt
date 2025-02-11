package com.sparkfusion.domainadminservices.about.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portabout.IAdminAboutRepository
import com.sparkfusion.portdomainservices.admin.portabout.IUpdateAboutBlockUseCase
import com.sparkfusion.portdomainservices.admin.portabout.UpdateAboutModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class UpdateAboutBlockUseCase @Inject constructor(
    private val adminAboutRepository: IAdminAboutRepository
) : IUpdateAboutBlockUseCase {

    override suspend fun updateBlock(blocks: List<UpdateAboutModel>): Answer<Unit> {
        for (block in blocks) {
            adminAboutRepository.updateAboutBlock(
                block.id, block.description, block.icon, block.file
            ).onFailure {
                return Answer.Failure(it)
            }
        }

        return Answer.Success(Unit)
    }
}