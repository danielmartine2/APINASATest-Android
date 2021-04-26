package com.example.mobiletestandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletestandroid.R
import com.example.mobiletestandroid.Util
import com.example.mobiletestandroid.data.ApolloMapper
import com.example.mobiletestandroid.data.ApolloRepository
import com.example.mobiletestandroid.data.dataSource.Cloud.ApolloDataCloud
import com.example.mobiletestandroid.data.dataSource.Local.ApolloDataLocal
import com.example.mobiletestandroid.data.dataSource.Cloud.EndPoints
import com.example.mobiletestandroid.data.dataSource.Local.ApolloDB
import com.example.mobiletestandroid.domain.ApolloEntity
import com.example.mobiletestandroid.ui.di.DaggerApolloComponent
import com.example.mobiletestandroid.usecases.GetApolloData
import com.example.mobiletestandroid.usecases.UpdateApolloData
import com.loopj.android.http.SyncHttpClient
import javax.inject.Inject

private const val EXTRA_DETAIL = "Detail"

class MainActivity() : AppCompatActivity(), ApolloPresenter.View{

    @Inject lateinit var apolloPresenter: ApolloPresenter

    private var checkGoToDetail = false

    private var apolloAdapter = ApolloAdapter(mutableListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerApolloComponent.builder().build().inject(this)
        ApolloDB.getDatabase(application)

        apolloPresenter.setView(this)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = apolloAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu?.findItem(R.id.menu_search)

        searchItem?.let {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.isNullOrBlank()){

                        apolloAdapter.updateItems(apolloAdapter.getAllItems())
                    }else{
                        val apolloFilter = apolloPresenter.apolloFilter(apolloAdapter.getAllItems(), newText)
                        apolloAdapter.updateFilterItems(apolloFilter)
                    }
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }


    override fun renderApolloData(apolloData: MutableList<ApolloEntity>) {
        apolloAdapter.updateItems(apolloData)
    }

    override fun failGetApolloData() {

    }

    override fun withoutInternet() {

    }

    override fun updateFavourite(apollo: ApolloEntity) {
        apolloAdapter.updateItemChange(apollo)
        apolloPresenter.updateFavourite(apollo)
    }

    override fun navigateToDetail(apollo: ApolloEntity) {
        if (!checkGoToDetail) {
            checkGoToDetail = true
            val intent = Intent(this, ApolloDetailActivity::class.java)
            intent.putExtra(EXTRA_DETAIL, apollo)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        apolloPresenter.onResume()
        checkGoToDetail = false
    }

    override fun onDestroy() {
        apolloPresenter.onDestroy()
        super.onDestroy()
    }


}