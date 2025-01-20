package com.sparkfusion.domain.admin.port.porthome

import com.sparkfusion.core.common.result.Answer

interface IReadAccountUseCase {

    suspend fun readAccount(): Answer<AccountModel>
}












