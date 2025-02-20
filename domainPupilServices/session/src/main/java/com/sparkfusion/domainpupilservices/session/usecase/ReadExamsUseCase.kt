package com.sparkfusion.domainpupilservices.session.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portsession.ISessionRepository
import com.sparkfusion.domainpupilservices.session.mapper.SessionEntityMapper
import com.sparkfusion.portdomainservices.pupil.portsession.IReadExamsUseCase
import com.sparkfusion.portdomainservices.pupil.portsession.SessionModel
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadExamsUseCase @Inject constructor(
    private val sessionRepository: ISessionRepository,
    private val sessionEntityMapper: SessionEntityMapper
) : IReadExamsUseCase {

    override suspend fun readExams(): Answer<List<SessionModel>> {
        return sessionRepository.readExams(LocalDate.now())
            .suspendMap { list -> list.map { sessionEntityMapper.map(it) } }
    }
}

















