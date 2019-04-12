package com.rafaelguimas.cardata.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelguimas.cardata.ui.ui.manufacturer.ManufacturerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ManufacturerFragment.newInstance())
                .commitNow()
        }
    }

}
