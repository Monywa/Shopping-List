package com.ms.shoppinglist

import android.app.Application
import android.content.Context
import com.ms.shoppinglist.data.db.ShoppingDatabase
import com.ms.shoppinglist.data.repositories.ShoppingRepository
import com.ms.shoppinglist.ui.ShoppingViewModelProviderFactory
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule


class BaseApplication : Application(),DIAware{
    override val di by DI.lazy {
        import(androidXModule(this@BaseApplication))
        bind<ShoppingDatabase>() with singleton { ShoppingDatabase.getDataBase(instance())}
        bind<ShoppingRepository>() with singleton { ShoppingRepository(instance()) }
        bind<ShoppingViewModelProviderFactory>() with provider { ShoppingViewModelProviderFactory(instance(   )) }
    }
}