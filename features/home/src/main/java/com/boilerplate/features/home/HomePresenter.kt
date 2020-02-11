package com.boilerplate.features.home

import com.boilerplate.common.base.BasePresenter
import com.boilerplate.data.local.UserRepository
import com.boilerplate.data.remote.UserService
import kotlinx.coroutines.launch

/**
 * A simple [BaseViewModel] that provide the data and handle logic to communicate with the model
 * for [HomeActivity].
 */
class HomePresenter(userRepository: UserRepository, private val userService: UserService)
    : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    override fun loadData() {
        view?.getCoroutine()?.launch {
            try {
                val users = userService.fetchTopUsers()
                view?.showData(users.items)
            } catch (e: Exception) {
                e.printStackTrace()
                view?.errorScreen(e.message)
            }
        }
    }
}