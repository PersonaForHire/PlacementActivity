package org.wit.placement.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.placement.R
import org.wit.placement.main.MainApp
import org.wit.placement.models.PlacemarkModel

class MainActivity : AppCompatActivity(),AnkoLogger {
  var placemark = PlacemarkModel()
  lateinit var app : MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    app = application as MainApp

    if (intent.hasExtra("placemark_edit")) {
      placemark = intent.extras.getParcelable<PlacemarkModel>("placemark_edit")
      placemarkTitle.setText(placemark.title)
      description.setText(placemark.description)
    }

    btnAdd.setOnClickListener() {
      placemark.title = placemarkTitle.text.toString()
      placemark.description = description.text.toString()
      if (placemark.title.isNotEmpty()) {
        //app.placemarks.add(placemark.copy())
        app.placemarks.create(placemark.copy())
        // app.placemarks.forEach { info("add Button Pressed: $it")}
        app.placemarks.findAll().forEach{info("add Button Pressed: $it")}
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      }
      else {
        toast (getString(R.string.activity_enterTitle))
      }
    }
    toolbarAdd.title = title
    setSupportActionBar(toolbarAdd)
  }
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_placement, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when(item?.itemId){
      R.id.item_cancel-> startActivityForResult<PlacemarkListActivity>(0)
    }
    return super.onOptionsItemSelected(item)
  }
}
