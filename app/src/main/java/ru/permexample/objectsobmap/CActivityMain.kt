package ru.permexample.objectsobmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class CActivityMain : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cmain)

        val button : Button = findViewById(R.id.button1)
        button.setOnClickListener{
            Toast.makeText(applicationContext, "Hello!", Toast.LENGTH_SHORT).show()
        }
    }
}