package com.mahmoud_ashraf.dailyforecastapplication.daily_forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mahmoud_ashraf.dailyforecastapplication.R
import com.mahmoud_ashraf.dailyforecastapplication.base.StateData
import com.mahmoud_ashraf.dailyforecastapplication.databinding.ForecastListFragmentBinding
import com.mahmoud_ashraf.domain.models.DailyWeather
import org.koin.androidx.viewmodel.ext.android.viewModel


class ForecastListFragment : Fragment() {

    private val viewModel: ForecastListViewModel by viewModel()
    private var binding: ForecastListFragmentBinding? = null
    private val adapter by lazy { WeatherInfoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ForecastListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchView()
        setupRecyclerView()
        dailyWeatherObserver()
    }

    private fun setupRecyclerView() {
        binding?.rvForecasts?.adapter = adapter
    }

    private fun setupSearchView() {
        binding?.svMain?.isSubmitButtonEnabled = true
        binding?.svMain?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.getWeatherByCityName(cityName = it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun dailyWeatherObserver() {
        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    StateData.DataStatus.LOADING -> showProgress()
                    StateData.DataStatus.SUCCESS -> handleSuccessState(it)
                    StateData.DataStatus.WARNING -> handleWarningState(it)
                    StateData.DataStatus.ERROR -> handleErrorState()
                }
            }
        }
    }



    private fun showProgress() {
        binding?.progressBar?.isVisible = true
    }

    private fun hideProgress() {
        binding?.progressBar?.isVisible = false
    }

    private fun handleSuccessState(it: StateData<List<DailyWeather>>) {
        hideProgress()
        adapter.submitList(it.data)
    }

    private fun handleWarningState(it: StateData<List<DailyWeather>>) {
        hideProgress()
        adapter.submitList(it.data)
        showSnackBar(getString(R.string.not_accurate_data), false)
    }

    private fun handleErrorState() {
        hideProgress()
        showSnackBar(getString(R.string.something_wrong_happened), true)
    }

    private fun showSnackBar(message: String, enableRetry: Boolean) {
        val snack = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
        if (enableRetry) {
            snack.setAction(getString(R.string.retry)) {
                binding?.svMain?.query?.toString()
                    ?.let { query -> viewModel.getWeatherByCityName(query) }
            }
        }
        snack.show()
    }

}