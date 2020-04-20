package com.covid.Model

import java.util.ArrayList

class HomeListModel {
    var brandName: String? = null
    var brandCanPhoto: String? = null
    var price: String? = null

    companion object {

        val list: List<HomeListModel>
            get() {
                val homeListModelList = ArrayList<HomeListModel>()
                var homeListModel = HomeListModel()
                homeListModel.brandName = "Bisleri"
                homeListModel.price = "30"
                homeListModelList.add(homeListModel)

                homeListModel = HomeListModel()
                homeListModel.brandName = "AquaFina"
                homeListModel.price = "50"
                homeListModelList.add(homeListModel)

                homeListModel = HomeListModel()
                homeListModel.brandName = "Bisleri"
                homeListModel.price = "20"
                homeListModelList.add(homeListModel)

                homeListModel = HomeListModel()
                homeListModel.brandName = "AquaFina"
                homeListModel.price = "40"
                homeListModelList.add(homeListModel)

                return homeListModelList
            }
    }
}

