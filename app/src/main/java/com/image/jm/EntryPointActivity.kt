package com.image.jm

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent

import android.os.Bundle
import com.image.jm.screens.main.MainScreen
import com.image.jm.theme.ResourceObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPointActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResourceObject.ImageTheme {
                MainScreen()
            }
        }
    }
}