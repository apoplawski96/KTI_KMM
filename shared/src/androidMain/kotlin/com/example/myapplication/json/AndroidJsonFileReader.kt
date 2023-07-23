package com.example.myapplication.json

import android.content.Context
import com.example.myapplication.common.JsonFileReader

class AndroidJsonFileReader(private val context: Context) : JsonFileReader {

    override fun readJsonFile(filePath: String): String = context.loadJSONFromAssets(filePath)

    private fun Context.loadJSONFromAssets(fileName: String): String =
        applicationContext.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
}

