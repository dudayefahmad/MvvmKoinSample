package com.example.mvvmkoinsample.presentation.launchcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.example.mvvmkoinsample.R
import com.example.mvvmkoinsample.data.State
import com.example.mvvmkoinsample.presentation.goals.GoalActivity
import com.example.mvvmkoinsample.util.delayInDefault
import com.example.mvvmkoinsample.util.showErrorSnackbar
import com.example.mvvmkoinsample.util.showSnackbar
import kotlinx.android.synthetic.main.activity_launch_code.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.anko.intentFor
import org.koin.android.viewmodel.ext.android.viewModel

class LaunchCodeActivity : AppCompatActivity() {

    companion object {
        const val LAUNCH_CODE_MAX_LENGTH = 6
    }

    private var delayingJob: Job? = null
    private val viewModel by viewModel<LaunchCodeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_code)

        startObservingData()
        setUpView()
    }

    private fun startObservingData() {
        viewModel.launchCodeValidationState.observe(this) {
            when (it) {
                is State.Loading -> {
                    edt_launch_code.isEnabled = false
                    btn_validation.isEnabled = false
                }
                is State.Error -> {
                    edt_launch_code.isEnabled = true
                    btn_validation.isEnabled = true
                    showErrorSnackbar(cl_root, R.string.generic_error)
                }
                is State.Success -> {
                    edt_launch_code.isEnabled = true
                    btn_validation.isEnabled = true
                    showSnackbar(cl_root, R.string.success)
                    navigationToListGoal()
                }
            }
        }
    }

    private fun setUpView() {
        btn_validation.setOnClickListener {
            var value_launch_code = edt_launch_code.text.toString()
            if (value_launch_code.length == LAUNCH_CODE_MAX_LENGTH) {
                delayingJob = lifecycleScope.launch {
                    delayInDefault(500)
                    viewModel.validate(value_launch_code)
                }
            } else {
                if (delayingJob?.isCancelled == false) {
                    delayingJob?.cancel()
                }
                showErrorSnackbar(cl_root, R.string.minimal_input)
            }
        }
    }

    private fun navigationToListGoal() {
        lifecycleScope.launchWhenCreated {
            delayInDefault(500)

            startActivity(
                intentFor<GoalActivity>().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        }
    }
}
