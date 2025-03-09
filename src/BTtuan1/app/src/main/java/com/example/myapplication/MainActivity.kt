package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val imageview = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load(R.drawable.sadboy) // có thể đổi thành URL
            .apply(RequestOptions.circleCropTransform()) // Đổi ảnh thành hình tròn
            .into(imageview)
        val myname = findViewById<TextView>(R.id.name)
        myname.text ="PHẠM KHOA"
        val myhome = findViewById<TextView>(R.id.name1)
        myhome.text ="ĐỒNG THÁP, VIỆT NAM"
        val btnBack = findViewById<Button>(R.id.btnBack);
        val btnNote: Button = findViewById(R.id.btnNote)
        btnBack.setOnClickListener{finish()}; // Kết thúc Activity khi nhấn nút
        btnNote.setOnClickListener {
            Toast.makeText(this, "Bạn đã nhấn Ghi chú!", Toast.LENGTH_SHORT).show()
        }

    }
}