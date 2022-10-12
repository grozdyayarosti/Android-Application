package ru.permexample.objectsobmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class CActivityMain : AppCompatActivity()
{
    val obj = CObject("It's name","It's description")

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cmain)

        val button : Button = findViewById(R.id.button1)
        val editTextInput : EditText = findViewById(R.id.editTextInput)
        val textView2 : TextView = findViewById(R.id.textView2)
        button.setOnClickListener{
            obj.comments.add(editTextInput.text.toString())

//            Toast.makeText(applicationContext, "Result: ${obj.comments.size}!", Toast.LENGTH_SHORT).show()
            textView2.text = "" + obj.comments.size
        }
    }
}