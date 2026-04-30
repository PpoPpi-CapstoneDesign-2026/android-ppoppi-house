package com.ppoppi.house.domain

import androidx.compose.ui.graphics.Color
import com.ppoppi.house.ui.theme.Amber200
import com.ppoppi.house.ui.theme.Amber50
import com.ppoppi.house.ui.theme.Amber600
import com.ppoppi.house.ui.theme.Blue400
import com.ppoppi.house.ui.theme.Blue50
import com.ppoppi.house.ui.theme.Blue600
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.Primary600
import com.ppoppi.house.ui.theme.Primary800
import com.ppoppi.house.ui.theme.Red400
import com.ppoppi.house.ui.theme.Red50
import com.ppoppi.house.ui.theme.Red600

enum class Triage {
    URGENT,
    SOON,
    MONITOR,
    NORMAL,
    ;

    companion object {
        fun Triage.toColor(): List<Color> =
            when (this) {
                URGENT -> listOf(Red50, Red400, Red600)
                SOON -> listOf(Amber50, Amber200, Amber600)
                MONITOR -> listOf(Blue50, Blue400, Blue600)
                NORMAL -> listOf(Primary50, Primary600, Primary800)
            }
    }
}
