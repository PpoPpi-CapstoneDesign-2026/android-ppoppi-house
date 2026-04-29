package com.ppoppi.house.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.ui.login.component.LoginButton
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.ic_dog_and_cat),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(160.dp))
        LoginButton(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 44.dp)
                    .padding(bottom = 18.dp),
            backgroundColor = Color(0xFFFEE500),
            imageResource = painterResource(R.drawable.ic_kakao_login),
            text = stringResource(R.string.login_with_kakao),
            textColor = Black,
        )
        LoginButton(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 44.dp)
                    .padding(bottom = 18.dp),
            backgroundColor = Black,
            imageResource = painterResource(R.drawable.ic_google_login),
            text = stringResource(R.string.login_with_google),
            textColor = White,
        )
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

@Composable
@Preview(showBackground = true)
private fun LoginScreenPreview() {
    PpoPpiTheme {
        LoginScreen()
    }
}
