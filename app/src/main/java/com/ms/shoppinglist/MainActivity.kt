package com.ms.shoppinglist

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ms.shoppinglist.data.db.ShoppingDatabase
import com.ms.shoppinglist.data.db.entities.ShoppingItem
import com.ms.shoppinglist.data.repositories.ShoppingRepository
import com.ms.shoppinglist.ui.AddShoppingItemDialog
import com.ms.shoppinglist.ui.ShoppingViewModel
import com.ms.shoppinglist.ui.ShoppingViewModelProviderFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.Instance
import org.kodein.di.instance


class MainActivity : AppCompatActivity(),DIAware{
    val rvShoppingItems by lazy {
        findViewById<RecyclerView>(R.id.rv_shopping_list)
    }
    val btnAdd by lazy {
        findViewById<FloatingActionButton>(R.id.floating_add_btn)
    }

    override val di : DI by lazy { (applicationContext as DIAware).di }

    private val factory:ShoppingViewModelProviderFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val database =ShoppingDatabase.getDataBase(this)
//        val repository=ShoppingRepository(database)
//
//        val factory=ShoppingViewModelProviderFactory(repository)

        val viewModel= ViewModelProvider(this,factory)[ShoppingViewModel::class.java]

        val adapter=ShoppingItemAdapter(listOf(),viewModel)
        rvShoppingItems.layoutManager=LinearLayoutManager(this)

        viewModel.getAllShoppingItems().observe(this){
            adapter.list=it
            rvShoppingItems.adapter=adapter
        }


        btnAdd.setOnClickListener {
            AddShoppingItemDialog(this,object:AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.insert(item)
                }

            }).show()
        }



    }

}