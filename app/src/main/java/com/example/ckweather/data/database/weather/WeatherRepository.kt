package com.example.ckweather.data.database.weather

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class WeatherRepository(context: Context): WeatherDao{
    private val weatherDao = WeatherDB.getInstance(context).WeatherDao()

    override suspend fun insertAll(weather: List<WeatherItem>) = withContext(Dispatchers.IO){
        weatherDao.insertAll(weather)
    }

    override suspend fun delete(weather: List<WeatherItem>) = withContext(Dispatchers.IO){
        weatherDao.delete(weather)
    }

    override suspend fun update(weather: WeatherItem) = withContext(Dispatchers.IO){
        weatherDao.update(weather)
    }

    override fun getAll(): Flow<List<WeatherItem>> {
        return weatherDao.getAll()
    }

    override suspend fun dropDatabase() = withContext(Dispatchers.IO){
        weatherDao.dropDatabase()
    }
}