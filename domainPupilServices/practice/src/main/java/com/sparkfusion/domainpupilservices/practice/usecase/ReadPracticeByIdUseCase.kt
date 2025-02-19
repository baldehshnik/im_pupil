package com.sparkfusion.domainpupilservices.practice.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portpractice.IPracticeRepository
import com.sparkfusion.domainpupilservices.practice.mapper.PracticeInfoEntityMapper
import com.sparkfusion.portdomainservices.pupil.portpractice.IReadPracticeByIdUseCase
import com.sparkfusion.portdomainservices.pupil.portpractice.PracticeInfoModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadPracticeByIdUseCase @Inject constructor(
    private val practiceRepository: IPracticeRepository,
    private val practiceInfoEntityMapper: PracticeInfoEntityMapper
) : IReadPracticeByIdUseCase {

    override suspend fun readPracticeById(id: Int): Answer<PracticeInfoModel> {
        return practiceRepository.readPracticeById(id)
            .suspendMap { practiceInfoEntityMapper.map(it) }
    }
}



















