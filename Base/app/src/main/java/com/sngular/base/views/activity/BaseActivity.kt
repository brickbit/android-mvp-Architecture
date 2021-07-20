package com.sngular.base.views.activity

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.sngular.domain.contractor.BaseContractor
import com.sngular.domain.presenter.BasePresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.subKodein


abstract class BaseActivity <out V : BaseContractor> : AppCompatActivity(), KodeinAware,
    BaseContractor {

    abstract val progress: ProgressBar

    abstract val presenter: BasePresenter<V>

    abstract val binding: ViewBinding

    abstract val activityModule: Kodein.Module

    override val kodein by subKodein(kodein()) {
        import(activityModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeUI()
        registerListeners()
        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }

    abstract fun initializeUI()

    abstract fun registerListeners()

    override fun showError(error: String) = Toast.makeText(this, error, Toast.LENGTH_SHORT).show()

    override fun showError(errorId: Int) = Toast.makeText(this, getString(errorId), Toast.LENGTH_SHORT).show()

    override fun showMessage(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun showMessage(messageId: Int) = Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show()

    override fun showProgress() { progress.visibility = VISIBLE }

    override fun hideProgress() { progress.visibility = GONE }

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }
}


