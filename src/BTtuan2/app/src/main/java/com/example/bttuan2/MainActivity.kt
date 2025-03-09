package com.example.bttuan2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val ageid = findViewById<EditText>(R.id.edtTuoi)
        val nameid = findViewById<EditText>(R.id.edtHoTen)
        val kt = findViewById<Button>(R.id.btnKiemTra)
        val kq = findViewById<EditText>(R.id.edtkq)

        kt.setOnClickListener {
            val age = ageid.text.toString().toInt()
            if (age > 65) {
                kq.setText("Người Già")
            }
            else if (age >= 6 && age <= 65) {
                kq.setText("Người Lớn")
            }
            else if(age >= 2 && age <=6){
                kq.setText("Trẻ Em")
            }
            else if(age < 2 && age > 0){
                kq.setText("Em Bé")
            }
            else{
                kq.setText("Không phải con người")
            }
        }
    }
}