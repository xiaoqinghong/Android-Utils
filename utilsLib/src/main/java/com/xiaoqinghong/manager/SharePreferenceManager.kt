package com.xiaoqinghong.manager

import android.content.Context
import android.content.SharedPreferences
import com.xiaoqinghong.utils.DebugLog

class SharePreferenceManager {
    private val tag = "SharePreferenceManager"
    private var isInit = false
    private val mSpCache = mutableMapOf<String, SpInstance>()

    companion object {
        val INSTANCE: SharePreferenceManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SharePreferenceManager()
        }
    }

    /**
     * 只初始化一次
     */
    fun initOnce(context: Context, vararg fileNames: String) {
        if (isInit) return
        synchronized(this) {
            if (isInit) return@synchronized
            for (fileName in fileNames) {
                context.getSharedPreferences(fileName, Context.MODE_PRIVATE)?.let {
                    mSpCache.put(fileName, SpInstance(it, fileName))
                }
            }
            isInit = true
        }
    }

    /**
     * 查询某个文件
     */
    fun find(fileName: String): SpInstance? {
        return mSpCache[fileName]
    }

    /**
     * sp实体
     */
    inner class SpInstance constructor(
        private val mSp: SharedPreferences,
        private val fileName: String
    ) {

        private val tag = "SpInstance"

        /**
         * 保存数据
         */
        fun <T : Any> put(key: String, value: T) {
            val editor = mSp.edit()
            when (value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Long -> editor.putLong(key, value)
                is Float -> editor.putFloat(key, value)
                is Boolean -> editor.putBoolean(key, value)
                else -> DebugLog.e("$tag $fileName", "不支持的数据类:${value.javaClass.name}, 保存失败")
            }
            editor.apply()
        }

        /**
         * 获取数据
         */
        @Suppress("UNCHECKED_CAST")
        fun <T : Any> get(key: String, default: T): T {
            return try {
                val value = when (default) {
                    is String -> mSp.getString(key, default)
                    is Int -> mSp.getInt(key, default)
                    is Long -> mSp.getLong(key, default)
                    is Float -> mSp.getFloat(key, default)
                    is Boolean -> mSp.getBoolean(key, default)
                    else -> mSp.getString(key, default.toString())
                }
                value as T
            } catch (e: Exception) {
                DebugLog.e("$tag $fileName", "不支持的数据类型，返回默认值。${e.message!!}")
                default
            }
        }
    }
}