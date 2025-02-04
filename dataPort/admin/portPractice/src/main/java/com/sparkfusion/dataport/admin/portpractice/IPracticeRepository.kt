package com.sparkfusion.dataport.admin.portpractice

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portpractice.entity.CreatePracticeEntity
import com.sparkfusion.dataport.admin.portpractice.entity.ReadListPracticeEntity
import com.sparkfusion.dataport.admin.portpractice.entity.ReadPracticeEntity
import com.sparkfusion.dataport.admin.portpractice.entity.UpdatePracticeEntity
import java.io.File

interface IPracticeRepository {

    suspend fun readPracticeById(id: Int): Answer<ReadPracticeEntity>
    suspend fun deletePracticeById(id: Int): Answer<Unit>
    suspend fun readPractices(institutionId: Int): Answer<List<ReadListPracticeEntity>>
    suspend fun createPractice(practice: CreatePracticeEntity, image: File): Answer<Unit>
    suspend fun updatePractice(practice: UpdatePracticeEntity, image: File?): Answer<Unit>
}


































