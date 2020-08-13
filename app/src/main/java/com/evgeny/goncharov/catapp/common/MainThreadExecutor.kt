package com.evgeny.goncharov.catapp.common

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class MainThreadExecutor : Executor {

    private val handler = Handler(Looper.getMainLooper())

    override fun execute(task: Runnable) {
        handler.post(task)
    }
}