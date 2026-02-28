package template.screen.search

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.adamglin.PhosphorIcons
import com.adamglin.phosphoricons.Bold
import com.adamglin.phosphoricons.Fill
import com.adamglin.phosphoricons.Thin
import com.adamglin.phosphoricons.bold.CaretLeft
import com.adamglin.phosphoricons.fill.MagnifyingGlass
import com.adamglin.phosphoricons.thin.X
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.zinc.chengdu.theme.LocalExtraColors
import template.data.NetworkApi
import template.data.RickMortyCharacters

object SearchModal : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewmodel = koinScreenModel<SearchViewModel>()

        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp).padding(top = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navigator.pop() }) {
                    Icon(imageVector = PhosphorIcons.Bold.CaretLeft, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.width(6.dp))
                OutlinedTextField(
                    value = viewmodel.searchQuery,
                    onValueChange = { viewmodel.onQueryChange(it) },
                    placeholder = { Text("Search") }, singleLine = true, maxLines = 1,
                    shape = RoundedCornerShape(24.dp),
                    trailingIcon = {
                        Row(modifier = Modifier.padding(end = 10.dp).width(60.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                            Box(modifier= Modifier.size(25.dp), contentAlignment = Alignment.Center) {
                                androidx.compose.animation.AnimatedVisibility(visible = viewmodel.searchQuery.length > 3, enter = fadeIn(), exit = fadeOut()) {
                                    IconButton(onClick = { viewmodel.onQueryChange("") }, ) {
                                        Icon(imageVector = PhosphorIcons.Thin.X, contentDescription = "Clear text")
                                    }
                                }
                            }

                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = PhosphorIcons.Fill.MagnifyingGlass,
                                    contentDescription = "Search",
                                    tint = LocalExtraColors.current.selectNavColor
                                )
                            }
                        }

                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
                    keyboardActions = KeyboardActions(onGo = {}),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        focusedContainerColor = MaterialTheme.colorScheme.primaryContainer
                    )

                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)) {
                items(items = viewmodel.searchResults, key = { it.id }) { character ->
                    CharacterItem(character)
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: RickMortyCharacters) {
    Text(text = character.name)
    Text(text = character.species)
}


class SearchViewModel(private val api: NetworkApi) : ScreenModel {
    var searchQuery by mutableStateOf("")
        private set

    var searchResults by mutableStateOf<List<RickMortyCharacters>>(emptyList())
        private set

    private var searchJob: Job? = null

    fun onQueryChange(newQuery: String) {
        searchQuery = newQuery
        searchJob?.cancel()
        screenModelScope.launch {
            if (newQuery.length > 3) {
                searchJob = screenModelScope.launch {
                    delay(500)
                    searchResults = api.getCharacters(newQuery)
                }
            } else {
                searchResults = emptyList()
            }
        }
    }

    override fun onDispose() {
        super.onDispose()
    }
}