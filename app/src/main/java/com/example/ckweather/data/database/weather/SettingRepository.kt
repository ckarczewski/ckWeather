package com.example.ckweather.data.database.weather

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SettingRepository(context: Context): SettingDao {
    private val settingDao = WeatherDB.getInstance(context).SettingDao()

    override suspend fun insertAll(setting: SettingItem) = withContext(Dispatchers.IO) {
        settingDao.insertAll(setting)
    }

    override suspend fun update(setting: SettingItem) {
        settingDao.update(setting)
    }

    override fun getAll(): Flow<List<SettingItem>> {
        return settingDao.getAll()
    }
}