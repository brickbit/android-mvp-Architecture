package com.sngular.base.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    protected val items: MutableList<T> = mutableListOf(),
    private val onItemClickListener: (T) -> Unit = {},
    private val onLongClickListener: (T) -> Unit = {}) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T>>() {

    abstract val itemLayoutId: Int

    abstract fun viewHolder(view: View): BaseViewHolder<T>

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(itemLayoutId, parent, false)

        val viewHolder = viewHolder(view)
        viewHolder.onItemClickListener = {onItemClickListener(items[it])}
        viewHolder.onLongClickListener = {onLongClickListener(items[it])}
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.lastIndex)
    }

    fun remove(item: T) {
        if(items.contains(item)) {
            val index = items.indexOf(item)
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun update(item: T) {
        if(items.contains(item)) {
            val index = items.indexOf(item)
            items[index] = item
            notifyItemChanged(index)
        }
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addAll(itemList: MutableList<T>) {
        items.addAll(itemList)
        notifyDataSetChanged()
    }

    fun replace(itemList: MutableList<T>) {
        clear()
        addAll(itemList)
    }

    abstract class  BaseViewHolder<in T>(
        itemView: View,
        var onItemClickListener: (Int) -> Unit = {},
        var onLongClickListener: (Int) -> Unit = {}): RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { onItemClickListener(adapterPosition) }
            itemView.setOnLongClickListener {
                onLongClickListener(adapterPosition)
                true
            }
        }
    abstract fun bind(model:T)
    }

}