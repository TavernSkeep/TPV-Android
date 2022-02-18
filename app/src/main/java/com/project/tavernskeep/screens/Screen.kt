package com.project.tavernskeep.screens

sealed class Screen(val route: String, val arguments: String) {
    object Home: Screen(route = "home", arguments = "")
    //object Detail: Screen(route = "detail", arguments = "/{id}")
}