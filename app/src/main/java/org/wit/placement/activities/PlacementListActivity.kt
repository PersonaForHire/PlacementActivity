package org.wit.placement.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_placement_list.*
import kotlinx.android.synthetic.main.card_placemark.view.*
import org.wit.placement.R
import org.wit.placement.main.MainApp
import org.wit.placement.models.PlacemarkModel


class PlacemarkListActivity : AppCompatActivity() {

  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placement_list)
    app = application as MainApp

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = PlacementAdapter(app.placemarks)
    toolbarMain.title = title
    setSupportActionBar(toolbarMain)


  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }
}

class PlacementAdapter constructor(private var placemarks: List<PlacemarkModel>) :
  RecyclerView.Adapter<PlacementAdapter.MainHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacementAdapter.MainHolder {
    return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_placemark, parent, false))
  }


  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val placemark = placemarks[holder.adapterPosition]
    holder.bind(placemark)
  }

  override fun getItemCount(): Int = placemarks.size


  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(placemark: PlacemarkModel) {
      itemView.placementTitle.text = placemark.title
      itemView.description.text = placemark.description
    }
  }


}

