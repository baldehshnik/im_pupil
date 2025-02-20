package com.sparkfusion.domainpupilservices.session.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portsession.SessionEntity
import com.sparkfusion.portdomainservices.pupil.portsession.SessionModel
import javax.inject.Inject

internal class SessionEntityMapper @Inject constructor(
): Mapper<SessionEntity, SessionModel> {

    override suspend fun map(input: SessionEntity): SessionModel = with(input) {
        SessionModel(id, type, name, audience, dateTime, status)
    }
}