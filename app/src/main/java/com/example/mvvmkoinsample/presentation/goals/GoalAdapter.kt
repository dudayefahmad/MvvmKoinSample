package com.example.mvvmkoinsample.presentation.goals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmkoinsample.R
import com.example.mvvmkoinsample.data.remote.model.response.Goals
import com.example.mvvmkoinsample.util.loadImageUrl
import kotlinx.android.synthetic.main.item_goal.view.*

class GoalAdapter : RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    var goals: List<Goals> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var clickListener: (Goals) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        return GoalViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_goal, parent, false))
    }

    override fun getItemCount(): Int = goals.size

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(goals[position])
    }

    inner class GoalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(goals: Goals) {
            itemView.tv_name.text = goals.name
            itemView.img_icon.loadImageUrl(goals.icon)
            itemView.setOnClickListener {
                clickListener(goals)
            }
        }
    }

}