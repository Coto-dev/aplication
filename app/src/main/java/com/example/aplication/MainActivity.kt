package com.example.aplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.example.aplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var blindingClass:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        blindingClass = ActivityMainBinding.inflate(layoutInflater)
//        blindingClass - это основной контейнер где хранятся все эелементы с MainActivity
//        пример работы blindingClass.beginButton.width = 100
          setContentView(blindingClass.root)
    }
    fun OnClickStart(view: View){ // функция привязана к кнопке с id beginButton
        //val beginButton = findViewById<Button>(R.id.beginButton)
        setContentView(R.layout.activity_second)
    }
}
//:)