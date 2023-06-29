package com.example.ckweather.data.database.weather

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.ckweather.helpers.helpers

@Entity(tableName = "setting_table")
data class SettingItem(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    var temperatureUnit: helpers.TempUnits = helpers.TempUnits.C
)
