package com.example.textwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class TextWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        for (id in appWidgetIds) {
            editor.remove(PREF_PREFIX + id)
        }
        editor.apply()
    }

    companion object {
        const val PREFS_NAME = "TextWidgetPrefs"
        const val PREF_PREFIX = "widget_text_"

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val text = prefs.getString(PREF_PREFIX + appWidgetId, "") ?: ""

            val views = RemoteViews(context.packageName, R.layout.widget_text)

            if (text.isNotEmpty()) {
                views.setTextViewText(R.id.widget_text_view, text)
            } else {
                views.setTextViewText(R.id.widget_text_view, context.getString(R.string.widget_text_hint))
            }

            // 点击小部件打开配置页面
            val intent = Intent(context, WidgetConfigActivity::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(
                context,
                appWidgetId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_text_view, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
