package com.jroviraa.android.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jroviraa.android.materialdesignprogressbutton.MaterialDesignProgressButton
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var buttonDesign: MaterialDesignProgressButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadComponents()
        setUpListener()
    }

    private fun loadComponents() {
        buttonDesign = findViewById(R.id.button)
    }

    private fun setUpListener() {
        buttonDesign.setOnClickListener {
            buttonDesign.isClickable = false
            buttonDesign.showLoading()

            launch {
                withContext(Dispatchers.IO) {
                    Thread.sleep(2000)
                }
                buttonDesign.hideLoading()
                buttonDesign.isClickable = true
            }
        }
    }

}
