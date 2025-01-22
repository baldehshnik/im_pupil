package com.sparkfusion.domain.admin.post.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.common.portinstitutionevent.IInstitutionEventRepository
import com.sparkfusion.domain.admin.port.portpost.IReadInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.portpost.InstitutionEventModel
import com.sparkfusion.domain.admin.post.mapper.InstitutionEventEntityMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadInstitutionEventUseCase @Inject constructor(
    private val institutionEventRepository: IInstitutionEventRepository,
    private val institutionEventEntityMapper: InstitutionEventEntityMapper
): IReadInstitutionEventUseCase {

    override suspend fun readInstitutionEvent(id: Int): Answer<InstitutionEventModel> {
        return institutionEventRepository.readInstitutionEvent(id)
            .suspendMap { institutionEventEntityMapper.map(it) }
    }
}




















