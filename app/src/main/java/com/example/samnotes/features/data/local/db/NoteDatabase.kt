package com.example.samnotes.features.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.samnotes.features.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
abstract class NoteDatabase:RoomDatabase() {
    abstract val dao: NoteDao
    companion object {
        const val databaseName = "NoteItems"
    }
}