package com.example.androidtask.database

import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.room.Query
import com.example.androidtask.model.Profile
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class ProfileRepository(context: Context) {
    private val myExecutor = Executors.newSingleThreadExecutor()
    private val myHandler = Handler(Looper.getMainLooper())
    var db: ProfileDAO = ProfileDatabase.getDatabase(context).profileDao()
    fun insetMovie(profileEntity: ProfileEntity) {
        insertAsyncTask(db,profileEntity)
    }
    suspend fun getAllStudents(): Flow<List<ProfileEntity>> {
        return withContext(IO) {
            db.getAllData()
        }
    }

    private fun insertAsyncTask(db: ProfileDAO,profileEntity: ProfileEntity): Any? {
        myExecutor.execute {
            myHandler.post {
                db.insert(profileEntity)
            }
        }
        return null
    }

    fun updateTitle(id:Int, name:String)
    {
        myExecutor.execute {
            myHandler.post {
                Log.e("Update", name.toString())
                db.updateName(id, name = name)
            }
        }
        }
    }


