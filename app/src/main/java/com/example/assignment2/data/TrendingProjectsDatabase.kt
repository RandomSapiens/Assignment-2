package com.example.assignment2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

@Database(entities = [TrendingProjects::class], version = 2, exportSchema = false)
abstract class TrendingProjectsDatabase:RoomDatabase() {
    abstract fun trendingProjectsDao():TrendingProjectsDao
    companion object{
        private var INSTANCE:TrendingProjectsDatabase?=null
        fun getDatabase(context: Context):TrendingProjectsDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null)return tempInstance

            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    TrendingProjectsDatabase::class.java,
                    "trending_projects_database"
                ).build()
//                addCallback(ProjectCallback(provider)).
                INSTANCE=instance
                return instance
            }
        }
    }

}


class ProjectCallback(
    private val provider: Provider<TrendingProjectsDao>
) : RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        val p1 =TrendingProjects(houseName = "Shanti-Niketan",
            houseOwner = "Bhola Ram",
            houseAddress ="Jhanda Chawk Saguna More",
            houseSize = "2,3,4 BHK",
            houseRent = "₹ 88.73L - 1.84 Cr"
        )



        val p2 = TrendingProjects(houseName = "Baba Ki Haweli",
            houseOwner = "Diamond Sharma",
            houseAddress ="Echelon Square Sector -32",
            houseSize = "2,3,4 BHK",
            houseRent = "₹ 88.73L - 1 Cr"
        )

        for (i in 1..15) {
            if (i % 2 == 1) provider.get().addTrendingProjects(p1)
            else provider.get().addTrendingProjects(p2)
        }
    }
}
