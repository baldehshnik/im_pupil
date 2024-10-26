package com.sparkfusion.domain.common.faculty

import com.sparkfusion.domain.common.portfaculty.IReadFacultiesUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadFacultiesUseCase @Inject constructor(
    private val readFacultiesUseCase: IReadFacultiesUseCase
) : IReadFacultiesUseCase {

}
