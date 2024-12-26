package ru.egorrublev.a8lab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {

    private val _meters = MutableLiveData<String>()
    val meters: LiveData<String> get() = _meters

    private val _kilometers = MutableLiveData<String>()
    val kilometers: LiveData<String> get() = _kilometers

    private val _centimeters = MutableLiveData<String>()
    val centimeters: LiveData<String> get() = _centimeters

    private val _millimeters = MutableLiveData<String>()
    val millimeters: LiveData<String> get() = _millimeters

    init {
        // Инициализация значений по умолчанию
        _meters.value = ""
        _kilometers.value = ""
        _centimeters.value = ""
        _millimeters.value = ""
    }

    // Метод для конвертации
    fun convertFromMeters(metersInput: String) {
        val meters = metersInput.toDoubleOrNull()
        if (meters != null) {
            _meters.value = meters.toString()
            _kilometers.value = (meters / 1000).toString()
            _centimeters.value = (meters * 100).toString()
            _millimeters.value = (meters * 1000).toString()
        }
    }

    fun convertFromKilometers(kilometersInput: String) {
        val kilometers = kilometersInput.toDoubleOrNull()
        if (kilometers != null) {
            _meters.value = (kilometers * 1000).toString()
            _kilometers.value = kilometers.toString()
            _centimeters.value = (kilometers * 100000).toString()
            _millimeters.value = (kilometers * 1000000).toString()
        }
    }

    fun convertFromCentimeters(centimetersInput: String) {
        val centimeters = centimetersInput.toDoubleOrNull()
        if (centimeters != null) {
            _meters.value = (centimeters / 100).toString()
            _kilometers.value = (centimeters / 100000).toString()
            _centimeters.value = centimeters.toString()
            _millimeters.value = (centimeters * 10).toString()
        }
    }

    fun convertFromMillimeters(millimetersInput: String) {
        val millimeters = millimetersInput.toDoubleOrNull()
        if (millimeters != null) {
            _meters.value = (millimeters / 1000).toString()
            _kilometers.value = (millimeters / 1000000).toString()
            _centimeters.value = (millimeters / 10).toString()
            _millimeters.value = millimeters.toString()
        }
    }

    // Метод для очистки значений
    fun clearConversion() {
        _meters.value = ""
        _kilometers.value = ""
        _centimeters.value = ""
        _millimeters.value = ""
    }
}
