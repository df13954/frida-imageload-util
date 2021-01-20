package com.android.fridaimageload

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val iv: ImageView = findViewById(R.id.iv_image)
        val ivCy: ImageView = findViewById(R.id.iv_cy)
        //加载图片
        val url =
            "https://pics6.baidu.com/feed/622762d0f703918fe231bff2f881aa9158eec46d.jpeg?token=4956296509af2a64e5f9aa9251658d85"
        ImageHelper.load(iv, url)

        ImageHelper.loadGif(ivCy, R.mipmap.ic_cy)


    }
}