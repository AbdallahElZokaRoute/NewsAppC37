package com.route.newsappc37.solid

/**
 *  SOLID ->
 *  'S'ingle Responsibilty Principle
 *  'O'pen/ Closed Principle
 *  'L'iskov Substituation principle
 *  'I'nterface Seggregation Principle
 *  'D'ependency Inversion Principle
 *
 *   // Single Responsibilty Principle
 *       - Every Class or function , should have One target
 *
 *
 *
 *
 *
 *
 *
 *
 *      for(i in n){ // O(n)
 *
 *      }
 *
 *
 */
//Single Responsipility
data class Book(
    var authorName: String? = null,
    var bookName: String? = null,
    var releasedDate: String? = null,
    var content: String? = null,
    var id: Int? = null
) {
    fun findWordInBookContents() {
        //searches for the keyword from content
    }


}

class ShareBook {
    fun shareBookContents(book: Book) {
        //share book contents with current parameter
    }
}

class BookPrinter() {
    fun printBookForClient(book: Book) {
        //prints book for client
        book
    }
}

// Open Closed Principle -> Open for Extension / Closed For Modification
/**
 *  Shapes -> Draw Shape
 *
 *
 *
 * */
interface Shape {
    fun draw()
}

class Rectangle : Shape {
    val width: Int? = null
    val height: Int? = null
    override fun draw() {
        print("Drawing Rectangle...")
        print("Draw Rectangle Successfully")
    }
}

class Square : Shape {
    val width: Int? = null
    override fun draw() {
        print("Drawing square...")
        print("Draw square Successfully")
    }

}

class Triangle : Shape {
    val width: Int? = null

    override fun draw() {
        print("Drawing triangle...")
        print("Draw triangle Successfully")
    }
}

class Circle : Shape {
    val radius: Int? = null
    override fun draw() {
        print("Drawing Circle...")
        print("Draw Circle Successfully")
    }


}

class ShapeDrawer {
    //Open For Extension"Inheritance"/ Closed For Modification
    //Rectangle -> Child Of Shape
    fun drawShape(shape: Shape) {
        shape.draw()
    }
}

//Liskov's Substitution Principle
// Abstract before class
// Abstract before function

open class Vehicle {
    var productionYear: String? = null
    var vehicleName: String? = null

    open fun printSpecs() {
        print("Vehicle Production Year = $productionYear  Vehicle Name : $vehicleName")
    }

}

class Car : Vehicle() {

    override fun printSpecs() {
        print("Car Production Year = $productionYear  Car Name : $vehicleName")
    }
}

fun main() {
    var vehicle = Vehicle()
    vehicle.printSpecs()
    vehicle = Car()
    vehicle.printSpecs()
}

// Interface Segregation
// Bad Practice
interface OnClickListener {
    fun onClick()
}

interface OnLongClickListener {
    fun onLongClick()
}

interface OnDoubleClickListener {
    fun onDoubleClick()
}

interface OnSwipeClick {
    fun onSwipeClick()
}

class CustomButton : OnClickListener, OnLongClickListener {
    override fun onClick() {
        print("onClick Triggered")
    }

    override fun onLongClick() {
        print("On Long Click Triggered")
    }
}

