package com.sparkfusion.domainpupilservices.practice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portpractice.IPracticeRepository
import com.sparkfusion.domainpupilservices.practice.mapper.PracticeListEntityMapper
import com.sparkfusion.portdomainservices.pupil.portpractice.IReadPracticesUseCase
import com.sparkfusion.portdomainservices.pupil.portpractice.PracticeListModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadPracticesUseCase @Inject constructor(
    private val practiceRepository: IPracticeRepository,
    private val practiceListEntityMapper: PracticeListEntityMapper
) : IReadPracticesUseCase {

    override suspend fun readPractices(): Answer<List<PracticeListModel>> {
        return practiceRepository.readPractices()
            .suspendMap { list ->
                list.map {
                    practiceListEntityMapper.map(it)
                }
            }
    }
}



















