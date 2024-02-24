package com.example.todo_compose_mvvm.ui.screens.splash

import android.window.SplashScreen
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo_compose_mvvm.R
import com.example.todo_compose_mvvm.ui.theme.LOGO_SIZE
import com.example.todo_compose_mvvm.ui.theme.TODOCOMPOSEMVVMTheme
import com.example.todo_compose_mvvm.util.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit,
) {
    var startAnimation by remember { mutableStateOf(false) }
    val offsetState by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 100.dp, label = "",
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    val alphaState by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, label = "",
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(SPLASH_SCREEN_DELAY)
        navigateToListScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.
                size(LOGO_SIZE)
                .offset(y = offsetState)
                .alpha(alphaState)
            ,
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
    SplashScreen({})
}

@Composable
@Preview
private fun SplashScreenPreview2(){
    TODOCOMPOSEMVVMTheme(darkTheme = true) {
        SplashScreen({})
    }
}