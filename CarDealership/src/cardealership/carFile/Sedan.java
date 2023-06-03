/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership.carFile;

import cardealership.Car;

/**
 *
 * @author kevin
 */
public class Sedan extends Car {

    public Sedan(String MakeandModel, int year, String color, double mileage, double price,
            String fuelType, String engineSize, String transmission, double milesPerGallon, int seatNumber,
            int doorNumber, double weight, String condition, String vehicleIdentificationNumber, int safetyRating,
            String features, String ownershipHistory) {
        super(MakeandModel, year, color, mileage, price,
                fuelType, engineSize, transmission, milesPerGallon,
                seatNumber, doorNumber, weight, condition, vehicleIdentificationNumber,
                safetyRating, features, ownershipHistory);
    }
}
