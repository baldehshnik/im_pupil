package com.sparkfusion.data.pupil.mapper.session

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.session.SessionDataEntity
import com.sparkfusion.dataport.pupil.portsession.SessionEntity
import javax.inject.Inject

internal class SessionDataEntityMapper @Inject constructor(
): Mapper<SessionDataEntity, SessionEntity> {

    override suspend fun map(input: SessionDataEntity): SessionEntity = with(input) {
        SessionEntity(id, type, name, audience, dateTime, status)
    }
}