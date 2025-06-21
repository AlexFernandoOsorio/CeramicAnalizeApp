package org.tesis.ie.ceramic.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.tesis.ie.ceramic.ui.categories.ListCategoriesScreen
import org.tesis.ie.ceramic.ui.home.HomeScreen
import org.tesis.ie.ceramic.ui.home.WelcomeScreen
import org.tesis.ie.ceramic.ui.information.InformationScreen
import org.tesis.ie.ceramic.ui.investigation.InvestigationScreen
import org.tesis.ie.ceramic.ui.search.SearchScreen

object AppNavGraph : BaseNavGraph {

    sealed class Dest(val route: String) {

        data object Root : Dest("/home-root")

        data object Welcome : Dest("/welcome")

        data object Search : Dest("/search")

        data object Home : Dest("/home")

        data object Investigation : Dest("/investigation")

        data object Information : Dest("/information")

        data object ListCategories : Dest("/list-categories")

    }

    override fun build(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {

        navGraphBuilder.navigation(route = Dest.Root.route, startDestination = Dest.Welcome.route) {
            composable(route = Dest.Welcome.route){
                WelcomeScreen(
                    onHomeClick = {
                        navHostController.navigate(route = Dest.Home.route)
                    }
                )
            }

            composable(route = Dest.Search.route){
                SearchScreen(
                    onBackClick = {
                        navHostController.popBackStack()
                    }
                )
            }

            composable(route = Dest.Home.route){
                HomeScreen(
                    onSearchClick = {
                        navHostController.navigate(route = Dest.Search.route)
                    },
                    onInvestigationClick = {
                        navHostController.navigate(route = Dest.Investigation.route)
                    },
                    onInformationClick = {
                        navHostController.navigate(route = Dest.Information.route)
                    },
                    onListCategoriesClick = {
                        navHostController.navigate(route = Dest.ListCategories.route)
                    }
                )
            }

            composable(route = Dest.Investigation.route){
                InvestigationScreen(
                    onBackClick = {
                        navHostController.popBackStack()
                    }
                )
            }

            composable(route = Dest.Information.route){
                InformationScreen(
                    onBackClick = {
                        navHostController.popBackStack()
                    }
                )
            }

            composable(route = Dest.ListCategories.route){
                ListCategoriesScreen(
                    onBackClick = {
                        navHostController.popBackStack()
                    }
                )
            }

        }
    }

}