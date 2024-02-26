package com.example.androidtask.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androidtask.database.ProfileEntity
import com.example.androidtask.database.ProfileRepository
import com.example.androidtask.model.Profile
import com.example.androidtask.ui.theme.AndroidTaskTheme
import com.example.androidtask.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController,viewModel: ProfileViewModel) {
    val creditCards by viewModel.creditCards.observeAsState(emptyList())
    val data by viewModel.data.observeAsState()



    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val ctx = LocalContext.current
                val repo = ProfileRepository(context = ctx)

                var textName by remember {
                    mutableStateOf(TextFieldValue(""))
                }
                var textEmail by remember {
                    mutableStateOf(TextFieldValue(""))
                }
                var textMobile by remember {
                    mutableStateOf(TextFieldValue(""))
                }
                var textGender by remember {
                    mutableStateOf(TextFieldValue(""))
                }

                TextField(
                    value = textName,
                    onValueChange = {
                        textName = it
                    },
                    label = { Text(text = "Name") },
                    placeholder = { Text(text = "Enter Name") },
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = textEmail,
                    onValueChange = {
                        textEmail = it
                    },
                    label = { Text(text = "Email") },
                    placeholder = { Text(text = "Enter Email") },
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = textMobile,
                    onValueChange = {
                        textMobile = it
                    },
                    label = { Text(text = "Mobile") },
                    placeholder = { Text(text = "Enter Mobile No") },
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = textGender,
                    onValueChange = {
                        textGender = it
                    },
                    label = { Text(text = "Gender") },
                    placeholder = { Text(text = "Enter Gender") },
                )
                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = {
                    viewModel.createProfile(Profile(textName.text,textEmail.text,textMobile.text,textGender.text))
//                    viewModel.insertToRoom(Profile(textName.text,textEmail.text,textMobile.text,textGender.text))
                    repo.insetMovie(
                        ProfileEntity(id=0,
                            textName.text,textEmail.text,textMobile.text,textGender.text)
                    )
                    },
                    enabled = true,
                    border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                    shape = MaterialTheme.shapes.medium,

                    ) {
                    Text(text = "Add", color = Color.White)
                }
            }
        }
    }

}
