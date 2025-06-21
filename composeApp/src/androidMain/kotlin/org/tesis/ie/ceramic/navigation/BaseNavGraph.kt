package org.tesis.ie.ceramic.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface BaseNavGraph {

    fun build(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    )

}