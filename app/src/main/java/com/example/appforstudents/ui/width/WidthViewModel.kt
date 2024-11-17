package com.example.appforstudents.ui.width

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appforstudents.logic.classes.RoadData.RoadData

class WidthViewModel : ViewModel() {
    private val roadData: RoadData = RoadData.getInstance()

    // LiveData для ширины
    private val _width = MutableLiveData<Double>().apply { value = roadData.width }
    val width: LiveData<Double> get() = _width

    // Метод для обновления ширины
    fun updateWidth(newWidth: Double) {
        roadData.width = newWidth
        _width.value = newWidth
    }
}