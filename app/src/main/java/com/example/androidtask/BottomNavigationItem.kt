package com.example.androidtask

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androidtask.screens.Screens

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "List",
                icon = Icons.Filled.List,
                route = Screens.List.route
            ),
            BottomNavigationItem(
                label = "Add",
                icon = Icons.Filled.Add,
                route = Screens.Add.route
            ),
            BottomNavigationItem(
                label = "Map",
                icon = Icons.Filled.LocationOn,
                route = Screens.Map.route
            ),
        )
    }
}