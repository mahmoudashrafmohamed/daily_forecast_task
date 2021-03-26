package com.mahmoud_ashraf.dailyforecastapplication.daily_forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud_ashraf.dailyforecastapplication.databinding.WeatherItemViewBinding
import com.mahmoud_ashraf.dailyforecastapplication.utils.toReadableDateTime
import com.mahmoud_ashraf.domain.models.DailyWeather

class WeatherInfoAdapter :
    ListAdapter<DailyWeather, WeatherInfoAdapter.WeatherViewHolder>(WeatherDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            WeatherItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) =
        holder.bind(getItem(position))


    class WeatherViewHolder(
        private val binding: WeatherItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            data: DailyWeather
        ) {
            binding.tvDate.text = data.date?.toString()?.toReadableDateTime()
            binding.tvTemp.text = data.temp.toString()
        }
    }

    class WeatherDiffCallback : DiffUtil.ItemCallback<DailyWeather>() {
        override fun areItemsTheSame(
            oldItem: DailyWeather,
            newItem: DailyWeather
        ): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(
            oldItem: DailyWeather,
            newItem: DailyWeather
        ): Boolean {
            return oldItem == newItem
        }


    }
}