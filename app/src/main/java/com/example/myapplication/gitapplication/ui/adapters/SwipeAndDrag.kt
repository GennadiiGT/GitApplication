package com.example.myapplication.gitapplication.ui.adapters

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeAndDrag : ItemTouchHelper.Callback() {

    private lateinit var background: Drawable
    private var initiated: Boolean = false

    private val paint = Paint()

    var leftBG: Int = Color.LTGRAY
    var leftLabel: String = ""

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlag = ItemTouchHelper.LEFT
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, swipeFlag)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        recyclerView.adapter?.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    private fun initSwipeView() {
        paint.color = Color.WHITE
        paint.textSize = 55f
        paint.textAlign = Paint.Align.CENTER
        background = ColorDrawable();
        initiated = true;
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(
            c, recyclerView, viewHolder, dX,
            dY, actionState, isCurrentlyActive
        )
        val itemView: View = viewHolder.itemView


        if (!initiated) {
            initSwipeView()
        }

        val xMarkTop = itemView.top + ((itemView.bottom - itemView.top)) / 2

        colorCanvas(
            c,
            leftBG,
            itemView.right + dX.toInt(),
            itemView.top,
            itemView.right,
            itemView.bottom
        )
        drawTextOnCanvas(c, leftLabel, (itemView.right - 200).toFloat(), (xMarkTop + 10).toFloat())
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

    }

    private fun colorCanvas(
        canvas: Canvas,
        canvasColor: Int,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    ) {

        (background as ColorDrawable).color = canvasColor
        background.setBounds(left, top, right, bottom)
        background.draw(canvas)

    }

    private fun drawTextOnCanvas(canvas: Canvas, label: String, x: Float, y: Float) {
        canvas.drawText(label, x, y, paint)
    }

}