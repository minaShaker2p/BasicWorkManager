package com.rezkalla.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    private val workManager = WorkManager.getInstance(this)
    lateinit var btnStartWork: Button
    lateinit var btnWorkStatus: Button
    lateinit var btnResetStatus: Button
    lateinit var btnWorkUIThread: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStartWork = findViewById(R.id.btnStartWork)
        btnWorkStatus = findViewById(R.id.btnWorkStatus)
        btnResetStatus = findViewById(R.id.btnResetStatus)
        btnWorkUIThread = findViewById(R.id.btnWorkOnUIThread)
        initClickListeners()
    }

    private fun initClickListeners() {
        btnStartWork.setOnClickListener {
            val workRequest = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            workManager.enqueue(workRequest)
        }
        btnWorkStatus.setOnClickListener {
            val toast = Toast.makeText(
                this,
                "The work status is:${WorkStatusSingleton.workComplete}",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
        btnResetStatus.setOnClickListener {
            WorkStatusSingleton.workComplete = false
        }
        btnWorkUIThread.setOnClickListener {
            Thread.sleep(10000)
            WorkStatusSingleton.workComplete = true
        }

    }
}