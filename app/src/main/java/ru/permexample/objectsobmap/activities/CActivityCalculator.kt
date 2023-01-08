package ru.permexample.objectsobmap.activities


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import ru.permexample.objectsobmap.R
import ru.permexample.objectsobmap.databinding.ActivityCalculatorBinding


class CActivityCalculator : AppCompatActivity()
{
    private lateinit var binding: ActivityCalculatorBinding

//    val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
//        when {
//            granted -> {
//                requestCamera.launch() // доступ к камере разрешен,  открываем камеру
//            }
//            !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
//                // доступ к камере запрещен, пользователь поставил галочку Don't ask again.
//            }
//            else -> {
//                // доступ к камере запрещен
//            }
//        }
//    }
//    val requestCamera = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
//        // используем bitmap
//    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val tests = intent.extras?.getString("MY_KEY_STRING")
//        val testd = intent.extras?.getDouble("MY_KEY_DOUBLE")

        intent.extras?.let{
            val tests = it.getString("MY_KEY_STRING")
            val testd = it.getDouble("MY_KEY_DOUBLE") // Принимаем данные на дочернюю активность
        }?:run{
            Toast.makeText(this,"Параметр недоступен!", Toast.LENGTH_SHORT).show()
        }

        binding.button1.setOnClickListener{
            plus()
        }
        binding.button2.setOnClickListener{
            val v1 = binding.filledTextField1.editText?.text.toString().toDouble()
            val v2 = binding.filledTextField2.editText?.text.toString().toDouble()
            binding.textView.text=String.format(getString(R.string.Output), v1-v2)
        }
        binding.button3.setOnClickListener{
            val v1 = binding.filledTextField1.editText?.text.toString().toDouble()
            val v2 = binding.filledTextField2.editText?.text.toString().toDouble()
            binding.textView.text=String.format(getString(R.string.Output), v1*v2)
        }
        binding.button4.setOnClickListener{
            val v1 = binding.filledTextField1.editText?.text.toString().toDouble()
            val v2 = binding.filledTextField2.editText?.text.toString().toDouble()
            binding.textView.text=String.format(getString(R.string.Output), v1/v2)
        }
        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    // Back is pressed... Finishing the activity
                    val myIntent = Intent()
                    myIntent.putExtra("MY_KEY_4", "Это строка, которая возвращается при нажатии кнопки назад")
                    setResult(RESULT_OK, myIntent)
                    finish()
                }
            }
        )
    }
    private fun plus()
    {
        val v1 = binding.filledTextField1.editText?.text.toString().toDouble()
        val v2 = binding.filledTextField2.editText?.text.toString().toDouble()
        binding.textView.text=String.format(getString(R.string.Output), v1 + v2)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.miAbout -> {
                Toast.makeText(this,"Button \"about application\"is clicked",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.miPhoto -> {
                true // убрать надо
//                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
//                    // объясняем пользователю, почему нам необходимо данное разрешение
//                } else {
//                    permission.launch(Manifest.permission.CAMERA)
//                }
            }
            R.id.miClose -> {
                val myIntent = Intent()
                myIntent.putExtra("MY_KEY_3", "Это строка, которая возвращается обратно") // Передаём строку в родительскую активность
                setResult(RESULT_OK, myIntent) // Возвращает в переменную ResultCode - значение RESULT_OK
                finish() // Отменяет текущую активность, в нашем случае - закрывает
                true
            }
            R.id.miAdd -> {
                plus()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}