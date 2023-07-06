package com.example.aula1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.aula1.ui.theme.Aula1Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {

    // Define uma variável privada _uiState do tipo MutableStateFlow<UiState>
    // que contém o estado da interface do usuário
    private val _uiState = MutableStateFlow(UiState())

    // Define uma propriedade pública uiState do tipo StateFlow<UiState>
    // que retorna o estado atual da interface do usuário
    val uiState: StateFlow<UiState> = _uiState

    // Define um método público add que recebe uma transação como parâmetro
    // Adiciona a transação à lista de transações existente no estado da interface do usuário
    // e atualiza o estado da interface do usuário com a nova lista de transações
    fun add(transaction: String) {
        val transactions = _uiState.value.transactions.toMutableList()
        transactions.add(transaction)
        _uiState.value = UiState(transactions = transactions)
    }

    // Define uma classe interna UiState que representa o estado da interface do usuário
    // Ela contém uma lista de transações
    data class UiState(
        val transactions : List<String> = emptyList()
    )
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aula1Theme {
                Column() {
                    Welcome()
                    Transactions()
                }
            }
        }
    }
}

@Composable
fun Transactions(viewModel: MyViewModel = viewModel()) {
    // Coleta o estado da interface do usuário do MyViewModel
    val uiState by viewModel.uiState.collectAsState()
    // Define uma layout de coluna
    Column {
        // Define um LazyColumn com um modificador para preencher a largura disponível e o peso
        LazyColumn(
            modifier = Modifier.padding(16.dp).weight(1f).fillMaxWidth(),
            // Define o preenchimento do conteúdo
            contentPadding = PaddingValues(8.dp)
        ) {
            // Loop através das transações no estado da interface do usuário
            items(uiState.transactions.size) { index->
                // Define uma composição de transação com a transação atual
                Transaction(uiState.transactions[index])

            }
        }
        // Define um botão com um ouvinte de clique que adiciona uma nova transação ao MyViewModel
        Button(onClick = { viewModel.add("New Transaction") }) {
            Text(text = "Add new Transaction")
        }
    }
}

@Composable
private fun Transaction(transaction: String) {
    // Define um Card com uma elevação de 8dp e um modifier para preencher o width
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Define uma Row layout
        Row {
            // Define um Text com o texto da transação, um modifier para preencher o width, estilo de fonte grande e negrito
            Text(
                text = transaction,
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }


    }
}


@Composable
fun Welcome() {
    // Cria uma linha vertical com o texto "Welcome back, Gabriel Santos" e um ícone de lixeira
    Row(modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
        ) {
        
        Text(
            text = "Welcome back, \nGabriel Santos",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Clear Transactions",
            tint = MaterialTheme.colorScheme.primary
        )
        
    }
}

private val transactionsDummy = listOf <String>(
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
    "Gasolina", "Aluguel", "Estudos", "Café", "Academia",
)


