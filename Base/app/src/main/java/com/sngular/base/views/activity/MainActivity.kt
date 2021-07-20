package com.sngular.base.views.activity

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.sngular.base.databinding.ActivityMainBinding
import com.sngular.base.navigation.Router
import com.sngular.base.views.adapter.CourseAdapter
import com.sngular.domain.contractor.MainContractor
import com.sngular.domain.model.CourseDto
import com.sngular.domain.presenter.MainPresenter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MainActivity : BaseActivity<MainContractor>(), MainContractor {

    override val progress by lazy { binding.progressBar }

    override val presenter: MainPresenter by instance()

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var adapter = CourseAdapter {
        val parser = Gson()
        val course = parser.toJson(it)
        Router.loadDetail(this, course)
    }

    override val activityModule: Kodein.Module = Kodein.Module(this.javaClass.name) {
        bind<MainPresenter>() with provider {
            MainPresenter(
                repository = instance(),
                errorManager = instance(),
                executor = instance(),
                view = this@MainActivity
            )
        }
    }

    override fun initializeUI() {
        configAdapter()
    }

    override fun registerListeners() {
        //Nothing to do here
    }

    override fun onGetCourseSuccess(courses: List<CourseDto>) {
        adapter.clear()
        courses.map { adapter.add(it) }
    }

    override fun onGetCourseError(error: String) {
        Snackbar.make(binding.mainActivity,error, Snackbar.LENGTH_SHORT).show()
    }

    private fun configAdapter() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

}