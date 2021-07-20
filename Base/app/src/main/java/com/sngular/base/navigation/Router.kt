package com.sngular.base.navigation

import android.content.Context
import android.content.Intent
import com.sngular.base.views.activity.DetailActivity

object Router {
    const val detailCourseExtra = "course"

    fun loadDetail(context: Context, course: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(detailCourseExtra,course)
        context.startActivity(intent)
    }
}