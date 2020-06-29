package com.xiaoqinghong.utils

/**
 * 数据传递转换工具类(数据量过大时使用，避免bundle超出1m数据大小异常)
 * 只能在UI线程操作
 */
object DataTransUtil {
    private var mObj: Any? = null

    /**
     * 放入传递数据
     *
     * @param data 数据本身
     */
    fun put(data: Any) {
        if (!ThreadExUtil.isMainThread()) {
            DebugLog.e("DataTransUtil", "DataTransUtil.put(data: Any)必须在UI线程操作")
            return
        }
        mObj = data
    }

    /**
     * 获取传递数据
     *
     * @param defaultValue 默认值
     * @param <T>          默认值类型
     * @return 传递的数据
    </T> */
    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(defaultValue: T): T {
        return try {
            return if (mObj == null) {
                if (!ThreadExUtil.isMainThread()) {
                    DebugLog.e("DataTransUtil", "DataTransUtil.get()必须在UI线程操作")
                }
                defaultValue
            } else {
                mObj as T
            }
        } catch (e: Exception) {
            DebugLog.e("转换异常", e.message ?: "异常信息为空")
            defaultValue
        } finally {
            //置空数据
            mObj = null
        }
    }
}