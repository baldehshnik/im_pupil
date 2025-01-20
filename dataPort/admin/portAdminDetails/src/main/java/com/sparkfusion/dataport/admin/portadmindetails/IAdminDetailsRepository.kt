package com.sparkfusion.dataport.admin.portadmindetails

import com.sparkfusion.core.common.result.Answer

interface IAdminDetailsRepository {

    suspend fun readAdminDetails(id: Int): Answer<AdminDetailsEntity>

    suspend fun updateAccessOfAdmin(id: Int): Answer<Unit>

    suspend fun deleteAdmin(id: Int): Answer<Unit>
}













