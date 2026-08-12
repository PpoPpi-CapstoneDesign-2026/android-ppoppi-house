package com.ppoppi.house.ui.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.format(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return this.format(formatter)
}
