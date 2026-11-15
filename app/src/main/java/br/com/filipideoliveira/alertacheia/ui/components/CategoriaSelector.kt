package br.com.filipideoliveira.alertacheia.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import br.com.filipideoliveira.alertacheia.model.Categoria

// Componente 2: a fileira de chips do filtro.
// Ele não filtra nada: só mostra qual está ativo e avisa a tela do toque.
@Composable
fun CategoriaSelector(
    categorias: List<Categoria>,
    categoriaSelecionada: Categoria?,
    onCategoriaSelecionada: (Categoria?) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            Chip(
                texto = "Todas",
                selecionado = categoriaSelecionada == null,
                onClick = { onCategoriaSelecionada(null) }
            )
        }
        items(categorias) { categoria ->
            Chip(
                texto = categoria.label,
                selecionado = categoriaSelecionada == categoria,
                onClick = { onCategoriaSelecionada(categoria) }
            )
        }
    }
}

// Selecionado fica azul cheio; os outros, brancos com borda fina.
@Composable
private fun Chip(texto: String, selecionado: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = selecionado,
        onClick = onClick,
        label = { Text(texto) },
        shape = RoundedCornerShape(50),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.surface,
            labelColor = MaterialTheme.colorScheme.primary,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = selecionado,
            borderColor = MaterialTheme.colorScheme.outlineVariant
        )
    )
}
