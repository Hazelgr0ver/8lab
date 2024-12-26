package ru.egorrublev.a8lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    // Ручная привязка элементов
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ConverterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализируем ActivityMainBinding вручную
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Наблюдение за данными из ViewModel
        viewModel.meters.observe(this, Observer { value ->
            if (binding.etMeters.text.toString() != value) {
                binding.etMeters.setText(value)
            }
        })

        viewModel.kilometers.observe(this, Observer { value ->
            if (binding.etKilometers.text.toString() != value) {
                binding.etKilometers.setText(value)
            }
        })

        viewModel.centimeters.observe(this, Observer { value ->
            if (binding.etCentimeters.text.toString() != value) {
                binding.etCentimeters.setText(value)
            }
        })

        viewModel.millimeters.observe(this, Observer { value ->
            if (binding.etMillimeters.text.toString() != value) {
                binding.etMillimeters.setText(value)
            }
        })

        // Слушатели для всех полей ввода
        binding.etMeters.doAfterTextChanged {
            val text = it.toString()
            if (text.isNotEmpty()) {
                viewModel.convertFromMeters(text)
            }
        }

        binding.etKilometers.doAfterTextChanged {
            val text = it.toString()
            if (text.isNotEmpty()) {
                viewModel.convertFromKilometers(text)
            }
        }

        binding.etCentimeters.doAfterTextChanged {
            val text = it.toString()
            if (text.isNotEmpty()) {
                viewModel.convertFromCentimeters(text)
            }
        }

        binding.etMillimeters.doAfterTextChanged {
            val text = it.toString()
            if (text.isNotEmpty()) {
                viewModel.convertFromMillimeters(text)
            }
        }

        // Обработка нажатия кнопки "Очистить"
        binding.btnClear.setOnClickListener {
            clearFields()
        }
    }

    // Класс для привязки элементов вручную
    class ActivityMainBinding(
        val root: View,  // root view of the layout
        val etMeters: EditText,
        val etKilometers: EditText,
        val etCentimeters: EditText,
        val etMillimeters: EditText,
        val btnClear: Button  // добавляем кнопку "Очистить"
    ) {
        companion object {
            // Метод для ручной привязки
            fun inflate(inflater: LayoutInflater): ActivityMainBinding {
                val root = inflater.inflate(R.layout.activity_main, null)
                val etMeters = root.findViewById<EditText>(R.id.etMeters)
                val etKilometers = root.findViewById<EditText>(R.id.etKilometers)
                val etCentimeters = root.findViewById<EditText>(R.id.etCentimeters)
                val etMillimeters = root.findViewById<EditText>(R.id.etMillimeters)
                val btnClear = root.findViewById<Button>(R.id.btnClear)  // Привязываем кнопку

                return ActivityMainBinding(root, etMeters, etKilometers, etCentimeters, etMillimeters, btnClear)
            }
        }
    }


    // Очистка всех полей
    private fun clearFields() {
        binding.etMeters.text.clear()
        binding.etKilometers.text.clear()
        binding.etCentimeters.text.clear()
        binding.etMillimeters.text.clear()

        // Сброс значений в ViewModel
        viewModel.clearConversion()
    }
}
