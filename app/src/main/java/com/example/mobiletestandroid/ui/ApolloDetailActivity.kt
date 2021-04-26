package com.example.mobiletestandroid.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobiletestandroid.R
import com.example.mobiletestandroid.Util
import com.example.mobiletestandroid.data.ApolloMapper
import com.example.mobiletestandroid.data.ApolloRepository
import com.example.mobiletestandroid.data.dataSource.Cloud.ApolloDataCloud
import com.example.mobiletestandroid.data.dataSource.Cloud.EndPoints
import com.example.mobiletestandroid.data.dataSource.Local.ApolloDataLocal
import com.example.mobiletestandroid.domain.ApolloEntity
import com.example.mobiletestandroid.ui.di.DaggerApolloDetailComponent
import com.example.mobiletestandroid.usecases.GetApolloData
import com.example.mobiletestandroid.usecases.UpdateApolloData
import com.loopj.android.http.SyncHttpClient
import com.squareup.picasso.Picasso
import javax.inject.Inject

private const val EXTRA_DETAIL = "Detail"

class ApolloDetailActivity : AppCompatActivity() {

    private var apolloData: ApolloEntity? = null
    @Inject lateinit var detailPresenter: ApolloDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apollo_detail)
        DaggerApolloDetailComponent.builder().build().inject(this)

        apolloData = intent?.getSerializableExtra(EXTRA_DETAIL) as ApolloEntity?

        val ivApollo: ImageView = findViewById(R.id.iv_apollo)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        val tvTitle: TextView = findViewById(R.id.tv_title)
        val btFavourite: Button = findViewById(R.id.bt_favourite)
        val btFavouriteOff: Button = findViewById(R.id.bt_favourite_off)

        progressBar.visibility = View.VISIBLE

        apolloData?.let {
            if (it.image.isNotEmpty()) {
                Picasso.get()
                    .load(it.image)
                    .fit()
                    .centerInside()
                    .into(ivApollo)
                progressBar.visibility = View.GONE
            }

            tvTitle.text = it.title

            if (it.isFavourite){
                favourite(btFavourite, btFavouriteOff)
            }else{
                notFavourite(btFavourite, btFavouriteOff)
            }


            btFavourite.setOnClickListener { view ->
                it.isFavourite = !it.isFavourite
                detailPresenter.updateFavourite(it)
                notFavourite(btFavourite, btFavouriteOff)
            }

            btFavouriteOff.setOnClickListener { view ->
                it.isFavourite = !it.isFavourite
                detailPresenter.updateFavourite(it)
                favourite(btFavourite, btFavouriteOff)
            }

        }

    }

    fun favourite(btFavourite: Button, btNotFavourite: Button){
        btFavourite.visibility = View.VISIBLE
        btNotFavourite.visibility = View.GONE
    }

    fun notFavourite(btFavourite: Button, btNotFavourite: Button){
        btFavourite.visibility = View.GONE
        btNotFavourite.visibility = View.VISIBLE
    }
}