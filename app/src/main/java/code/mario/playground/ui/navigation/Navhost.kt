package code.mario.playground.ui.navigation

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
import code.mario.playground.ui.ButtonsScreen
import code.mario.playground.ui.ChipsScreen
import code.mario.playground.ui.I18nScreen
import code.mario.playground.ui.IntrinsicSizeScreen
import code.mario.playground.ui.MainScreen
import code.mario.playground.ui.StatusBarScreen
import code.mario.playground.ui.SystemPaddingScreen
import code.mario.playground.ui.TermsRowScreen
import code.mario.playground.ui.navigation.Route.*
import code.mario.playground.ui.tabrow.TabRowRoute
import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Main : Route()

    @Serializable
    data object Buttons : Route()

    @Serializable
    data object Status : Route()

    @Serializable
    data object IntrinsicSize : Route()

    @Serializable
    data object SystemPadding : Route()

    @Serializable
    data object I18n : Route()

    @Serializable
    data object Terms : Route()

    @Serializable
    data object TabRow : Route()

    @Serializable
    data object Chips : Route()
}

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
            MainScreen(onNavigation = { navController.navigate(it) })
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
        composable<Terms> {
            TermsRowScreen()
        }
        composable<TabRow> {
            TabRowRoute()
        }
        composable<Chips> {
            ChipsScreen()
        }
    }
}
