package com.example.textwidget

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class WidgetConfigActivity : AppCompatActivity() {

    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        setResult(RESULT_CANCELED)

        // 读取 appWidgetId
        appWidgetId = intent?.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        editText = findViewById(R.id.edit_text)

        // 加载已保存的文本
        val prefs = getSharedPreferences(TextWidgetProvider.PREFS_NAME, MODE_PRIVATE)
        val savedText = prefs.getString(TextWidgetProvider.PREF_PREFIX + appWidgetId, "") ?: ""
        editText.setText(savedText)
        editText.setSelection(savedText.length)

        findViewById<Button>(R.id.btn_confirm).setOnClickListener {
            saveAndFinish()
        }

        findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            finish()
        }
    }

    private fun saveAndFinish() {
        val text = editText.text.toString()

        // 保存文本
        val prefs = getSharedPreferences(TextWidgetProvider.PREFS_NAME, MODE_PRIVATE)
        prefs.edit()
            .putString(TextWidgetProvider.PREF_PREFIX + appWidgetId, text)
            .apply()

        // 更新小部件
        val appWidgetManager = AppWidgetManager.getInstance(this)
        TextWidgetProvider.updateAppWidget(this, appWidgetManager, appWidgetId)

        // 设置结果（添加小部件时需要）
        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }
}
