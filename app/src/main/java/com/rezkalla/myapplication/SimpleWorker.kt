package com.rezkalla.myapplication

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Created by Amr on 11/29/2021.
 */
class SimpleWorker(context: Context, params:WorkerParameters) :Worker(context,params) {
    override fun doWork(): Result {
        // simulate long time operation
        Thread.sleep(10000)
        WorkStatusSingleton.workComplete=true
        return Result.success()
    }
}