package com.sparkfusion.domain.pupil.account.mapper

import android.util.Log
import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portaccount.entity.ReadPupilAccountEntity
import com.sparkfusion.domain.pupil.port.portaccount.model.ReadPupilAccountModel
import javax.inject.Inject

internal class ReadPupilAccountEntityMapper @Inject constructor(
    private val pupilEntityMapper: PupilEntityMapper,
    private val groupInfoEntityMapper: GroupInfoEntityMapper
) : Mapper<ReadPupilAccountEntity, ReadPupilAccountModel> {

    override suspend fun map(input: ReadPupilAccountEntity): ReadPupilAccountModel = with(input) {
        Log.d("TAGTAG", input.toString())
        ReadPupilAccountModel(
            id,
            firstname,
            lastname,
            patronymic,
            isPrefect,
            code,
            pupilEntityMapper.map(pupil),
            groupInfoEntityMapper.map(groupInfo)
        )
    }
}























