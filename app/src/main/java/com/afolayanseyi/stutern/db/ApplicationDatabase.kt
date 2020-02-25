package com.afolayanseyi.stutern.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.afolayanseyi.stutern.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao


    companion object {
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    ApplicationDatabase::class.java,
                    "our-database")
                    .build()
            }
            return INSTANCE as ApplicationDatabase
        }

    }
}