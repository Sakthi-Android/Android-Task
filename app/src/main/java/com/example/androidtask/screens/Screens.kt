package com.example.androidtask.screens

sealed class Screens(val route : String) {
    object List : Screens("list_route")
    object Add : Screens("add_route")
    object Map : Screens("map_route")
}