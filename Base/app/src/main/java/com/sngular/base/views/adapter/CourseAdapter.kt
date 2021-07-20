package com.sngular.base.views.adapter

import android.view.View
import com.sngular.base.R
import com.sngular.base.databinding.ActivityMainRecyclerViewItemBinding
import com.sngular.domain.model.CourseDto
import com.squareup.picasso.Picasso

class CourseAdapter(onItemClick: (CourseDto) -> Unit): BaseAdapter<CourseDto>(onItemClickListener = onItemClick) {

    override val itemLayoutId: Int = R.layout.activity_main_recycler_view_item

    override fun viewHolder(view: View): BaseViewHolder<CourseDto> = CourseViewHolder(view)

    class CourseViewHolder(view: View) : BaseAdapter.BaseViewHolder<CourseDto>(view) {
        private val binding = ActivityMainRecyclerViewItemBinding.bind(view)

        override fun bind(model: CourseDto) {
            binding.textViewTitle.text = model.attributes.name
            binding.textViewContributors.text = model.attributes.contributor

            Picasso.get().load(model.attributes.artworkUrl).into(binding.imageView)
        }
    }
}