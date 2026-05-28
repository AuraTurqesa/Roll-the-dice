package com.example.eldaw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eldaw.ui.theme.ElDawTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElDawTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceRoller(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DiceRoller(modifier: Modifier) {
    var diceValue by remember { mutableStateOf(1) }
    var isRolling by remember { mutableStateOf(false) }

    // Cambia el valor del dado cada 10 ms si el botón se ha presionado
    LaunchedEffect(isRolling) {
        if (isRolling) {
            val totalDuration = 250L // Duración total en ms (3 segundos)
            val interval = 10L        // Intervalo de cambio de dado (10 ms)
            val iterations = totalDuration / interval // Número de iteraciones

            for (i in 0 until iterations) {
                diceValue = Random.nextInt(1, 7) // Cambiar el dado a un valor aleatorio entre 1 y 6
                delay(interval)
            }
            isRolling = false // Detiene el cambio después de 3 segundos
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = when (diceValue) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }),
            contentDescription = "Dice"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { isRolling = true },
            modifier = Modifier
                .background(Color.Green, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(text = "Lanzar el dado", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiceRollerWithButton() {
    DiceRoller(modifier = Modifier)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ElDawTheme {
        DiceRoller(modifier = Modifier)
    }
}