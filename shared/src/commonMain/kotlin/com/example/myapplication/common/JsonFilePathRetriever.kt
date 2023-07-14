package com.example.myapplication.common

interface JsonFilePathRetriever {

    fun getPath(fileName: String): String
}