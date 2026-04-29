package com.ppoppi.house.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.domain.COLOR
import com.ppoppi.house.domain.COLOR.Companion.toColor
import com.ppoppi.house.domain.Pet
import com.ppoppi.house.domain.SEX
import com.ppoppi.house.domain.SEX.Companion.toLabel
import com.ppoppi.house.domain.SPECIES
import com.ppoppi.house.domain.SPECIES.Companion.toLabel
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun PetItem(
    pet: Pet,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, Gray100, RoundedCornerShape(12.dp))
                .noRippleClickable(onClick = { onClick() })
                .padding(horizontal = 12.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier =
                Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(pet.color.toColor()),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text =
                    if (pet.species == SPECIES.DOG) {
                        stringResource(R.string.icon_dog)
                    } else {
                        stringResource(
                            R.string.icon_cat,
                        )
                    },
                textAlign = TextAlign.Center,
                style = PpoPpiTheme.typography.headline1,
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = pet.name,
                style = PpoPpiTheme.typography.title2,
                color = Black,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text =
                    stringResource(
                        R.string.pet_item_information,
                        pet.species.toLabel(),
                        pet.age,
                        pet.sex.toLabel(),
                    ),
                style = PpoPpiTheme.typography.body5,
                color = Black,
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = Gray400,
            modifier =
                Modifier
                    .size(40.dp)
                    .padding(10.dp),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PetItemPreview() {
    PpoPpiTheme {
        PetItem(
            modifier = Modifier,
            pet =
                Pet(
                    name = "asdf",
                    species = SPECIES.DOG,
                    breed = "푸들",
                    age = 2,
                    sex = SEX.FEMALE,
                    color = COLOR.PRIMARY50,
                ),
            onClick = {},
        )
    }
}
