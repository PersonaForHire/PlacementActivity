package org.wit.placement.models

interface PlacementStore {
  fun findAll():List<PlacemarkModel>
  fun create(placemark: PlacemarkModel)
  fun update(placemark: PlacemarkModel)
}