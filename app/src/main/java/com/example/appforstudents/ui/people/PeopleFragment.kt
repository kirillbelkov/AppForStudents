package com.example.appforstudents.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appforstudents.databinding.FragmentPeopleBinding
import com.example.appforstudents.logic.classes.RoadData.RoadData
import com.example.appforstudents.logic.classes.Transport.Bus
import com.example.appforstudents.logic.classes.Transport.Minibus
import com.example.appforstudents.logic.classes.Transport.Tram

import com.example.appforstudents.logic.classes.Transport.Transport
import com.example.appforstudents.logic.classes.Transport.Trolleybus

class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private lateinit var transportList: List<Transport>
    private lateinit var selectedTransport: Transport

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val peopleViewModel =
            ViewModelProvider(this).get(PeopleViewModel::class.java)

        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Инициализация списка транспорта
        transportList = listOf(
           Bus(),
            Minibus(),
            Trolleybus(),
            Tram(),

        )

        val transportNames = transportList.map { it.name }

        // Настройка адаптера для Spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, transportNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sTransport.adapter = adapter

        // Обработчик выбора в Spinner
        binding.sTransport.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedTransport = transportList[position]
                updateEditTexts()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Ничего не делать
            }
        }

        // Обработка изменения значений в EditText
        binding.tSedPeople.addTextChangedListener {
            validateEditTexts(binding.tSedPeople, binding.numSedPeople, selectedTransport.sedPlaces)
        }

        binding.tSatPeople.addTextChangedListener {
            validateEditTexts(binding.tSatPeople, binding.numStaPeople, selectedTransport.satPlaces)
        }

        binding.bPeople.setOnClickListener{
            saveData()
        }

        return root
    }

    private fun updateEditTexts() {
        binding.numSedPeople.setText(selectedTransport.sedPlaces.toString())
        binding.numStaPeople.setText(selectedTransport.satPlaces.toString())
    }

    private fun validateEditTexts(largeEditText: EditText, smallEditText: EditText, maxValue: Int) {
        val smallValue = smallEditText.text.toString().toIntOrNull() ?: 0
        val largeValue = largeEditText.text.toString().toIntOrNull() ?: 0

       if (largeValue > smallValue) {
           smallEditText.setText(largeValue.toString())
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun saveData() {
        val roadData = RoadData.getInstance()
        roadData.currentSedPlaces =binding.tSedPeople.text.toString().toInt() ?: 0
        roadData.currentSatPlaces = binding.tSatPeople.text.toString().toInt() ?: 0
        roadData.transportType=selectedTransport.name
        roadData.maxSatPlaces=binding.numStaPeople.text.toString().toInt() ?: selectedTransport.satPlaces
        roadData.maxSedPlaces=binding.numSedPeople.text.toString().toInt() ?: selectedTransport.sedPlaces
    }
}


