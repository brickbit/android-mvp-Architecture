package com.sngular.base.views.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.sngular.domain.contractor.BaseContractor
import com.sngular.domain.presenter.BasePresenter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.subKodein
import org.kodein.di.android.x.kodein

abstract class BaseFragment<out V : BaseContractor, T : ViewBinding>
    (private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T) :
    Fragment(), KodeinAware, BaseContractor {

    abstract val presenter: BasePresenter<V>

    private var _binding: T? = null

    val binding : T get() = _binding!!

    abstract val progress: CircularProgressIndicator

    abstract val fragmentModule: Kodein.Module

    override val kodein by subKodein(kodein()) {
        import(fragmentModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        registerListeners()

        presenter.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateMethod.invoke(inflater, container, false)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun initializeUI()

    abstract fun registerListeners()

    override fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorId: Int) {
        Toast.makeText(requireContext(), getString(errorId), Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(messageId: Int) {
        Toast.makeText(requireContext(), getString(messageId), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() = progress.show()

    override fun hideProgress() = progress.hide()

}