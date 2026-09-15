package com.phoneai.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoneai.assistant.network.SocketManager
import com.phoneai.assistant.ui.theme.PhoneAIAssistantTheme

class MainActivity : ComponentActivity() {
    private lateinit var socketManager: SocketManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        socketManager = SocketManager()
        socketManager.connect("http://10.0.2.2:5000") // For emulator

        setContent {
            PhoneAIAssistantTheme {
                MainScreen(socketManager)
            }
        }
    }

    override fun onDestroy() {
        socketManager.disconnect()
        super.onDestroy()
    }
}

@Composable
fun MainScreen(socketManager: SocketManager) {
    var isConnected by remember { mutableStateOf(false) }
    var alarmTime by remember { mutableStateOf("") }
    var alarmLabel by remember { mutableStateOf("") }
    var alarms by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(Unit) {
        socketManager.onConnectListener = {
            isConnected = true
        }
        socketManager.onDisconnectListener = {
            isConnected = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📱 Phone AI Assistant") },
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Connection Status
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                backgroundColor = if (isConnected) MaterialTheme.colors.primary else MaterialTheme.colors.error
            ) {
                Text(
                    text = if (isConnected) "🟢 Connected" else "🔴 Disconnected",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onPrimary
                )
            }

            // Alarm Input
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "⏰ Set Alarm",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    TextField(
                        value = alarmTime,
                        onValueChange = { alarmTime = it },
                        label = { Text("Time (HH:MM)") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    TextField(
                        value = alarmLabel,
                        onValueChange = { alarmLabel = it },
                        label = { Text("Alarm Label") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )
                    Button(
                        onClick = {
                            if (alarmTime.isNotEmpty() && alarmLabel.isNotEmpty()) {
                                socketManager.setAlarm(alarmTime, alarmLabel)
                                alarms = alarms + "$alarmTime - $alarmLabel"
                                alarmTime = ""
                                alarmLabel = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Set Alarm")
                    }
                }
            }

            // Alarms List
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "📋 Active Alarms",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    if (alarms.isEmpty()) {
                        Text("No alarms set yet")
                    } else {
                        alarms.forEach { alarm ->
                            Text(
                                text = alarm,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
