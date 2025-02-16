package com.sparkfusion.domain.pupil.home.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.porthome.IHomeRepository
import com.sparkfusion.domain.pupil.home.mapper.ReadInstitutionEventEntityMapper
import com.sparkfusion.domain.pupil.port.porthome.model.ReadInstitutionEventModel
import com.sparkfusion.domain.pupil.port.porthome.usecase.IReadEventsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadEventsUseCase @Inject constructor(
    private val homeRepository: IHomeRepository,
    private val readInstitutionEventEntityMapper: ReadInstitutionEventEntityMapper
): IReadEventsUseCase {

    override suspend fun readEvents(): Answer<List<ReadInstitutionEventModel>> {
        return homeRepository.readEvents()
            .suspendMap { list ->
                list.map {
                    readInstitutionEventEntityMapper.map(it)
                }
            }
    }
}





















