package com.route.newsappc37.designPatterns

/*

        Builder -> Build Complex Object


           Complex Object -> Dialogs -> title ,

            Create Custom Dialog -> Title only
                                    Title + Image
                                    Image

                                    .build()

           Cart -> Clothes , Product , Food

       GPS Google Maps
       LocationRequestBuilder().addLatLng().build()
        //Creational Patterns -> The way you create Object

        // Singleton -> Single Instance + Global Accessible
        // Factory -> Generate Objects corresponding to input
        // Builder -> Generate Complex object




*/
// Room -> RoomDatabaseBuilder
fun main() {
    val car = Car.Builder()
        .modelOfTheCar("Toyota")
        .numberOfCarTyres(4)
        .yearOfCarProduction("2020")
        .build()


}

fun add(num1: Int, num2: Int) = num1 + num2


class Car private constructor(
    private var model: String? = null,
    private var year: String? = null,
    private var numberOfTyres: Int? = null
) {


    data class Builder(
        private var model: String? = null,
        private var year: String? = null,
        private var numberOfTyres: Int? = null

    ) {
        fun numberOfCarTyres(numberOfCarTyres: Int) =
            apply { this.numberOfTyres = numberOfCarTyres }


        fun yearOfCarProduction(year: String) = apply { this.year = year }
        fun modelOfTheCar(model: String) = apply { this.model = model }

        fun build() = Car(model, year, numberOfTyres)

    }
}