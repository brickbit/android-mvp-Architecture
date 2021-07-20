package com.sngular.base.views.activity

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import com.google.gson.Gson
import com.sngular.base.databinding.ActivityDetailBinding
import com.sngular.base.navigation.Router
import com.sngular.domain.contractor.DetailContractor
import com.sngular.domain.model.CourseDto
import com.sngular.domain.presenter.DetailPresenter
import com.squareup.picasso.Picasso
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


class DetailActivity: BaseActivity<DetailContractor>(), DetailContractor  {

    override val progress by lazy { binding.progressBar }

    override val presenter: DetailPresenter by instance()

    override val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    private var course: CourseDto? = null

    override val activityModule: Kodein.Module = Kodein.Module(this.javaClass.name) {
        bind<DetailPresenter>() with provider {
            DetailPresenter(
                errorManager = instance(),
                executor = instance(),
                view = this@DetailActivity
            )
        }
    }

    override fun initializeUI() {
        val courseJson = intent.getStringExtra(Router.detailCourseExtra)
        val parser = Gson()
        course = parser.fromJson(courseJson, CourseDto::class.java)
        course?.let {
            binding.textViewTitle.text = it.attributes.name
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.textViewDescription.text = Html.fromHtml(it.attributes.description, Html.FROM_HTML_MODE_COMPACT)
            } else {
                binding.textViewDescription.text = Html.fromHtml(it.attributes.description)
            }
            binding.textViewLevel.text = it.attributes.difficulty
            binding.textViewContributors.text = it.attributes.contributor

            Picasso.get().load(it.attributes.artworkUrl).into(binding.imageView)
        }
    }

    override fun registerListeners() {
        binding.buttonViewCourse.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://raywenderlich.com/${course?.courseId}"))
            startActivity(browserIntent)
        }
    }

}