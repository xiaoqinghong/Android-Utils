package com.xiaoqinghong.utils

import android.os.Looper

object ThreadExUtil {
    fun isMainThread(): Boolean {
        return Looper.getMainLooper().thread.id == Thread.currentThread().id
    }
}