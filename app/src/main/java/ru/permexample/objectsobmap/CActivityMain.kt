package ru.permexample.objectsobmap

import android.widget.ImageView


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.textfield.TextInputLayout


class CActivityMain : AppCompatActivity()
{
    val obj = CObject("It's name","It's description")

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cmain)

        val button1 : Button = findViewById(R.id.button1)
        val button2 : Button = findViewById(R.id.button2)
        val button3 : Button = findViewById(R.id.button3)
        val button4 : Button = findViewById(R.id.button4)
        val button5 : Button = findViewById(R.id.button5)

        val editTextInput1 : TextInputLayout = findViewById(R.id.filledTextField1)
        val editTextInput2 : TextInputLayout = findViewById(R.id.filledTextField2)
        val textViewOutput : TextView = findViewById(R.id.textView)
        button1.setOnClickListener{
            var v1 = editTextInput1.textAlignment.toString().toDouble()
            var v2 = editTextInput2.textAlignment.toString().toDouble()
            textViewOutput.text="$v2"
        }
    }
}