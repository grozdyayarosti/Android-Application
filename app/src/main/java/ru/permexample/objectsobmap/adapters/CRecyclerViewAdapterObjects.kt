package ru.permexample.objectsobmap.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import ru.permexample.objectsobmap.databinding.RecyclerviewobjectsItemBinding
import ru.permexample.objectsobmap.model.CObject

class CRecyclerViewAdapterObjects(private val items: MutableList<CObject>)
    : RecyclerView.Adapter<CRecyclerViewAdapterObjects.CViewHolderObject>()
{
    class CViewHolderObject(private val binding: RecyclerviewobjectsItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener
    // Вспомогательный класс, который говорит о том, как нужно выводить информацию из элемента данных в запись списка в интерфейсе польз-ля
    {
        private lateinit var item : CObject
        fun bind(newItem : CObject){
            item = newItem
            binding.textViewName.text = newItem.name
            binding.textViewDescription.text = newItem.description
            binding.textViewPrice.text = newItem.price
        }
        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CViewHolderObject {
        val binding = RecyclerviewobjectsItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return CViewHolderObject(binding)
    }

    override fun onBindViewHolder(holder: CViewHolderObject, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}