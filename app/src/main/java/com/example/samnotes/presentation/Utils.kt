package com.example.samnotes.presentation

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


val newFormatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
fun getCurrentDate(): String = newFormatter.format(Calendar.getInstance().time)
fun getUriFileName(uri: Uri, context: Context): String? {
    var res: String? = null
    if (uri.scheme.equals("content")) {
        val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
        try {
            if (cursor != null && cursor.moveToFirst()) {
                res = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
                println("resource: $res")
            }
        } finally {
            cursor?.close()
        }
        if (res == null) {
            val resPath = uri.path
            val cut: Int = resPath?.lastIndexOf('/') ?: 0
            if (cut != -1) {
                res = resPath?.substring(cut + 1)
            }
        }
    }
    return res
}
val imageExtensionsArray: Array<String> = arrayOf("jpg","png","gif","tiff","JPG")
fun randomIdNum(): Int {
    return try {
        Random.nextInt(100000, 9999999)
    } catch (e: Exception) {
        Log.e("randomIdNum", "randomIdNum: $e", e)
    }
}
