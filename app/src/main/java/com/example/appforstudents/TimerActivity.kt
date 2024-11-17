package com.example.appforstudents

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.appforstudents.logic.classes.RoadData.RoadData

class TimerActivity : AppCompatActivity() {
    private lateinit var etPersonal: EditText
    private lateinit var etSocial: EditText
    private lateinit var currentTime: TextView
    private lateinit var btAddPersonal: Button
    private lateinit var btAddSocial: Button
    private lateinit var btReducePersonal: Button
    private lateinit var btReduceSocial: Button

    private var personalCount: Int = 0
    private var socialCount: Int = 0
    private var timer: CountDownTimer? = null
    private var timeInput:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_timer)

        etPersonal = findViewById(R.id.etPersonal)
        etSocial = findViewById(R.id.etSocial)
        currentTime = findViewById(R.id.currentTime)
        btAddPersonal = findViewById(R.id.btAddPersonal)
        btAddSocial = findViewById(R.id.btAddSocial)
        btReducePersonal = findViewById(R.id.btReducePersonal)
        btReduceSocial = findViewById(R.id.btReduceSocial)

        etSocial.setText("0")
        etPersonal.setText("0")

        // Получение времени
       timeInput = intent.getStringExtra("time")?.toInt()?:0

        btAddPersonal.setOnClickListener {
            personalCount += 1
            etPersonal.setText(personalCount.toString())
        }

        btAddSocial.setOnClickListener {
            socialCount+= 1
            etSocial.setText(socialCount.toString())
        }

        btReducePersonal.setOnClickListener {
            if (personalCount > 0) {
                personalCount -= 1
                etPersonal.setText(personalCount.toString())
            }
        }

        btReduceSocial.setOnClickListener {
            if (socialCount > 0) {
                socialCount -= 1
                etSocial.setText(socialCount.toString())
            }
        }

        // Запуск таймера
        startTimer()
    }

    private fun startTimer() {
        val totalTime = (timeInput) * 60000L // Переводим минуты в миллисекунды
        timer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60
                currentTime.text = String.format("Осталось: %02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                currentTime.text = "Таймер завершен!"
                saveData()
                finish() // Закрытие активности
            }
        }.start()
    }

    private fun saveData() {
        val roadData = RoadData.getInstance()
        roadData.carIntensive = personalCount
        roadData.busIntensive = socialCount
        roadData.time=intent.getStringExtra("time")?.toInt()?:0
    }
}
