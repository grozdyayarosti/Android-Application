package ru.permexample.objectsobmap.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.permexample.objectsobmap.R
import ru.permexample.objectsobmap.adapters.CRecyclerViewAdapterObjects
import ru.permexample.objectsobmap.databinding.ActivityClistBinding
import ru.permexample.objectsobmap.model.CObject

class CActivityList : AppCompatActivity() {
    private lateinit var binding: ActivityClistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Связываем код активности с файлом, описывающим внешний вид активности
        binding = ActivityClistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = mutableListOf<CObject>()
        items.add(CObject("Название курса","Период обучения","Цена"))
        items.add(CObject("Разработка Android приложений","10.06.22-10.12.22","7500"))
        items.add(CObject("Разработка IOS приложений","10.07.22-20.12.22","10000"))
        items.add(CObject("Веб разработка","15.08.22-15.11.22","5000"))
        items.add(CObject("Введение в искусственный инетеллект","1.07.22-1.01.23","10000"))
        items.add(CObject("Разработка игр","15.06.22-10.12.22","8000"))
        items.add(CObject("Анализ данных","20.10.22-20.02.23","9000"))
        items.add(CObject("DevOps","20.10.22-20.02.23","5000"))
        items.add(CObject("Базы данных","20.11.22-20.12.22","4000"))
        items.add(CObject("Системное администрирование","20.11.22-20.02.23","5000"))

        binding.rvObjects.layoutManager = LinearLayoutManager(this)
        binding.rvObjects.adapter = CRecyclerViewAdapterObjects(items)
    }
}