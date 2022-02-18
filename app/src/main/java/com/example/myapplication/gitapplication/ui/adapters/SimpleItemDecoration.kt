package com.example.myapplication.gitapplication.ui.adapters

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.gitapplication.R

class SimpleItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable? = ContextCompat.getDrawable(context, R.drawable.line_divider)

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)

        val left = parent.paddingLeft
        val right = parent.width

        val childCount = parent.childCount
        for (i in 0 until childCount) {

            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + (mDivider?.intrinsicHeight ?: 0)
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(canvas)

        }

    }
}