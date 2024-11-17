package com.example.appforstudents.logic.classes.RoadData

import com.example.appforstudents.logic.classes.Transport.Bus
import com.example.appforstudents.logic.classes.Transport.Transport

class RoadData ()
{
    var width:Double=0.0
    var time:Int=0
    var carIntensive=0
    var busIntensive=0
    var maxSedPlaces=0
    var maxSatPlaces=0
    var transportType=""
    var currentSedPlaces=0
    var currentSatPlaces=0
    companion object {
        @Volatile
        private var INSTANCE: RoadData? = null

        fun getInstance(): RoadData {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RoadData().also { INSTANCE = it }
            }
        }
    }

    override fun toString(): String {
        return """
            Ширина дороги: $width м
            Время в пути: $time мин
            Интенсивность легковых автомобилей: $carIntensive
            Интенсивность автобусов: $busIntensive
            Максимальное количество сидячих мест: $maxSedPlaces
            Максимальное количество стоячих мест: $maxSatPlaces
            Тип транспорта: $transportType
            Текущее количество сидячих мест: $currentSedPlaces
            Текущее количество стоячих мест: $currentSatPlaces
        """.trimIndent()
    }
}



