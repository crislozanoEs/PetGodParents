package com.petgodparents.petgodparents.di

import android.content.Context
import androidx.room.Room
import com.petgodparents.petgodparents.data.local.appointment.AppDatabase
import com.petgodparents.petgodparents.data.repository.AppointmentRepository
import com.petgodparents.petgodparents.data.repository.IAppointmentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataDi {

    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()
    }

    @Provides
    fun providesAppointmentRepository(database: AppDatabase): IAppointmentRepository {
        return AppointmentRepository(database)
    }


}