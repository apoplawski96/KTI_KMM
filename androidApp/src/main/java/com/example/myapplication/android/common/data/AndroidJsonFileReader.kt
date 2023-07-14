package com.example.myapplication.android.common.data

import android.content.Context
import com.example.myapplication.common.JsonFileReader

class AndroidJsonFileReader(private val context: Context) : JsonFileReader {

    override fun readJsonFile(filePath: String): String {
        return context.loadJSONFromAssets(filePath)
    }

    private fun Context.loadJSONFromAssets(fileName: String): String {
        return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }
}

