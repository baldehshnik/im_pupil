package com.sparkfusion.dataport.pupil.portaccount

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portaccount.entity.ReadPupilAccountEntity

interface IAccountRepository {

    suspend fun readPupilAccount(): Answer<ReadPupilAccountEntity?>
}



























