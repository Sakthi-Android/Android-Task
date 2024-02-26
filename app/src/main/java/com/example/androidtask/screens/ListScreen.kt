package com.example.androidtask.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidtask.database.ProfileEntity
import com.example.androidtask.database.ProfileRepository
import com.example.androidtask.ui.theme.AndroidTaskTheme
import com.example.androidtask.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController,viewModel: ProfileViewModel) {
    val profileData by viewModel.studentDetailsList.collectAsState()
    var selectedIndex by remember {
        mutableStateOf(-1)
    }
    val openDialog = remember { mutableStateOf(false) }
    val singleData = MutableStateFlow(ProfileEntity())

    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val ctx = LocalContext.current
            val repo = ProfileRepository(context = ctx)
            LaunchedEffect(Unit) {
                viewModel.getAllData(repo)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
            ) {
                if (profileData.isEmpty()) {
                    // Show loading indicator or placeholder
                    Text(text = "Loading...")
                } else {
                    // Display the list of credit cards
                    LazyColumn {
                        items(profileData) { data ->
                            Text(text = data.email,
                                modifier = Modifier.selectable(selected = data.id == selectedIndex,
                                onClick = {
                                    if (selectedIndex != data.id){Log.e("Clicked","Clicked")
                                        openDialog.value=true
                                        singleData.value=data
                                    selectedIndex = data.id} else {
                                        openDialog.value=false
                                        selectedIndex = -1
                                        Log.e("Clicked","Not Clicked")}}))
                            Text(text = data.mobile)
                            Text(text = data.name)
                            Text(text = data.gender)
                            Divider()
                        }
                    }
                }
            }
            if (openDialog.value) {
                var textName by remember {
                    mutableStateOf(TextFieldValue(""))
                }
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Edit Name")
                    },
                    text = {
                        TextField(
                            value = textName,
                            onValueChange = {
                                textName = it
                            },
                            label = { Text(text = "Name") },
                            placeholder = { Text(text = singleData.value.name) },
                        )

                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                                repo.updateTitle(singleData.value.id,textName.text)
                            }
                        ) {
                            Text("Confirm")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                            }
                        ) {
                            Text("Dismiss")
                        }
                    }
                )
            }
        }

    }

}

