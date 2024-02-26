package com.example.androidtask

import android.app.Application
import androidx.room.Room
import com.example.androidtask.database.ProfileDatabase

class MyApp:Application() {
    companion object {
         var database: ProfileDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
         database = Room.databaseBuilder(
            applicationContext,
            ProfileDatabase::class.java,
            "ProfileEntity"
        ).build()
    }
}