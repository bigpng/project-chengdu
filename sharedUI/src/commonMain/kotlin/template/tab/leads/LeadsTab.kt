package template.tab.leads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object LeadsTab: Tab {
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(index = 3u, title = "Leads", icon = null)
        }

    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize().background(color = Color.Magenta), contentAlignment = Alignment.Center) {
            Text("Leads Tab")
        }
    }
}