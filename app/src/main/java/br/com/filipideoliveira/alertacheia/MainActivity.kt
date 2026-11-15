package br.com.filipideoliveira.alertacheia

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.filipideoliveira.alertacheia.ui.screens.HomeScreen
import br.com.filipideoliveira.alertacheia.ui.theme.AlertaCheiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ícones claros na barra de status, sobre o azul da TopAppBar.
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))
        setContent {
            AlertaCheiaTheme {
                HomeScreen()
            }
        }
    }
}
