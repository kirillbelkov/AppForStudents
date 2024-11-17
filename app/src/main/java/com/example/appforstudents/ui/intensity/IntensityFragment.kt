package com.example.appforstudents.ui.intensity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appforstudents.TimerActivity

import com.example.appforstudents.databinding.FragmentIntensityBinding

class IntensityFragment : Fragment() {

    private var _binding: FragmentIntensityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val intensityViewModel =
            ViewModelProvider(this).get(IntensityViewModel::class.java)

        _binding = FragmentIntensityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Обработка нажатия на кнопку
        binding.btGo.setOnClickListener {
            val inputValue = binding.etTime.text.toString()
            if (inputValue.isNotEmpty()) {
                val intent = Intent(requireActivity(), TimerActivity::class.java)
                intent.putExtra("time", inputValue)
                startActivity(intent) // Запускаем TimerActivity
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
