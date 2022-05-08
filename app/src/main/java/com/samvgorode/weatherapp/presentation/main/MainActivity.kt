package com.samvgorode.weatherapp.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.samvgorode.weatherapp.databinding.ActivityMainBinding
import com.samvgorode.weatherapp.presentation.history.HistoryActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            viewModel = this@MainActivity.viewModel
            openHistory = {
                hideKeyboard()
                startActivity(Intent(this@MainActivity, HistoryActivity::class.java))
            }
            setContentView(root)
        }
    }

    private fun hideKeyboard() = binding?.root?.run {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}