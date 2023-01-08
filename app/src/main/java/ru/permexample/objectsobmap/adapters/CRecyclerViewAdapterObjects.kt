package ru.permexample.objectsobmap.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.permexample.objectsobmap.databinding.RecyclerviewobjectsItemBinding
import ru.permexample.objectsobmap.model.CObject

class CRecyclerViewAdapterObjects
    (
    private val items                               : MutableList<CObject>,
    private val OnItemClickListener                 : (Int, CObject) -> Unit,
    private val OnItemRemoveListener                : (Int, CObject) -> Unit
    ) : RecyclerView.Adapter<CRecyclerViewAdapterObjects.CViewHolderObject>()
{
    inner class CViewHolderObject
        (
        private val binding                         : RecyclerviewobjectsItemBinding,
        private val OnItemClickListener             : (Int, CObject) -> Unit,
        private val OnItemRemoveListener            : (Int, CObject) -> Unit
        )                                           : RecyclerView.ViewHolder(binding.root)
    // Вспомогательный класс, который говорит о том, как нужно выводить информацию из элемента данных в запись списка в интерфейсе польз-ля
    {
        private lateinit var item : CObject

        init{
            // Обработка клика на все поля элемента кроме кнопки корзины
            binding.linearLayoutObject.setOnClickListener {
                OnItemClickListener(items.indexOf(item),item)
            }
            // Обработка клика на кнопку с корзиной
            binding.buttonRemove.setOnClickListener {
                OnItemRemoveListener(items.indexOf(item),item)
            }
        }

        fun bind(newItem : CObject){
            item = newItem
            binding.textViewName.text = newItem.name
            binding.textViewDescription.text = newItem.period
            binding.textViewPrice.text = newItem.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CViewHolderObject {
        val binding = RecyclerviewobjectsItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return CViewHolderObject(
            binding,
            OnItemClickListener,
            OnItemRemoveListener)
    }

    override fun onBindViewHolder(holder: CViewHolderObject, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}