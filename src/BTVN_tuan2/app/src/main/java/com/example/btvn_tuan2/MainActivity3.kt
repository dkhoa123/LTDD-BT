package com.example.btvn_tuan2

import android.content.Intent
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

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        val imageview = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load(R.drawable.sadboy)
            .apply(RequestOptions.circleCropTransform()) // Đổi ảnh thành hình tròn
            .into(imageview)
        val myname = findViewById<TextView>(R.id.name)
        myname.text ="PHẠM KHOA"
        val myhome = findViewById<TextView>(R.id.name1)
        myhome.text ="ĐỒNG THÁP, VIỆT NAM"
        val btnBack = findViewById<Button>(R.id.btnBack);
        val btnNote: Button = findViewById(R.id.btnNote)
        btnBack.setOnClickListener{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        };
        btnNote.setOnClickListener {
            Toast.makeText(this, "Bạn đã nhấn Ghi chú!", Toast.LENGTH_SHORT).show()
        }
    }
}