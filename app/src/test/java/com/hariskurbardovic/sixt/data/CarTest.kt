package com.hariskurbardovic.sixt.data

import com.hariskurbardovic.sixt.database.models.Car
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CarTest {

    private lateinit var car: Car

    @Before
    fun setUp() {
        car = Car(
            "WMWSW31030T222518",
            "mini",
            "MINI",
            "Vanessa",
            "BMW",
            "MINI",
            "midnight_black",
            "MINI",
            "MINI",
            0.7,
            "M",
            "M-VO0259",
            48.134557,
            11.576921,
            "REGULAR",
            "https://cdn.sixt.io/codingtask/images/mini.png"
        )
    }

    @Test
    fun test_car_matches() {
        assertEquals("WMWSW31030T222518", car.id)
        assertEquals("mini", car.modelIdentifier)
        assertEquals("MINI", car.modelName)
        assertEquals("Vanessa", car.name)
        assertEquals("BMW", car.make)
        assertEquals("MINI", car.group)
        assertEquals("midnight_black", car.color)
        assertEquals("MINI", car.series)
        assertEquals("MINI", car.fuelType)
        assertEquals(0.7, car.fuelLevel)
        assertEquals("M", car.transmission)
        assertEquals("M-VO0259", car.licensePlate)
        assertEquals(48.134557, car.latitude)
        assertEquals(11.576921, car.longitude)
        assertEquals("REGULAR", car.innerCleanliness)
        assertEquals("https://cdn.sixt.io/codingtask/images/mini.png", car.carImageUrl)
    }

    @Test
    fun test_toString() {
        Assert.assertEquals("Vanessa", car.toString())
    }
}