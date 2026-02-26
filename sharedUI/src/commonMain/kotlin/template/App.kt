package template

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Duotone
import com.adamglin.phosphoricons.Fill
import com.adamglin.phosphoricons.duotone.AddressBook
import com.adamglin.phosphoricons.duotone.ChatsTeardrop
import com.adamglin.phosphoricons.duotone.HouseSimple
import com.adamglin.phosphoricons.duotone.Play
import com.adamglin.phosphoricons.duotone.Storefront
import com.adamglin.phosphoricons.duotone.UserFocus
import com.adamglin.phosphoricons.fill.AddressBook
import com.adamglin.phosphoricons.fill.ChatsTeardrop
import com.adamglin.phosphoricons.fill.HouseSimple
import com.adamglin.phosphoricons.fill.Play
import com.adamglin.phosphoricons.fill.Plus
import com.adamglin.phosphoricons.fill.Storefront
import com.adamglin.phosphoricons.fill.UserFocus
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.zinc.chengdu.theme.AppTheme
import org.zinc.chengdu.theme.LocalExtraColors
import template.data.NetworkApi
import template.screen.search.SearchViewModel
import template.tab.chat.ChatTab
import template.tab.home.HomeTab
import template.tab.post.PostTab
import template.tab.shop.ShopTab
import template.tab.leads.LeadsTab
import template.tab.notifs.NotifsTab
import template.tab.profile.ProfileTab
import template.theme.component.TopBar


@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    initKoin()
    Navigator(screen = BottomTabContainer) { navigator ->
        SlideTransition(navigator)
    }
}

object BottomTabContainer: Screen {
    @Composable
    override fun Content() {
        TabNavigator(HomeTab) {
            Scaffold(
                contentWindowInsets = WindowInsets(0, 0, 0, 0), topBar = { TopBar() },
                bottomBar = {
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        tonalElevation = 0.dp,
                        modifier = Modifier.navigationBarsPadding().height(60.dp)
                    ) {
                        TabNavigationItem(
                            HomeTab, PhosphorIcons.Duotone.HouseSimple, PhosphorIcons.Fill.HouseSimple
                        )
                        TabNavigationItem(LeadsTab, PhosphorIcons.Duotone.Play, PhosphorIcons.Fill.Play)
                        TabNavigationItem(PostTab, PhosphorIcons.Fill.Plus, PhosphorIcons.Fill.Plus)
                        TabNavigationItem(
                            ChatTab, PhosphorIcons.Duotone.AddressBook, PhosphorIcons.Fill.AddressBook
                        )
                        TabNavigationItem(ProfileTab, PhosphorIcons.Duotone.UserFocus, PhosphorIcons.Fill.UserFocus)
                    }
                }) { innerPadding ->
                Box(
                    modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()).fillMaxSize()
                        .clip(RoundedCornerShape(bottomStart = 21.dp, bottomEnd = 20.dp))
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    AnimatedContent(
                        targetState = LocalTabNavigator.current.current, transitionSpec = {
                            fadeIn(animationSpec = tween(220)) togetherWith fadeOut(
                                animationSpec = tween(
                                    120
                                )
                            )
                        }, modifier = Modifier
                    ) { targetTab ->
                        targetTab.Content()
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab, icon: ImageVector, selectedIcon: ImageVector) {
    val tabNavigator = LocalTabNavigator.current
    val selected = tabNavigator.current == tab
    val scale by animateFloatAsState(if (selected) 1.17f else 1f)

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = {
            Text(
                text = tab.options.title,
                fontSize = 8.sp,
                lineHeight = 8.sp,
                modifier = Modifier.scale(scale)
            )
        },
        icon = {
            if (selected) {
                Icon(
                    imageVector = selectedIcon,
                    contentDescription = tab.options.title,
                    modifier = Modifier.size(30.dp).scale(scale)
                )
            } else {
                Icon(
                    imageVector = icon,
                    contentDescription = tab.options.title,
                    modifier = Modifier.size(30.dp).scale(scale)
                )
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = LocalExtraColors.current.selectNavColor,
            indicatorColor = Color.Transparent
        )
    )
}

val myModule = module {
    single { SearchViewModel(NetworkApi()) }
}

fun initKoin() {
    try {
        startKoin {
            modules(myModule)
        }
    } catch (e: Exception) {}
}