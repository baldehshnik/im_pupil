package com.sparkfusion.domainpupilservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.domainpupilservices.magazine.mapper.GroupMemberWithPassEntityMapper
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberWithPassModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPassesUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadPassesUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val groupMemberWithPassEntityMapper: GroupMemberWithPassEntityMapper
) : IReadPassesUseCase {

    override suspend fun readPasses(lessonId: Int): Answer<List<GroupMemberWithPassModel>> {
        return magazineRepository.readPasses(lessonId, LocalDate.now())
            .suspendMap { list -> list.map { groupMemberWithPassEntityMapper.map(it) } }
    }
}




























