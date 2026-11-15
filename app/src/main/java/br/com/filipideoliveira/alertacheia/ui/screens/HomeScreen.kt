package br.com.filipideoliveira.alertacheia.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.filipideoliveira.alertacheia.data.AbrigoRepository
import br.com.filipideoliveira.alertacheia.model.Categoria
import br.com.filipideoliveira.alertacheia.ui.components.AbrigoListItem
import br.com.filipideoliveira.alertacheia.ui.components.CategoriaSelector
import br.com.filipideoliveira.alertacheia.ui.components.RotaSeguraSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val repository = remember { AbrigoRepository() }

    // Estado do filtro: null significa "Todas".
    var categoriaSelecionada by remember { mutableStateOf<Categoria?>(null) }

    // A tela só pede ao repositório. Nenhum filtro é feito aqui.
    val abrigosVisiveis = repository.filtrar(categoriaSelecionada)
    val destaques = repository.destaques()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("AlertaCheia", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                RotaSeguraSection(
                    abrigos = destaques,
                    regioesEmAlerta = repository.regioesEmAlerta()
                )
            }

            item {
                Text(
                    "Do que você precisa?",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                CategoriaSelector(
                    categorias = Categoria.entries,
                    categoriaSelecionada = categoriaSelecionada,
                    onCategoriaSelecionada = { categoriaSelecionada = it }
                )
            }

            item {
                Text(
                    "${categoriaSelecionada?.label ?: "Todos os abrigos"} (${abrigosVisiveis.size})",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            if (abrigosVisiveis.isEmpty()) {
                item { Text("Nenhum abrigo com esse atendimento") }
            } else {
                items(abrigosVisiveis) { abrigo -> AbrigoListItem(abrigo = abrigo) }
            }
        }
    }
}
