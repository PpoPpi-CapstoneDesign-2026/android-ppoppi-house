package com.ppoppi.house.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ppoppi.house.ui.diagnosis.select.SelectActivity
import com.ppoppi.house.ui.main.navigation.HOME
import com.ppoppi.house.ui.main.navigation.MainBottomNavigationBar
import com.ppoppi.house.ui.main.navigation.NavigationGraph
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PpoPpiTheme {
                val navController = rememberNavController()

                Scaffold(
                    containerColor = White,
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        MainBottomNavigationBar(
                            navController = navController,
                        )
                    },
                ) { innerPadding ->

                    NavigationGraph(
                        navigateToDiagnosis = {
                            val intent = SelectActivity.newIntent(this)
                            startActivity(intent)
                        },
                        startRoute = intent.getStringExtra(EXTRA_START_ROUTE) ?: HOME,
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

    companion object {
        private const val EXTRA_START_ROUTE = "EXTRA_START_ROUTE"

        fun newIntent(
            context: Context,
            startRoute: String? = null,
        ): Intent =
            Intent(context, MainActivity::class.java).apply {
                startRoute?.let { putExtra(EXTRA_START_ROUTE, it) }
            }
    }
}
