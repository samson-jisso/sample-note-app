package com.example.samnotes.presentation.camera_view.backend.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picturesEntity: PictureEntity?)


    @Transaction
    @Query("SELECT * FROM noteentity WHERE noteId = :noteId")
    fun getNotePicture(noteId: Int): NoteWithPictures
}