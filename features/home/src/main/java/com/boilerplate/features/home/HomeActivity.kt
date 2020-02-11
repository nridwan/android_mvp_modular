package com.boilerplate.features.home

import com.boilerplate.common.base.BaseMvpActivity
import com.boilerplate.data.model.data.User
import com.boilerplate.features.home.adapter.HomeAdapter
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject

class HomeActivity: BaseMvpActivity<HomeContract.Presenter>(), HomeContract.View {
    override val presenter: HomeContract.Presenter by inject<HomePresenter>()
    private val data = ArrayList<User>()

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun setup() {
        swipe_to_refresh.setOnRefreshListener {
            presenter.loadData()
        }
        recycler_view.adapter = HomeAdapter(data)
        showLoading()
        presenter.loadData()
    }

    override fun showData(data: List<User>) {
        dismissLoading()
        swipe_to_refresh.isRefreshing = false
        this.data.clear()
        this.data.addAll(data)
        recycler_view.adapter?.notifyDataSetChanged()
    }

    override fun getLayout(): Int = R.layout.activity_home

}