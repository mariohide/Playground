package code.mario.playground

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
data object Main

@Serializable
data object Buttons

@Serializable
data object Status

@Serializable
data object IntrinsicSize

@Serializable
data object SystemPadding

@Serializable
data object I18n

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    doDarkTheme: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Main,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300) // 动画时长，单位毫秒
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
    ) {
        composable<Main> {
            MainScreen(
                onNavToButtons = { navController.navigate(Buttons) },
                onNavToStatus = { navController.navigate(Status) },
                onNavToIntrinsicSize = { navController.navigate(IntrinsicSize) },
                onNavToSystemPadding = { navController.navigate(SystemPadding) },
                onNavToI18n = { navController.navigate(I18n) },
                doDarkTheme = doDarkTheme
            )
        }
        composable<Buttons> {
            ButtonsScreen()
        }
        composable<Status> {
            StatusBarScreen()
        }
        composable<IntrinsicSize> {
            IntrinsicSizeScreen()
        }
        composable<SystemPadding> {
            SystemPaddingScreen()
        }
        composable<I18n> {
            I18nScreen()
        }
    }
}
