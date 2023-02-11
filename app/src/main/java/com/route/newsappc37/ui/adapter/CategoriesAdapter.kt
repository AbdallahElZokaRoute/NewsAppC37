package com.route.newsappc37.ui.adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.newsappc37.R
import com.route.newsappc37.model.Category

class CategoriesAdapter(val categoriesList: List<Category?>?) :
    Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val RIGHT_SIDED = 20
    private val LEFT_SIDED = 5
    var onCategorySelected: OnCategorySelectedListener? = null
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) RIGHT_SIDED else LEFT_SIDED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        var view: View = if (viewType == LEFT_SIDED) {

            LayoutInflater.from(parent.context)
                .inflate(R.layout.categories_item_left_sided, parent, false)

        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.categories_item_right_sided, parent, false)
        }
        return CategoriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoriesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = categoriesList?.get(position)!!
        holder.categoryText.text = holder.itemView.context.getString(item.titleResID)
        holder.categoryImage.setImageResource(item.drawableResId)
        holder.categoryConstraintLayout.background.setColorFilter(
            holder.itemView.context.getColor(item.backgroundColor),
            PorterDuff.Mode.SRC_ATOP
        )
        holder.itemView.setOnClickListener {
            onCategorySelected?.onCategorySelected(item)
        }


    }


    class CategoriesViewHolder(itemView: View) : ViewHolder(itemView) {
        val categoryText: TextView = itemView.findViewById(R.id.category_text)
        val categoryImage: ImageView = itemView.findViewById(R.id.category_image)
        val categoryConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.category_item)

    }
}

interface OnCategorySelectedListener {
    fun onCategorySelected(category: Category)
}
