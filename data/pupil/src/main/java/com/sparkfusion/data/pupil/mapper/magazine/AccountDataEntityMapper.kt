package com.sparkfusion.data.pupil.mapper.magazine

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.account.ReadPupilAccountDataEntity
import com.sparkfusion.dataport.pupil.portmagazine.AccountEntity
import javax.inject.Inject

internal class AccountDataEntityMapper @Inject constructor(
) : Mapper<ReadPupilAccountDataEntity, AccountEntity> {

    override suspend fun map(input: ReadPupilAccountDataEntity): AccountEntity = with(input) {
        AccountEntity(id, firstname, lastname, patronymic, prefect, code)
    }
}