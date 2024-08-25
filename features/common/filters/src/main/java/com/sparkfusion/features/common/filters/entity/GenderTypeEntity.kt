package com.sparkfusion.features.common.filters.entity

import androidx.compose.runtime.saveable.Saver

val genderTypeEntitySaver = Saver<GenderTypeEntity, String>(
    save = { it.name },
    restore = {
        when(it) {
            GenderTypeEntity.GirlGender.name -> GenderTypeEntity.GirlGender
            GenderTypeEntity.BoyGender.name -> GenderTypeEntity.BoyGender
            GenderTypeEntity.AnyGender.name -> GenderTypeEntity.AnyGender
            else -> throw IllegalArgumentException("Unknown gender type")
        }
    }
)

sealed class GenderTypeEntity(val name: String) {
    data object GirlGender : GenderTypeEntity("GirlGender")
    data object BoyGender : GenderTypeEntity("BoyGender")

    data object AnyGender : GenderTypeEntity("AnyGender")
}
