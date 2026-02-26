package template.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.bold.MagnifyingGlass
import kmp_template.sharedui.generated.resources.Res
import kmp_template.sharedui.generated.resources.trademate_logo
import org.jetbrains.compose.resources.painterResource
import org.zinc.chengdu.theme.LocalExtraColors
import template.screen.search.SearchModal

@Composable
fun TopBar() {
    val navigator = LocalNavigator.currentOrThrow.parent ?: LocalNavigator.current
    Row(
        Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(41.dp)
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(Res.drawable.trademate_logo),
            contentDescription = null,
            Modifier.padding(start = 16.dp).width(83.dp),
            tint = LocalExtraColors.current.headerColor
        )
        IconButton(onClick = { navigator?.push(SearchModal) }, modifier = Modifier.padding(end = 16.dp).height(35.dp)) {
            Icon(
                imageVector = PhosphorIcons.Bold.MagnifyingGlass,
                contentDescription = "Search icon",

                tint = LocalExtraColors.current.headerColor
            )
        }

    }
}