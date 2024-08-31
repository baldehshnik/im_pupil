package com.sparkfusion.core.common.mapper

interface Mapper<in I, out O> {

    suspend fun map(input: I): O
}