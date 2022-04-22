package com.example.aplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.example.aplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var blindingClass:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        blindingClass = ActivityMainBinding.inflate(layoutInflater)
//        blindingClass - это основной контейнер где хранятся все эелементы с MainActivity
//        пример работы blindingClass.beginButton.width = 100
          setContentView(R.layout.activity_main)
    }
    fun OnClickStart(view: View){ // функция привязана к кнопке с id beginButton
        //val beginButton = findViewById<Button>(R.id.beginButton)
        setContentView(R.layout.activity_second)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}
//:)