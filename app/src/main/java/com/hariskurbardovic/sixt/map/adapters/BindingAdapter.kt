package com.hariskurbardovic.sixt.map.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hariskurbardovic.sixt.R
import com.hariskurbardovic.sixt.database.models.Car

@BindingAdapter("imageFromUrl")
fun bindImageFromDatabaseImageName(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view)
            .load(imageUrl)
            .placeholder(R.drawable.ic_image_black_24dp)
            .error(R.drawable.ic_broken_image_black_24dp)
            .into(view)
    }
}

@BindingAdapter("textFromCar")
fun bindTextFromCar(view: TextView, car: Car?) {
    car?.let {
        view.text = String.format(
            "modelIdentifier: %s\n" +
                    "modelName: %s\n" +
                    "make: %s\n" +
                    "group: %s\n" +
                    "color: %s\n" +
                    "series: %s\n" +
                    "fuelType: %s\n" +
                    "fuelLevel: %f\n" +
                    "transmission: %s\n" +
                    "licencePlate: %s\n" +
                    "latitude: %f\n" +
                    "longitude: %f\n" +
                    "innerCleanliness: %s\n",
            car.modelIdentifier,
            car.modelName,
            car.make,
            car.group,
            car.color,
            car.series,
            car.fuelLevel,
            car.fuelLevel,
            car.transmission,
            car.licensePlate,
            car.latitude,
            car.longitude,
            car.innerCleanliness
        )
    }
}