package com.hexeleries.ambareeshb.vzerve.dagger.component

import com.hexeleries.ambareeshb.vzerve.dagger.modules.UserModule
import com.hexeleries.ambareeshb.vzerve.dagger.scopes.UserScope
import com.hexeleries.ambareeshb.vzerve.db.UserDao
import com.hexeleries.ambareeshb.vzerve.repositories.HomeRepo
import dagger.Subcomponent

/**
 * Created by ambareesh on 28/11/17.
 * User scope [User].
 */
@Subcomponent(modules = arrayOf(UserModule::class))
@UserScope
interface UserComponent {
    fun userDao(): UserDao
    fun userHomeRepo(): HomeRepo


/*@Subcomponent.Builder
    interface Builder {
        fun requestModule(userModule: UserModule): Builder
        fun build(): UserComponent
    }*/
}
