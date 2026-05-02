package com.ppoppi.house.domain.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import com.ppoppi.house.ui.theme.Primary200
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.Primary600
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(
    val name: String,
    val species: SPECIES,
    val breed: String,
    val age: Int,
    val sex: SEX,
    val color: COLOR,
) : Parcelable

enum class SPECIES {
    DOG,
    CAT,
    ;

    companion object {
        fun SPECIES.toLabel(): String =
            when (this) {
                DOG -> "강아지"
                CAT -> "고양이"
            }
    }
}

enum class SEX {
    MALE,
    FEMALE,
    ;

    companion object {
        fun SEX.toLabel(): String =
            when (this) {
                MALE -> "남"
                FEMALE -> "여"
            }
    }
}

enum class COLOR {
    PRIMARY50,
    PRIMARY200,
    PRIMARY600,
    ;

    companion object {
        fun COLOR.toColor(): Color =
            when (this) {
                PRIMARY50 -> Primary50
                PRIMARY200 -> Primary200
                PRIMARY600 -> Primary600
            }
    }
}
