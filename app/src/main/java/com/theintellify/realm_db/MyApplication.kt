package com.theintellify.realm_db

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * @author {Hardik B. Mahant}
 * crated on 2/3/2020
 */

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("records.realm")
            .build()
        Realm.setDefaultConfiguration(config)
    }
}