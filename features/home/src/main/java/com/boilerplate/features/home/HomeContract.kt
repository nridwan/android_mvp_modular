package com.boilerplate.features.home

import com.boilerplate.common.base.BaseContract
import com.boilerplate.data.model.data.User

interface HomeContract {
    interface View: BaseContract.View {
        fun showData(data: List<User>)
    }
    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
    }
}