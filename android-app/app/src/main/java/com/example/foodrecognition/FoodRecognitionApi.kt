package com.example.foodrecognition

import android.graphics.Bitmap
import android.util.Base64
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class FoodRecognitionApi {

    private val client = OkHttpClient()

    suspend fun recognizeFoodComponents(bitmap: Bitmap): List<String> = withContext(Dispatchers.IO) {
        val base64Image = bitmapToBase64(bitmap)
        val apiUrl = "https://api.example.com/food-recognition" // Replace with actual API URL
        val apiKey = "YOUR_API_KEY" // Replace with your API key

        val json = JSONObject()
        json.put("image", base64Image)

        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(apiUrl)
            .addHeader("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

        val response = client.newCall(request).execute()
        if (!response.isSuccessful) {
            throw Exception("API call failed with code: ${response.code}")
        }

        val responseBody = response.body?.string() ?: throw Exception("Empty response body")
        val responseJson = JSONObject(responseBody)
        // Parse the response JSON to extract food components list
        val components = mutableListOf<String>()
        val items = responseJson.optJSONArray("components")
        if (items != null) {
            for (i in 0 until items.length()) {
                components.add(items.getString(i))
            }
        }
        components
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
}
