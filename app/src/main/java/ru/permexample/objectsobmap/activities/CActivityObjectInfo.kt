package ru.permexample.objectsobmap.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import ru.permexample.objectsobmap.databinding.ActivityCobjectInfoBinding

class CActivityObjectInfo : AppCompatActivity()
{
    private lateinit var binding : ActivityCobjectInfoBinding
    private var index : Int = -1
    private lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCobjectInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Принимаем данные на дочернюю активность
        intent.extras?.let{                                  // Берём параметры
            index = it.getInt("KEY_INDEX")              // Пытаемся их прочитать
            // Вывод наименования объекта на экран
            binding.inputName.editText!!.setText(it.getString("KEY_OBJECT_NAME")?: "")
        }?:run{
//            Toast.makeText(this,"Параметры недоступны!", Toast.LENGTH_SHORT).show()
            index = -1
            binding.inputName.editText!!.setText("")
        }

        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    // Back is pressed... Finishing the activity
                    val myIntent = Intent()
                    myIntent.putExtra("KEY_INDEX", index)
                    //Здесь в явном виде используется преобразование к строке,
                    //потому что без него результат команды считается чем-то более сложным (Parsable, Serializable),
                    //и не отображается методом getStingExtra в родительской форме.
                    myIntent.putExtra("KEY_OBJECT_NAME", "${binding.inputName.editText?.text ?: ""}")
                    setResult(RESULT_OK, myIntent)
                    finish()
                }
            }
        )
    }
}