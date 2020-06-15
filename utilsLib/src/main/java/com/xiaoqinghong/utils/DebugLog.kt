package com.xiaoqinghong.utils

import android.util.Log

/**
 * 打印调试信息的工具
 */
object DebugLog {
    fun d(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return
        Log.d(tag, message)
    }

    fun e(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return
        Log.e(tag, message)
    }

    fun i(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return
        Log.i(tag, message)
    }

    fun w(tag: String, message: String) {
        if (!BuildConfig.DEBUG) return
        Log.w(tag, message)
    }
}