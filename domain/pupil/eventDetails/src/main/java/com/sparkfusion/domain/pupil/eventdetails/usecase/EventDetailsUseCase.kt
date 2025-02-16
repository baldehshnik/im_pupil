package com.sparkfusion.domain.pupil.eventdetails.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.porteventdetails.IEventDetailsRepository
import com.sparkfusion.domain.pupil.eventdetails.mapper.EventDetailsEntityMapper
import com.sparkfusion.domain.pupil.port.porteventdetails.model.EventDetailsModel
import com.sparkfusion.domain.pupil.port.porteventdetails.usecase.IEventDetailsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class EventDetailsUseCase @Inject constructor(
    private val eventDetailsRepository: IEventDetailsRepository,
    private val eventDetailsEntityMapper: EventDetailsEntityMapper
) : IEventDetailsUseCase {

    override suspend fun readEventById(id: Int): Answer<EventDetailsModel> {
        return eventDetailsRepository.readEventById(id)
            .suspendMap { eventDetailsEntityMapper.map(it) }
    }
}




















