package com.example.samnotes.presentation.camera_view.backend.di

import com.example.samnotes.features.data.local.db.NoteDatabase
import com.example.samnotes.presentation.camera_view.backend.domain.repository.CameraRepository
import com.example.samnotes.presentation.camera_view.backend.domain.repository.CameraRepositoryImp
import com.example.samnotes.presentation.camera_view.backend.domain.use_cases.CameraUseCases
import com.example.samnotes.presentation.camera_view.backend.domain.use_cases.GetNotePicture
import com.example.samnotes.presentation.camera_view.backend.domain.use_cases.InsertPicture
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
//@EntryPoint
@InstallIn(SingletonComponent::class)
object CameraModule {

    @Provides
    @Singleton
    fun providesCameraRepository(db: NoteDatabase): CameraRepository {
        return CameraRepositoryImp(
            db.pictureDao
        )
    }

    @Provides
    @Singleton
    fun providesCameraUseCases(repository: CameraRepository): CameraUseCases {
        return CameraUseCases(
            insertPicture = InsertPicture(repository),
            getNotePicture = GetNotePicture(repository)
        )
    }

}