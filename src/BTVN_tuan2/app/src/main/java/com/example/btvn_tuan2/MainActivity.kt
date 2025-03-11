package com.example.btvn_tuan2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.btvn_tuan2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDoi.setOnClickListener {
            showListDialog();
        }
        binding.btnThem.setOnClickListener {
            ThemSach()
        }
        binding.dsSach.setOnClickListener{
            val i = Intent(this,MainActivity2::class.java)
            startActivity(i)
        }
        binding.btnCaNhan.setOnClickListener{
            val i = Intent(this,MainActivity3::class.java)
            startActivity(i)
        }
    }
    var checkBoxCount = 3;
    private fun ThemSach() {
        val newChechBox = CheckBox(this).apply {
            text = "Sách 0$checkBoxCount"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                75 // 50dp ≈ 150 pixel
            ).apply {
                setMargins(35, 10, 45, 20)
            }
            setBackgroundResource(R.drawable.bogoc_mautrang)
        }
        binding.line2.addView((newChechBox))
        checkBoxCount++
    }

    private fun showListDialog() {
        val names = arrayOf("Nguyễn Văn A", "Trần Thị B", "Lê Văn C", "Phạm Minh D")

        AlertDialog.Builder(this)
            .setTitle("Chọn tên nhân viên")
            .setItems(names) { _, which ->
                binding.edtTenNV.setText(names[which])
                Toast.makeText(this,"Bạn chọn ${names[which]}",Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Hủy", null)
            .show()
    }
}