package com.example.todo_compose_mvvm.ui.screens.splash

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo_compose_mvvm.R
import com.example.todo_compose_mvvm.ui.theme.LOGO_SIZE
import com.example.todo_compose_mvvm.ui.theme.TODOCOMPOSEMVVMTheme

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.
                size(LOGO_SIZE),
            painter = painterResource(
                id = getLogo()
            ),
            contentDescription = stringResource(id = R.string.todo_logo)
        )
    }
}

@Composable
fun getLogo(): Int {
    return if (isSystemInDarkTheme()) {
        R.drawable.logo_dark
     } else {
         R.drawable.logo_light
    }
}

@Composable
@Preview
private fun SplashScreenPreview(){
    SplashScreen()
}

@Composable
@Preview
private fun SplashScreenPreview2(){
    TODOCOMPOSEMVVMTheme(darkTheme = true) {
        SplashScreen()
    }
}