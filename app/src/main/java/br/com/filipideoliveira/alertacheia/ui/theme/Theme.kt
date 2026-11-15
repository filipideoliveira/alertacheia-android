package br.com.filipideoliveira.alertacheia.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CoresAlertaCheia = lightColorScheme(
    primary = Oceano,
    onPrimary = Color.White,
    primaryContainer = AguaClara,
    onPrimaryContainer = Oceano,
    secondary = OceanoMedio,
    onSecondary = Color.White,
    secondaryContainer = AguaClara,
    onSecondaryContainer = Oceano,
    background = Fundo,
    onBackground = Oceano,
    surface = Color.White,
    onSurface = Oceano,
    onSurfaceVariant = TextoSuave,
    outlineVariant = Borda,
    surfaceContainerHighest = Color.White,
    error = Alerta,
    onError = Color.White,
    errorContainer = AlertaClaro,
    onErrorContainer = Alerta
)

@Composable
fun AlertaCheiaTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = CoresAlertaCheia, content = content)
}
