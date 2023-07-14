package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Composable
fun App() {
    var count by remember {
        mutableStateOf(0)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { count++ }) {
            Text("Count: $count")
        }
    }
}

@Serializable
data class MQuestion(
    val id: Int,
    val question: String,
    val answer: String,
    val difficulty: String,
    @SerialName("topCategory")
    val topCategory: String,
    @SerialName("topCategoryId")
    val topCategoryId: Int,
    @SerialName("subCategory")
    val subCategory: String,
    @SerialName("subCategoryId")
    val subCategoryId: Int
)

//data class MQuestion(
//    val id: Long,
//    val question: String,
//    val answer: String,
//    val topCategory: MTopCategory,
//    val subCategory: MSubCategory,
//)