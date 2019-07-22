package com.hariskurbardovic.sixt.map.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hariskurbardovic.sixt.database.models.Car
import com.hariskurbardovic.sixt.databinding.RecyclerItemBinding
import com.hariskurbardovic.sixt.map.fragments.ListFragment

/**
 * Adapter for the [RecyclerView] in [ListFragment].
 */
class ListAdapter : androidx.recyclerview.widget.ListAdapter<
        Car, ListAdapter.ViewHolder>(CarDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = getItem(position)
        holder.apply {
            bind(car)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class ViewHolder(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Car) {
            binding.apply {
                car = item
                executePendingBindings()
            }
        }
    }
}

private class CarDiffCallback : DiffUtil.ItemCallback<Car>() {

    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}