package com.ms.shoppinglist.ui

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.ms.shoppinglist.AddDialogListener
import com.ms.shoppinglist.R
import com.ms.shoppinglist.data.db.entities.ShoppingItem

class AddShoppingItemDialog(context:Context,var addDialogListener:AddDialogListener):AppCompatDialog(context) {

    val edName by lazy {
        findViewById<EditText>(R.id.ed_name)
    }
    val edAmount by lazy{
        findViewById<EditText>(R.id.ed_amount)
    }
    val tvOk by lazy {
        findViewById<TextView>(R.id.tv_ok)
    }
    val tvCancel by lazy {
        findViewById<TextView>(R.id.tv_cancel)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_custom_dialog)

        tvOk?.setOnClickListener {
           val name=edName?.text.toString()
           val amount=edAmount?.text.toString()

            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context,"Please enter all the information",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else {
                    val item=ShoppingItem(name =name, amount = amount.toInt())
                addDialogListener.onAddButtonClicked(item)
                dismiss()
            }
        }

        tvCancel?.setOnClickListener {
            cancel()
        }
    }
}