package com.sparkfusion.data.admin.source

import com.sparkfusion.dataPort.admin.portinstitution.InstitutionEntity
import com.sparkfusion.dataport.admin.portstudents.entity.FacultyEntity
import com.sparkfusion.dataport.admin.portstudents.entity.SpecialityEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InstitutionApiService {

    @GET("/education/institution/ofAdmin")
    suspend fun readInstitutionOfAdmin(): Response<InstitutionEntity>

    @GET("/education/faculty/all")
    suspend fun readFaculties(): Response<List<FacultyEntity>>

    @GET("/education/speciality/byFaculty")
    suspend fun readSpecialitiesByFaculty(
        @Query("facultyId") facultyId: Int
    ): Response<List<SpecialityEntity>>
}






























