package com.sngular.base.app

import android.app.Application
import com.sngular.base.di.appModule
import com.sngular.base.di.dataModule
import com.sngular.base.di.domainModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class Application : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule(this@Application))
        import(domainModule)
        import(dataModule)
    }
}
