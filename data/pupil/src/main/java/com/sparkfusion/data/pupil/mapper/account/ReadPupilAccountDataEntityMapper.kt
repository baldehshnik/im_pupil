package com.sparkfusion.data.pupil.mapper.account

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.account.ReadPupilAccountDataEntity
import com.sparkfusion.dataport.pupil.portaccount.entity.ReadPupilAccountEntity
import javax.inject.Inject

internal class ReadPupilAccountDataEntityMapper @Inject constructor(
    private val pupilDataEntityMapper: PupilDataEntityMapper,
    private val groupInfoDataEntityMapper: GroupInfoDataEntityMapper
) : Mapper<ReadPupilAccountDataEntity, ReadPupilAccountEntity> {

    override suspend fun map(input: ReadPupilAccountDataEntity): ReadPupilAccountEntity =
        with(input) {
            ReadPupilAccountEntity(
                id,
                firstname,
                lastname,
                patronymic,
                prefect,
                code,
                pupilDataEntityMapper.map(pupil),
                groupInfoDataEntityMapper.map(groupInfo)
            )
        }
}

























