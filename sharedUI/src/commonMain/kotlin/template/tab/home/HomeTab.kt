package template.tab.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import coil3.compose.AsyncImage
import kmp_template.sharedui.generated.resources.Res
import kmp_template.sharedui.generated.resources.search_bar
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import template.data.Media
import template.data.NetApi
import kotlin.random.Random

object HomeTab : Tab {
    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(index = 1u, title = "Home", icon = null)
        }

    @Composable
    override fun Content() {
        val vm = koinScreenModel<HomeViewModel>()
        val res = vm.res
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 8.dp, horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = res, key = { it.id }) { i ->
                val randomHeight = remember(i) { Random.nextInt(175, 250).dp }
                when(i) {
                    is Media.Photo -> {
                        HomeItemImage( height = randomHeight, item = i, linkUrl = i.url,
                            imageUrl = i.src.small, user = i.photographer,
                            labelProvider = { i.alt }
                        )
                    }
                    is Media.Video -> {}
                }


            }
        }
    }
}

@Composable
fun <T> HomeItemImage(height: Dp, item: T, linkUrl: String, imageUrl: String, user: String, labelProvider: (T) -> String) {
    Box(
        modifier = Modifier.fillMaxWidth().height(height)
            .clip(RoundedCornerShape(16.dp)).background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = labelProvider(item),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(Res.drawable.search_bar)
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = labelProvider(item), style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(1.dp),
                        maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 1.em
                    )
                    Spacer(modifier = Modifier.height(0.5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        AsyncImage(model = "https://rickandmortyapi.com/api/character/avatar/17.jpeg", contentDescription = null,
                            modifier = Modifier.size(20.dp).clip(RoundedCornerShape(10.dp)).padding(horizontal = 1.dp),
                            contentScale = ContentScale.Crop, placeholder = painterResource(Res.drawable.search_bar))
                        Text(text = user, style = MaterialTheme.typography.labelSmall,  lineHeight = 1.em,
                            color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }

    }
}

class HomeViewModel(private val api: NetApi) : ScreenModel {
    var res by mutableStateOf<List<Media>>(emptyList())
        private set

    init {
        screenModelScope.launch {
            res = api.getCollection()
        }
    }
}