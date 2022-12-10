package com.example.samnotes.presentation.camera_view.backend.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface PictureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(picturesEntity: PictureEntity?)


    @Transaction
    @Query("SELECT * FROM noteentity WHERE noteId = :noteId")
    fun getNotePicture(noteId: Int): NoteWithPictures

    @Query("SELECT pictureId FROM PICTUREENTITY WHERE pictureAddress = :pictureAddress")
    fun getPictureId(pictureAddress:String?):Int

    @Update
    suspend fun updateNotePicture(picturesEntity: PictureEntity?)



}