package com.example.androidtask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ProfileEntity::class],
    version = 1
)
abstract class ProfileDatabase : RoomDatabase(){
    abstract fun profileDao(): ProfileDAO
    companion object {
        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        fun getDatabase(
            context: Context
        ): ProfileDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileDatabase::class.java,
                    "ProfileEntity"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
