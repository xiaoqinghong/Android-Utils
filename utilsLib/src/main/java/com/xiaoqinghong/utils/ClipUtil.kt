package com.xiaoqinghong.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.text.TextUtils

object ClipUtil {
    private val tag = this.javaClass.name

    /**
     * 复制文字到剪切板
     */
    fun putTextIntoClip(context: Context, text: String?): Boolean {
        try {
            if (TextUtils.isEmpty(text)) return false
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            //创建ClipData对象
            val clipData = ClipData.newPlainText("copy", text)
            //添加ClipData对象到剪切板中
            cm.setPrimaryClip(clipData)
            return true
        } catch (e: Exception) {
            DebugLog.e(tag, e.message ?: "")
            return false
        }
    }

    /**
     * 读取剪切板内容
     */
    fun getTextFromClip(context: Context): String {
        try {
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val cd = cm.primaryClip ?: return ""
            if (cd.itemCount == 0) return ""
            val c = context::class.java.constructors[0].newInstance("")
            return cd.getItemAt(0).text.toString().trim()
        } catch (e: Exception) {
            DebugLog.e(tag, e.message ?: "")
            return ""
        }
    }

    /**
     * 清空剪切板
     */
    fun clearClip(context: Context) {
        try {
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                cm.clearPrimaryClip()
            } else {
                cm.setPrimaryClip(ClipData.newPlainText("", ""))
            }
        } catch (e: Exception) {
            DebugLog.e(tag, e.message ?: "")
        }
    }
}