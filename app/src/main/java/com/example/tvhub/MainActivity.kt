package com.example.tvhub

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rv = RecyclerView(this).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 4)
            setPadding(48, 48, 48, 48)
            clipToPadding = false
            adapter = Adapter(CHANNELS) { open(it) }
        }
        setContentView(rv)
        rv.requestFocus()
    }

    private fun open(c: Channel) {
        when (c.type) {
            Type.APP -> {
                val i = packageManager.getLaunchIntentForPackage(c.target)
                if (i != null) startActivity(i)
                else startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${c.target}")))
            }
            Type.YOUTUBE -> try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(c.target)))
            } catch (e: Exception) { Toast.makeText(this, "No app can open this", Toast.LENGTH_SHORT).show() }
            Type.STREAM -> startActivity(
                Intent(this, PlayerActivity::class.java).putExtra("url", c.target).putExtra("name", c.name))
        }
    }

    class Adapter(private val items: List<Channel>, private val onClick: (Channel) -> Unit) :
        RecyclerView.Adapter<Adapter.VH>() {
        class VH(val tv: TextView) : RecyclerView.ViewHolder(tv)
        override fun onCreateViewHolder(p: ViewGroup, t: Int): VH {
            val tv = TextView(p.context).apply {
                layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 220).apply { setMargins(12, 12, 12, 12) }
                gravity = Gravity.CENTER
                textSize = 22f
                setTextColor(0xFFFFFFFF.toInt())
                isFocusable = true
                isClickable = true
                setBackgroundColor(0xFF2A2A3A.toInt())
                setOnFocusChangeListener { v, f ->
                    v.setBackgroundColor(if (f) 0xFFE50914.toInt() else 0xFF2A2A3A.toInt())
                    v.animate().scaleX(if (f) 1.08f else 1f).scaleY(if (f) 1.08f else 1f).setDuration(120).start()
                }
            }
            return VH(tv)
        }
        override fun onBindViewHolder(h: VH, i: Int) {
            h.tv.text = items[i].name
            h.tv.setOnClickListener { onClick(items[i]) }
        }
        override fun getItemCount() = items.size
    }
}
