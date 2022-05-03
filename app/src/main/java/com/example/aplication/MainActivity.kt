package com.example.aplication
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.example.aplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.beginButton.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }

    fun onClickStart(view: View){
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return true
    }
}
//:)