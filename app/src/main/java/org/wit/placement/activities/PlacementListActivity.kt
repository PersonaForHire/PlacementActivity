package org.wit.placement.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_placement_list.*
import org.jetbrains.anko.intentFor
import org.wit.placement.R
import org.wit.placement.main.MainApp
import org.jetbrains.anko.startActivityForResult
import org.wit.placement.models.PlacemarkModel


class PlacemarkListActivity : AppCompatActivity(), PlacementListener {

  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placement_list)
    app = application as MainApp

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    //recyclerView.adapter = PlacementAdapter(app.placemarks)
    recyclerView.adapter = PlacemarkAdapter(app.placemarks.findAll(),this)
    toolbarMain.title = title
    setSupportActionBar(toolbarMain)


  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when(item?.itemId){
      R.id.item_add-> startActivityForResult<MainActivity>(0)
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onPlacemarkClick(placemark: PlacemarkModel) {
    startActivityForResult(intentFor<MainActivity>().putExtra("placemark_edit",placemark),0)
  }
}

