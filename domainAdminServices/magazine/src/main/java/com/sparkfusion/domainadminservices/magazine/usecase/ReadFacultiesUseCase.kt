package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.FacultyEntityMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.FacultyModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadFacultiesUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadFacultiesUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val facultyEntityMapper: FacultyEntityMapper
): IReadFacultiesUseCase {

    override suspend fun readFaculties(): Answer<List<FacultyModel>> {
        return magazineRepository.readFaculties()
            .suspendMap { list -> list.map { facultyEntityMapper.map(it) } }
    }
}