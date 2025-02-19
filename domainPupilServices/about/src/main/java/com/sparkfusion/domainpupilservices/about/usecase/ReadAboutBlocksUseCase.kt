package com.sparkfusion.domainpupilservices.about.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portabout.IAboutRepository
import com.sparkfusion.domainpupilservices.about.mapper.AboutEntityMapper
import com.sparkfusion.portdomainservices.pupil.portabout.model.AboutModel
import com.sparkfusion.portdomainservices.pupil.portabout.usecase.IReadAboutBlocksUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadAboutBlocksUseCase @Inject constructor(
    private val aboutRepository: IAboutRepository,
    private val aboutEntityMapper: AboutEntityMapper
) : IReadAboutBlocksUseCase {

    override suspend fun readAboutBlocks(): Answer<List<AboutModel>> {
        return aboutRepository.readAboutBlocks()
            .suspendMap { list ->
                list.map {
                    aboutEntityMapper.map(it)
                }
            }
    }
}




















