package com.example.androidtask.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface ProfileDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( profileEntity: ProfileEntity)


    @Query("UPDATE ProfileEntity SET name = :name WHERE id = :id")
    fun updateName( id: Int, name: String)

    @Query("SELECT * FROM ProfileEntity")
    fun getAllData(): Flow<List<ProfileEntity>>

}