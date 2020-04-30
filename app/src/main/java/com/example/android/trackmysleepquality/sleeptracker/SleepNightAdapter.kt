package com.example.android.trackmysleepquality.sleeptracker


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding



class SleepNightAdapter(val clickListener: SleepNightClickListner)
    : ListAdapter<SleepNight,SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    //ListAdapter will take care of this by Default
//    var data = listOf<SleepNight>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(getItem(position)!!,clickListener)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor( val binding: ListItemSleepNightBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SleepNight, clickListener: SleepNightClickListner) {
            binding.sleep = item
            binding.clickListner = clickListener
            binding.executePendingBindings()
        }



        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemSleepNightBinding
                        .inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

    class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>()
    {
        override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {

            return oldItem.nightId == newItem.nightId
        }

        override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {

            return oldItem == newItem
        }

    }

    class SleepNightClickListner(val clickListener: (sleepId: Long) -> Unit)
    {
        fun onClick(night: SleepNight)
        {
            clickListener(night.nightId)
        }
    }
}


