package com.ppoppi.house.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@Composable
fun BreedDropdown(
    selectedBreed: String?,
    breeds: List<String>,
    onBreedSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    visibleCount: Int = 4,
    hint: String = stringResource(R.string.dropdown_hint),
) {
    var expanded by remember { mutableStateOf(false) }
    var dropdownWidth by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current

    val itemHeight = 48.dp
    val maxDropdownHeight = itemHeight * visibleCount

    Box(modifier = modifier) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        dropdownWidth = coordinates.size.width
                    }.border(1.dp, Gray100, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .height(50.dp)
                    .clickable { expanded = !expanded }
                    .padding(horizontal = 16.dp),
        ) {
            Text(
                text = selectedBreed ?: hint,
                style = PpoPpiTheme.typography.body1,
                modifier = Modifier.align(Alignment.CenterStart),
                color = if (selectedBreed == null) Gray200 else Black,
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = stringResource(R.string.dropdown_description),
                modifier = Modifier.align(Alignment.CenterEnd),
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier =
                Modifier
                    .width(with(density) { dropdownWidth.toDp() })
                    .heightIn(max = maxDropdownHeight)
                    .background(White),
        ) {
            breeds.forEach { breed ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = breed,
                            style = PpoPpiTheme.typography.body1,
                        )
                    },
                    onClick = {
                        onBreedSelected(breed)
                        expanded = false
                    },
                    modifier = Modifier.border(width = 1.dp, color = Gray100),
                )
            }
        }
    }
}
