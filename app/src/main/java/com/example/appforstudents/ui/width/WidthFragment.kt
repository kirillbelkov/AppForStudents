package com.example.appforstudents.ui.width

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.appforstudents.databinding.FragmentWidthBinding

class WidthFragment : Fragment() {

    private var _binding: FragmentWidthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val widthViewModel =
            ViewModelProvider(this).get(WidthViewModel::class.java)

        _binding = FragmentWidthBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Обработка нажатия на кнопку
        binding.btWidth.setOnClickListener {
            val widthInput = binding.tWidth.text.toString()
            if (widthInput.isNotEmpty()) {
                val newWidth = widthInput.toDouble()
                widthViewModel.updateWidth(newWidth)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}