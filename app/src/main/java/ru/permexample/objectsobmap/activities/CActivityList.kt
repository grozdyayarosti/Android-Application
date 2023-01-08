package ru.permexample.objectsobmap.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.permexample.objectsobmap.adapters.CRecyclerViewAdapterObjects
import ru.permexample.objectsobmap.databinding.ActivityClistBinding
import ru.permexample.objectsobmap.model.CObject

class CActivityList : AppCompatActivity() {
    private lateinit var resultLauncherObjectEdit             : ActivityResultLauncher<Intent> //  Обработчик для редактирования
    private lateinit var resultLauncherObjectAdd              : ActivityResultLauncher<Intent> //  Обработчик для добавления
    private lateinit var resultLauncherPermission             : ActivityResultLauncher<Array<String>> //  Обработчик для разрешений

    private lateinit var binding                              : ActivityClistBinding

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
        binding.rvObjects.adapter = CRecyclerViewAdapterObjects(
            items,
            { index,item ->
                // Вызов активности с информацией по объекту, передача туда параметров
                val intent = Intent(this, CActivityObjectInfo::class.java) // Переход на активность - CActivityObjectInfo, соответственно
                intent.putExtra("KEY_INDEX", index)
                intent.putExtra("KEY_OBJECT_NAME", item.name) // Отправляем данные на дочернюю активность
                resultLauncherObjectEdit.launch(intent)
            },
            { index, _ ->
                items.removeAt(index)
                binding.rvObjects.adapter?.notifyItemRemoved(index)
            }
        )

        /***********************************************************************************************
         * Обработка события завершения активности с информацией по объекту в режиме редактирования    *
         * существующего объекта.                                                                      *
         ***********************************************************************************************/
        resultLauncherObjectEdit            = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //Получение параметров из дочерней активности
                val data                    : Intent?
                                            = result.data
                val name                    = data?.getStringExtra("KEY_OBJECT_NAME") ?: ""
                val index                   = data?.getIntExtra("KEY_INDEX", -1)?: -1
                //Если какие-то проблемы с данными, выводи сообщение или как-то обрабатываем.
                if (index<0)
                {

                    Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
                }
                else
                {
                    //Если всё нормально,
                    //актуализируем объект в списке данных.
                    items[index].name       = name
                    //Говорим адаптеру списка, что конкретная единица данных обновлена,
                    //нужно повторно её вывести на экран.
                    (binding.rvObjects.adapter as CRecyclerViewAdapterObjects).notifyItemChanged(index)
                }
            }
        }
        /***********************************************************************************************
         * Обработка события завершения активности с информацией по объекту в режиме добавления        *
         * нового объекта.                                                                             *
         ***********************************************************************************************/
        resultLauncherObjectAdd             = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //Получение параметров из дочерней активности
                val data                    : Intent?
                                            = result.data
                val name                    = data?.getStringExtra("KEY_OBJECT_NAME") ?: ""
                val index                   = data?.getIntExtra("KEY_INDEX", -1)?: -1
                //Добавляем объект в списке данных.
                items.add(CObject(name,"test","test_price"))
                //Говорим адаптеру списка, что конкретная единица данных обновлена,
                //нужно повторно её вывести на экран.
                (binding.rvObjects.adapter as CRecyclerViewAdapterObjects).notifyItemInserted(items.size - 1)
            }
        }
        binding.fab.setOnClickListener{
            // Переход на активность - CActivityObjectInfo, соответственно
            val intent = Intent(this, CActivityObjectInfo::class.java)
            resultLauncherObjectAdd.launch(intent)
        }

        val pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        with (pref.edit()) {
            putString("KEY_INT", "123 456 789 0")
            putInt("KEY_STRING", 123)
            apply()
        }
    }
    // Метод, создающий список с отказанными разр-ми и открывает диалог польз-ля с ними
    private fun checkAndRequestPermissions(){
        val allPermissions                  = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        // Заполнение списка permissionsToAsk отказанными разрешениями
        val permissionsToAsk                = allPermissions
            .filter {
                return@filter ContextCompat.checkSelfPermission(
                    this,
                    it
                ) == PackageManager.PERMISSION_DENIED
            }
        if (permissionsToAsk.isNotEmpty())
            resultLauncherPermission.launch(
                permissionsToAsk.toTypedArray()
            )
    }
    // Метод, создающий обработчик и реагирующий на решение польз-ля о принятии/отказе разр-я
    private fun registerPermissionListener(){
        resultLauncherPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()){
            if(it[Manifest.permission.CAMERA] == true){
                Toast.makeText(this,"Camera run", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"Permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }
}