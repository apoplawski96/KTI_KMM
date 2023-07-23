package com.example.myapplication.json

import android.content.Context
import com.example.myapplication.common.JsonFilePathRetriever

class AndroidJsonFilePathRetriever(private val context: Context) : JsonFilePathRetriever {

    override fun getPath(fileName: String): String = context.assets.open(fileName).toString()
}
