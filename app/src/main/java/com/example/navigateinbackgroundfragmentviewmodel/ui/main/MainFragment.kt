package com.example.navigateinbackgroundfragmentviewmodel.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.navigateinbackground.ui.theme.NavigateInBackgroundTheme
import com.example.navigateinbackgroundfragmentviewmodel.R
import kotlinx.coroutines.delay

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("darran", "Loading next screen")
        viewModel.loadNextScreen()
        return ComposeView(requireContext()).apply {

            setContent {
                MainContent(findNavController())
            }

        }
    }

    @Composable
    private fun MainContent(navController: NavController) {

        val nextScreen = viewModel.deeplink.value

        LaunchedEffect(key1 = nextScreen, block = {
            if (viewModel.deeplink.value != Uri.EMPTY) {
                Log.d("darran", "navigating to next screen")
                navController.navigate(nextScreen)
                viewModel.deeplink.value = Uri.EMPTY
            }
        })

        // A surface container using the 'background' color from the theme
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text("Android", color = Color.Magenta)
            Button(onClick = {
                navController.navigate(viewModel.actionToNavigateTo.value)
            }) {
                Text("click this")
            }
        }
    }


}
