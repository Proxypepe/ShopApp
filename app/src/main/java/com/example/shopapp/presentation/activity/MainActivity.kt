package com.example.shopapp.presentation.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.datastore.dataStore
import com.example.shopapp.domain.*
import com.example.shopapp.domain.common.AppSettings
import com.example.shopapp.domain.common.AppSettingsSerializer
import com.example.shopapp.domain.common.LoginEvent
import com.example.shopapp.presentation.navigation.AppNavigation
import com.example.shopapp.repository.ShoppingAppApplication
import com.example.shopapp.ui.theme.ShopAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi

val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    private val cartViewModel: CartViewModel by viewModels {
        CartViewModelFactory((application as ShoppingAppApplication).localRepository)
    }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as ShoppingAppApplication).productRepository)
    }

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        FavoriteViewModelFactory(
            (application as ShoppingAppApplication).favoriteLocalRepository,
        )
    }

    private val searchViewModel: SearchViewModel by viewModels {
        SearchViewModelFactory((application as ShoppingAppApplication).productRepository)
    }

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            (application as ShoppingAppApplication).registerRepository,
            (application as ShoppingAppApplication).authRepository,
            dataStore,
        )
    }

    private val detailedViewModel: DetailedViewModel by viewModels {
        DetailedViewModelFactory(
            (application as ShoppingAppApplication).commentRepository,
        )
    }

    private fun initInfoLists() {
        mainViewModel.getRecommendedProduct()
        cartViewModel.initCart()
        favoriteViewModel.getFavorites()
    }

    override fun onStart() {
        super.onStart()
        initInfoLists()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appSettings = dataStore.data.collectAsState(
                initial = AppSettings()
            ).value

            if (appSettings.user_email != "")
                loginViewModel.obtainEvent(
                    LoginEvent.InitUserData
                )

            loginViewModel.obtainEvent(
                LoginEvent.SetTheme(appSettings.isDark)
            )

            ShopAppTheme(isDarkTheme = appSettings.isDark){
                AppNavigation(
                    mainPageViewModel = mainViewModel,
                    favoriteViewModel = favoriteViewModel,
                    cartViewModel = cartViewModel,
                    searchViewModel = searchViewModel,
                    loginViewModel = loginViewModel,
                    detailedViewModel = detailedViewModel
                )
            }
        }
    }
}
