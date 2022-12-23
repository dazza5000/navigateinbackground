package com.example.navigateinbackgroundfragmentviewmodel.ui.main

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import com.example.navigateinbackgroundfragmentviewmodel.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val actionToNavigateTo = mutableStateOf(Uri.EMPTY)
    val deeplink = mutableStateOf(Uri.EMPTY)

    fun updateDeeplink(uri: Uri) {
        actionToNavigateTo.value = Uri.parse("fooapp://register")
    }

    fun loadNextScreen() {
        actionToNavigateTo.value = Uri.parse("fooapp://register")
        viewModelScope.launch {
            delay(200L)
            Log.d("darran", "setting deeplink to ${actionToNavigateTo.value}")
            deeplink.value = actionToNavigateTo.value
        }
    }

}
