package com.ppoppi.house.ui.main.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary200
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun PetButton(
    onClick: () -> Unit,
    pet: Pet,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (isSelected) Primary50 else White
    val borderColor = if (isSelected) Primary200 else Gray100

    Box(
        modifier =
            modifier
                .size(60.dp, 66.dp)
                .border(1.dp, borderColor, RoundedCornerShape(10.dp))
                .background(backgroundColor, RoundedCornerShape(10.dp))
                .noRippleClickable(onClick),
    ) {
        Text(
            text = if (pet.species == SPECIES.DOG) "🐶" else "🐱",
            style = PpoPpiTheme.typography.headline1,
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 8.dp),
        )
        Text(
            text = pet.name,
            style = PpoPpiTheme.typography.title3,
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PetButtonPreview() {
    PpoPpiTheme {
        PetButton(
            onClick = {},
            pet =
                Pet(
                    name = "뽀삐",
                    species = SPECIES.DOG,
                    breed = "말티즈",
                    age = 1,
                    sex = SEX.MALE,
                    color = COLOR.PRIMARY50,
                ),
            isSelected = true,
        )
    }
}
