package com.xiaoqinghong.utils

object ArrayUtil {
    fun nullOrEmpty(list: List<*>?): Boolean {
        return list == null || list.isEmpty()
    }
}