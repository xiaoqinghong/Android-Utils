package com.xiaoqinghong.utils

import android.annotation.SuppressLint
import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

object DataUtil {
    private val tag = this.javaClass.name

    /**
     * 日期格式换时间戳(秒)
     */
    @SuppressLint("SimpleDateFormat")
    fun dateToStamp(tpl: String, date: String): Long {
        if (TextUtils.isEmpty(date)) return 0L
        val tp = if (TextUtils.isEmpty(tpl)) "yyyyMMdd" else tpl
        try {
            val sdf = SimpleDateFormat(tp)
            val d = sdf.parse(date) ?: return 0L
            val mDate = sdf.parse(sdf.format(d)) ?: return 0L
            return mDate.time / 1000
        } catch (e: Exception) {
            DebugLog.e(tag, e.message ?: "")
            return 0L
        }
    }

    /**
     * 时间戳换日期格式(秒)
     */
    @SuppressLint("SimpleDateFormat")
    fun stampToDate(tpl: String, stampSec: Long): String {
        val tp = if (TextUtils.isEmpty(tpl)) "yyyyMMdd" else tpl
        val sdf = SimpleDateFormat(tp)
        val mDate = Date(stampSec)
        return sdf.format(mDate)
    }

    /**
     * 获取当前本地时间戳，精确到秒
     */
    fun nowSecondStamp(): Long {
        return nowMSecondStamp() / 1000
    }

    /**
     * 获取当前本地时间戳，精确到毫秒
     */
    fun nowMSecondStamp(): Long {
        return System.currentTimeMillis()
    }
}