package com.ms.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ms.shoppinglist.data.db.entities.ShoppingItem
import com.ms.shoppinglist.ui.ShoppingViewModel

class ShoppingItemAdapter(
    var list: List<ShoppingItem>,
    private val viewModel:ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>(){

    inner class ShoppingItemViewHolder(view: View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.shopping_item,parent,false)
        return ShoppingItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val current=list[position]
        val itemName=holder.itemView.findViewById<TextView>(R.id.tv_item_name)
        val itemAmount=holder.itemView.findViewById<TextView>(R.id.tv_item_amount)
        val imgPlus=holder.itemView.findViewById<ImageView>(R.id.image_plus)
        val imgMinus=holder.itemView.findViewById<ImageView>(R.id.image_minus)
        val imgDelete=holder.itemView.findViewById<ImageView>(R.id.image_delete)

        itemName.text=current.name
        itemAmount.text=current.amount.toString()


        imgDelete.setOnClickListener {
            viewModel.delete(current)
        }

        imgPlus.setOnClickListener {
            current.amount++
            viewModel.insert(current)
        }
        imgMinus.setOnClickListener {
            if(current.amount > 0){
                current.amount--
                viewModel.insert(current)
            }
        }


    }

    override fun getItemCount(): Int {
      return list.size
    }
}