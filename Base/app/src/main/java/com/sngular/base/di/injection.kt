package com.sngular.base.di

import android.content.Context
import com.sngular.base.BuildConfig
import com.sngular.base.error.ErrorManagerImpl
import com.sngular.base.executor.CoroutineExecutorImpl
import com.sngular.data.api.ApiInterface
import com.sngular.data.constants.BuildType
import com.sngular.data.constants.buildType
import com.sngular.data.datasource.network.NetworkDataSource
import com.sngular.data.datasource.network.NetworkDataSourceImpl
import com.sngular.data.datasource.network.createService
import com.sngular.data.repository.CourseRepositoryImpl
import com.sngular.domain.error.ErrorManager
import com.sngular.domain.executor.CoroutineExecutor
import com.sngular.domain.repository.CourseRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

fun appModule(context: Context) = Kodein.Module("App") {
    bind<Context>() with singleton { context }
    bind<CoroutineExecutor>() with singleton { CoroutineExecutorImpl() }
    bind<ErrorManager>() with singleton { ErrorManagerImpl(context = context) }
    bind<BuildType>() with singleton { buildType(BuildConfig.BUILD_TYPE) }
}

val domainModule = Kodein.Module("Domain") {
    // Add here data dependencies
}

val dataModule = Kodein.Module("Data") {
    bind<ApiInterface>() with singleton {
        createService(endPoint = ApiInterface.ENDPOINT)
    }

    // Data Sources
    bind<NetworkDataSource>() with singleton { NetworkDataSourceImpl(api = instance()) }
    bind<CourseRepository>() with singleton { CourseRepositoryImpl(network = instance()) }

}
