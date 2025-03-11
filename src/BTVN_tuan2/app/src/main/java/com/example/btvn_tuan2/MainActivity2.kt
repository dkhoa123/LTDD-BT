package com.example.btvn_tuan2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.mutableIntListOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    lateinit var customAdapter: CustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var list = mutableListOf<OutData>()
        list.add(OutData(R.drawable.lukhachvenduong,"Lữ Khách Ven Đường","Chữa Lành"))
        list.add(OutData(R.drawable.airoicungsebinhyen,"Ai Rồi Cũng Sẽ Bình Yên","Chữa Lành"))
        list.add(OutData(R.drawable.lennhatchuyendoi,"Lén Nhặt Chuyện Đời","Chữa Lành"))
        list.add(OutData(R.drawable.daucoradivansecuoi,"Dẫu Có Ra Đi Vẫn Sẽ Cười","Chữa Lành"))

        customAdapter = CustomAdapter(this, list)
        //khai báo biến dẫn đến lvSach
        val lvsach = findViewById<ListView>(R.id.lvSach)
        lvsach.adapter = customAdapter

        val rehome = findViewById<ImageButton>(R.id.rehome)
        rehome.setOnClickListener{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
}