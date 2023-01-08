package ru.permexample.objectsobmap.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceManager
import ru.permexample.objectsobmap.R
import ru.permexample.objectsobmap.databinding.ActivityCloginBinding

class CActivityLogin : AppCompatActivity() {
    private lateinit var pref                                 : SharedPreferences
    private lateinit var binding                              : ActivityCloginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCloginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получение ссылки на объект и ассоциирование его с файлом
        pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)


        // Чтение ранее сохранённого имени пользователя
        val userName = pref.getString(getString(R.string.KEY_USER_NAME),"")

        if (userName!="")   //Если пользователь уже зарегистрировался.
        {
            //Вызов активности со списком.
            val intent = Intent(this, CActivityList::class.java)
            startActivity(intent)
            finish()
            return
        }
        // Обработкка клика на кнопку "Войти"
        binding.buttonLogin.setOnClickListener {
            onButtonClick()
        }
    }
    private fun onButtonClick()
    {
        val userName = checkLogin("${binding.inputLogin.editText?.text?: ""}",
            "${binding.inputPassword.editText?.text?: ""}")
        if (userName == "") {
            Toast.makeText(this, "Invalid user data", Toast.LENGTH_LONG).show()
            return
        }
        //Сохраняем учётную запись пользователя в файл с настройками приложения.
        with (pref.edit()) {
            putString(getString(R.string.KEY_USER_NAME), userName)
            apply()
        }

        // Вызов активности со списком
        val intent = Intent(this, CActivityList::class.java) // Переход на активность - CActivityList, соответственно
        startActivity(intent)
        finish()
    }
    private fun checkLogin(
        login           : String,
        password        : String
    )                   : String
    {
        if (login == "test@gmail.com" && password=="test123")
            return "test"
        return ""
    }
}