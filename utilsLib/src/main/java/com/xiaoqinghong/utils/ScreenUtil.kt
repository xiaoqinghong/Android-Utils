package com.xiaoqinghong.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView

/**
 * 屏幕工具
 */
object ScreenUtil {

    private var screen_w = 0
    private var screen_h = 0
    private var density = 0.0f

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    fun screenWidth(context: Context): Int {
        if (this.screen_w <= 0) {
            this.getScreenByContext(context)
        }
        return this.screen_w
    }

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        if (this.screen_h <= 0) {
            this.getScreenByContext(context)
        }
        return this.screen_h
    }

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    private fun getScreenByContext(context: Context) {
        val metrics = context.resources.displayMetrics
        this.screen_w = metrics.widthPixels
        this.screen_h = metrics.heightPixels
        this.density = metrics.density
    }

    /**
     * 获取ViewGroup屏幕截图
     */
    fun shotViewGroup(group: ViewGroup): Bitmap? {
        return shotViewGroup(group, Color.WHITE)
    }

    /**
     * 获取ViewGroup屏幕截图
     */
    fun shotViewGroup(group: ViewGroup, color: Int): Bitmap? {
        var h = 0
        val bitmap: Bitmap
        for (i in 0 until group.childCount) {
            h += group.getChildAt(i).height
            group.getChildAt(i).setBackgroundColor(color)
        }
        bitmap = Bitmap.createBitmap(group.width, h, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        group.draw(canvas)
        return bitmap
    }

    /**
     * 获取NestedScrollView屏幕截图
     */
    fun shotNestedScrollView(scrollView: NestedScrollView, color: Int): Bitmap? {
        var h = 0
        val bitmap: Bitmap
        for (i in 0 until scrollView.childCount) {
            h += scrollView.getChildAt(i).height
            scrollView.getChildAt(i).setBackgroundColor(color)
        }
        bitmap = Bitmap.createBitmap(scrollView.width, h, Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        scrollView.draw(canvas)
        return bitmap
    }

}