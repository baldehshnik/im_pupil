package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.exception.ImPupilException
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.GroupEntityMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.GroupModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadGroupByNamePartUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupByNamePartUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val institutionRepository: IAdminInstitutionRepository,
    private val groupEntityMapper: GroupEntityMapper
) : IReadGroupByNamePartUseCase {

    override suspend fun readGroupByNamePart(namePart: String): Answer<List<GroupModel>> {
        var error: ImPupilException? = null
        val result = institutionRepository.readInstitutionOfAdmin()
            .onFailure { error = it }

        if (error != null) return Answer.Failure(error!!)
        return magazineRepository.readGroupByNamePart(result.unwrap().id, namePart)
            .suspendMap { list -> list.map { groupEntityMapper.map(it) } }
    }
}




















