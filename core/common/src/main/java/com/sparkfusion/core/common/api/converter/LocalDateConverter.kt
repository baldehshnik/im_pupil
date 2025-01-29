package com.sparkfusion.core.common.api.converter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateConverter : TypeAdapter<LocalDate>() {

    override fun write(writer: JsonWriter, value: LocalDate) {
        writer.value(value.format(DateTimeFormatter.ISO_DATE))
    }

    override fun read(reader: JsonReader): LocalDate {
        return LocalDate.parse(reader.nextString(), DateTimeFormatter.ISO_DATE)
    }
}

