package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

/*
{
    "status":"ok",
    "query":"北京",
    "places":[
        {"name":"北京市","location":{"lat":39.9041999,"lng":116.4073963},"formatted_address":"中国北京市"},
        {"name":"北京西站","location":{"lat":39.89491,"lng":116.322056},"formatted_address":"中国 北京市 丰台区 莲花池东路118号"},
        {"name":"北京南站","location":{"lat":39.865195,"lng":116.378545},"formatted_address":"中国 北京市 丰台区 永外大街车站路12号"},
        {"name":"北京站(地铁站)","location":{"lat":39.904983,"lng":116.427287},"formatted_address":"中国 北京市 东城区 2号线"}
    ]
}
*/

// 数据模型与数据的关系：
// 数据模型描述了数据的结构，即数据是长什么样子的，数据包含哪些成分，数据由哪几个部分组成
// 数据是一个或多个实体，是具体存在的对象，这类实体具备一定的结构，将这些结构抽离（抽象）出来，便是这类数据的数据模型
// 只要我有数据模型，我就能知道所有数据的组成，只要我有数据，我就能根据数据抽象出对应的数据模型，这就是数据和数据模型的关系

// 这个类用于描述接口返回的数据
data class PlaceResponse(val status: String, val places: List<Place>)

// 使用 @SerializedName 注解将 JSON 字段 formatted_address 和 Kotlin 的字段 address 建立映射关系
// 这个类用于描述一个地方的地理信息：比如经纬度，比如具体的地理位置
data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)

// 经纬度信息
data class Location(val lng: String, val lat: String)