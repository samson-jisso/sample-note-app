package com.example.samnotes.features.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.samnotes.features.data.local.entity.NoteEntity
import com.example.samnotes.presentation.camera_view.backend.data.db.PictureDao
import com.example.samnotes.presentation.camera_view.backend.data.db.PicturesEntity

@Database(
    entities = [NoteEntity::class, PicturesEntity::class],
    version = 2,
    exportSchema = false
)
abstract class NoteDatabase:RoomDatabase() {
    abstract val dao: NoteDao
    abstract val pictureDao: PictureDao
    companion object {
        const val databaseName = "NoteItems"
    }
}