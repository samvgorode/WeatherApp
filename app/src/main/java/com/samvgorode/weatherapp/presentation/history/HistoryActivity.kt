package com.samvgorode.weatherapp.presentation.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.samvgorode.weatherapp.databinding.ActivityHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null

    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater).apply {
            viewModel = this@HistoryActivity.viewModel
            goBack = { super.onBackPressed() }
            setContentView(root)
        }
    }
}