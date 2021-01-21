package com.android.fridaimageload

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.debug.imageload.ImageLoader
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
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

    fun clearDiskCache(view: View) {
        //子线程操作
        // thread {
        //     ImageLoader.getInstance().clearDiskCache()
        // }

        //GlobalScope 创建的协程没有父协程，GlobalScope 通常也不与任何生命周期组件绑定。
        //除非手动管理，否则很难满足我们实际开发中的需求。所以，GlobalScope 能不用就尽量不用。
        // GlobalScope.launch {
        //clearDisk()
        // }


        // launch {
        //     async {
        //         clearDisk()
        //     }
        // }

        //依赖生命周期,不增加dis，默认当前环境
        lifecycleScope.launch(Dispatchers.IO) {
            clearDisk()
        }
    }

    private fun clearDisk() {
        val thread = Thread.currentThread().name
        Log.i("dddd", "thread:$thread")
        ImageLoader.getInstance().clearDiskCache()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}