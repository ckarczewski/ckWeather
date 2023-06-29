package com.example.ckweather

import android.app.Application
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
        runBlocking(Dispatchers.IO) {
            val setting = settingFlow.first()
            if(setting.isNotEmpty()){
                setting[0].temperatureUnit = unit;
                settingRepository.update(setting[0])
            }
        }
    }
}