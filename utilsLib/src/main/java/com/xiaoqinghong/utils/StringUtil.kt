package com.xiaoqinghong.utils

import android.text.TextUtils

object StringUtil {
    fun getString(text: String? = null, default: String = ""): String {
        return if (TextUtils.isEmpty(text)) default else text!!
    }
}