package parking

import kotlin.system.exitProcess

fun main() {

    var parkingSpot =  createParkingOfRegNumber()
    var colorCar = createParkingOfColorCar()
    var slot = 1

    while (true) {
        val userChoice = readln().split(" ")
        when {
            userChoice[0] == "park" -> {
                if (parkingSpot.isEmpty())
                    sorry()
                else {
                    loop@ for (i in parkingSpot) {
                        if (i == "free") {
                            slot = parkingSpot.indexOf(i)
                            break@loop
                        }
                    }
                    if (parkingSpot.contains("free")) {
                        parkingSpot[slot] = userChoice[1]
                        colorCar[slot] = userChoice[2].uppercase()
                        println("${userChoice[2]} car parked in spot ${slot + 1}.")
                    }
                    else
                        println("Sorry, the parking lot is full.")
                }
            }
            userChoice[0] == "exit" -> exitProcess(0)
            userChoice[0] == "leave" -> {
                if (parkingSpot.isEmpty())
                    sorry()
                else {
                    parkingSpot[(userChoice[1].toInt() - 1)] = "free"
                    colorCar[(userChoice[1].toInt() - 1)] = "noColor"
                    println("Spot ${userChoice[1]} is free.")
                }
            }
            userChoice[0] == "create" -> {
                parkingSpot = createParkingOfRegNumber(userChoice[1].toInt())
                colorCar = createParkingOfColorCar(userChoice[1].toInt())
                println("Created a parking lot with ${userChoice[1]} spots.")
            }
            userChoice[0] == "status" -> {
                if (parkingSpot.isEmpty())
                    sorry()
                else if (userChoice[0] == "status") {
                    var count = 0
                    for (i in parkingSpot) {
                        if (i == "free")
                            count++
                    }
                    if (count == parkingSpot.size)
                        println("Parking lot is empty.")
                    else {
                        loop@ for (i in parkingSpot.indices) {
                            if (parkingSpot[i] == "free")
                                continue@loop
                            println("${i + 1} ${parkingSpot[i]} ${colorCar[i]}")
                        }
                    }
                }
            }
            userChoice[0] == "reg_by_color" -> {
                if (parkingSpot.isEmpty())
                    sorry()
                else if (userChoice[1].uppercase() !in colorCar)
                    println("No cars with color ${userChoice[1].uppercase()} were found.")
                else if (userChoice[1].uppercase() in colorCar){
                    var regNumber = mutableListOf<String>()
                    for (i in colorCar.indices){
                        if (colorCar[i] == userChoice[1].uppercase())
                            regNumber.add(parkingSpot[i])
                    }
                    println(regNumber.joinToString(", "))
                }
            }
            userChoice[0] == "spot_by_color" -> {
                if (parkingSpot.isEmpty())
                    sorry()
                else if (userChoice[1].uppercase() !in colorCar)
                    println("No cars with color ${userChoice[1].uppercase()} were found.")
                else if (userChoice[1].uppercase() in colorCar){
                    var spotOfColorCar = mutableListOf<Int>()
                    for (i in colorCar.indices){
                        if (colorCar[i] == userChoice[1].uppercase())
                            spotOfColorCar.add(i+1)
                    }
                    println(spotOfColorCar.joinToString(", "))
                }
            }
            userChoice[0] == "spot_by_reg" -> {
                if (parkingSpot.isEmpty())
                    sorry()
                else if (userChoice[1] !in parkingSpot)
                    println("No cars with registration number ${userChoice[1]} were found.")
                else if (userChoice[1] in parkingSpot){
                    println(parkingSpot.indexOf(userChoice[1]) + 1)
                }
            }
        }
    }
}

fun createParkingOfRegNumber(x: Int = 0) = MutableList(x){"free"}
fun createParkingOfColorCar(x: Int = 0) = MutableList(x){"noColor"}
fun sorry(){
    println("Sorry, a parking lot has not been created.")
}
