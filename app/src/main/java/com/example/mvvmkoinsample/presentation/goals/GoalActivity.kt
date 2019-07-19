package com.example.mvvmkoinsample.presentation.goals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmkoinsample.R
import com.example.mvvmkoinsample.data.State
import com.example.mvvmkoinsample.data.remote.model.response.Goals
import com.example.mvvmkoinsample.util.*
import kotlinx.android.synthetic.main.activity_goal.*
import org.koin.android.viewmodel.ext.android.viewModel

class GoalActivity : AppCompatActivity() {

    private val viewModel by viewModel<GoalViewModel>()
    private val adapter by lazy { GoalAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)
        setUpObservingData()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        rv_goal.layoutManager = GridLayoutManager(this, 2)
        adapter.clickListener = {
            showSnackbar2(cl_root, it.name)
        }
        rv_goal.adapter = adapter
    }

    private fun setUpObservingData() {
        with(viewModel) {
            getGoalListState.observe(this@GoalActivity) {
                when (it) {
                    is State.Success -> {
                        hideLoading()
                        updateList(it.data)
                    }
                    is State.Loading -> showLoading()
                    is State.Error -> {
                        hideLoading()
                        it.throwable.message?.toInt()?.let { it1 -> showErrorSnackbar(cl_root, it1) }
                    }
                }
            }
        }
    }

    private fun updateList(data: List<Goals>) {
        rv_goal.visible()
        adapter.goals = data
        adapter.notifyDataSetChanged()
    }

    private fun hideLoading() {
        progress_view.gone()
        rv_goal.visible()
    }

    private fun showLoading() {
        progress_view.visible()
        rv_goal.gone()
    }

}
