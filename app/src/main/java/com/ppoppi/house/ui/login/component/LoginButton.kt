package com.ppoppi.house.ui.login.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@Composable
fun LoginButton(
    backgroundColor: Color,
    imageResource: Painter,
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier =
            modifier
                .fillMaxWidth()
                .height(52.dp),
        shape = RoundedCornerShape(8.dp),
        color = backgroundColor,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = imageResource,
                contentDescription = null,
                modifier =
                    Modifier
                        .padding(start = 30.dp)
                        .size(16.dp)
                        .align(Alignment.CenterStart),
            )
            Text(
                text = text,
                modifier =
                    Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.Center),
                style = PpoPpiTheme.typography.title2,
                color = textColor,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LoginButtonPreview() {
    PpoPpiTheme {
        Column {
            LoginButton(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 44.dp),
                backgroundColor = Color(0xFF03A94D),
                imageResource = painterResource(R.drawable.ic_naver_login),
                text = stringResource(R.string.login_with_naver),
                textColor = White,
            )
        }
    }
}
