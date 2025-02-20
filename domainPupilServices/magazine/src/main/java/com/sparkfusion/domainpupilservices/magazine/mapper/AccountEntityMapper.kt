package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.AccountEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.AccountModel
import javax.inject.Inject

internal class AccountEntityMapper @Inject constructor(
): Mapper<AccountEntity, AccountModel> {

    override suspend fun map(input: AccountEntity): AccountModel = with(input) {
        AccountModel(id, firstname, lastname, patronymic, prefect, code)
    }
}