package com.ppoppi.house.ui.main.setting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ppoppi.house.R
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.Red400
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.noRippleClickable

@Composable
fun LogoutDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
    ) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = White,
            modifier = modifier.fillMaxWidth(),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 28.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Red400,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.setting_logout_dialog_description),
                    style = PpoPpiTheme.typography.title1,
                    color = Gray400,
                )
                Spacer(modifier = Modifier.height(22.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Button(
                        onClick = onConfirm,
                        modifier =
                            Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .noRippleClickable(onClick = onConfirm)
                                .weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = Primary400,
                            ),
                    ) {
                        Text(
                            text = stringResource(R.string.setting_logout_dialog_logout),
                            style = PpoPpiTheme.typography.body4,
                            color = White,
                        )
                    }
                    Button(
                        onClick = onDismiss,
                        modifier =
                            Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .noRippleClickable(onClick = onConfirm)
                                .weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = Gray100,
                            ),
                    ) {
                        Text(
                            text = stringResource(R.string.setting_logout_dialog_cancel),
                            style = PpoPpiTheme.typography.body4,
                            color = Gray400,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LogoutDialogPreview() {
    PpoPpiTheme {
        LogoutDialog(onConfirm = {}, onDismiss = {})
    }
}
