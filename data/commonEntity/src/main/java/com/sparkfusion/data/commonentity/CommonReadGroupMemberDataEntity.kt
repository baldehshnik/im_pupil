package com.sparkfusion.data.commonentity

interface CommonReadGroupMemberDataEntity {
    val id: Int
    val firstname: String
    val lastname: String
    val patronymic: String
    val prefect: Boolean
    val code: String
    val pupil: CommonPupilDataEntity?
    val educationPlaceDto: CommonEducationPlaceDataEntity?
}