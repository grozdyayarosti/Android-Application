package ru.permexample.objectsobmap.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import ru.permexample.objectsobmap.R

class CActivityLogin : AppCompatActivity() {
    private lateinit var resultLauncher : ActivityResultLauncher<Intent> //  Обработчик

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clogin)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val tests = data?.getStringExtra("MY_KEY_3")
                val x = 123
            }
        }
    }
    fun onButtonClick(view: View)
    {
        val intent = Intent(this, CActivityList::class.java) // Переход на активность - CActivityList, соответственно
//        intent.putExtra("MY_KEY_STRING", "Это тестовая строка")
//        intent.putExtra("MY_KEY_DOUBLE", 123.456) // Отправляем данные на дочернюю активность
        resultLauncher.launch(intent)
    }
}