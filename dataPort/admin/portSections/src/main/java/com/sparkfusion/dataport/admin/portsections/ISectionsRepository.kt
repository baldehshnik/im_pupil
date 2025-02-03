package com.sparkfusion.dataport.admin.portsections

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portsections.entity.CreateSectionEntity
import com.sparkfusion.dataport.admin.portsections.entity.ReadSectionEntity
import com.sparkfusion.dataport.admin.portsections.entity.UpdateSectionEntity
import java.io.File

interface ISectionsRepository {

    suspend fun readSections(institutionId: Int): Answer<List<ReadSectionEntity>>
    suspend fun readSectionById(id: Int): Answer<ReadSectionEntity>
    suspend fun deleteSectionById(id: Int): Answer<Unit>

    suspend fun createSection(
        createSection: CreateSectionEntity,
        image: File
    ): Answer<Unit>

    suspend fun updateSection(
        updateSection: UpdateSectionEntity,
        image: File?
    ): Answer<Unit>
}

























