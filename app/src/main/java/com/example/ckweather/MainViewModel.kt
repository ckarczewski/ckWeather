package com.example.ckweather

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.ckweather.data.database.weather.SettingItem
import com.example.ckweather.data.database.weather.SettingRepository
import com.example.ckweather.helpers.helpers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class MainViewModel(app: Application): AndroidViewModel(app) {
    private val settingRepository = SettingRepository(app.applicationContext)

    fun getSetting(): Flow<List<SettingItem>> {
        val savedSettings = settingRepository.getAll()
        runBlocking(Dispatchers.IO) {
            val set = savedSettings.first()
            if (set.isEmpty()){
                val newSettings = SettingItem(temperatureUnit = helpers.TempUnits.C)
                settingRepository.insertAll(newSettings)
            }
        }
        return savedSettings
    }

    fun changeTemperatureUnit(unit: helpers.TempUnits){
        val settingFlow = settingRepository.getAll()
        Log.d("setting flow", "$settingFlow")
        runBlocking(Dispatchers.IO) {
            val setting = settingFlow.first()
            Log.d("setting", "$setting")
            if(setting.isNotEmpty()){
                setting[0].temperatureUnit = unit;
                Log.d("settomg[o]tempunit", "${setting[0]}")
                settingRepository.update(setting[0])
            }
        }
    }
}