package com.sparkfusion.portdomainservices.pupil.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdatePassesStatusModel

interface IUpdatePassesUseCase {

    suspend fun updatePasses(
        updatePassesStatus: UpdatePassesStatusModel
    ): Answer<Unit>
}