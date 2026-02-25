package org.kmp.template

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.kmp.template.theme.AppTheme
import org.kmp.template.screen.home.HomeScreen


@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    Navigator(HomeScreen()) { navigator ->
        SlideTransition(navigator)
    }
}
