package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String, val places: List<Place>)

// 使用 @SerializedName 注解将 JSON 字段 formatted_address 和 Kotlin 的字段 address 建立映射关系
data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

// 经纬度信息
data class Location(val lng: String, val lat: String)