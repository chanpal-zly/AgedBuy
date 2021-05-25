package com.lind.agedbuy.ui.activity

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lind.agedbuy.R
import com.lind.agedbuy.ui.AgedBaseActivity

class MainActivity : AgedBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            com.google.android.material.snackbar.Snackbar.make(view, "Replace with your own action", com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}