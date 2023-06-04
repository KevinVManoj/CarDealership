/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership;

/**
 *
 * @author kevin
 */
public class Car 
{
    private String MakeandModel; //The make and model  of the car (e.g., Nissan Altima, Toyota Camry)
    private int year; //The year the car was manufactured
    private String color; //The color of the car's exterior.
    private double mileage; //The total distance the car has traveled in miles.
    private double price; //The price of the car.
    private String fuelType; //The type of fuel the car uses (e.g., gasoline, diesel, electric)
    private String engineSize; //The size or displacement of the car's engine (e.g., 2.0L, 3.5L)
    private String transmission; //The type of transmission in the car (e.g., manual, automatic, CVT)
    private double milesPerGallon; //The number of miles a car can travel on 1 gallon of fuel
    private int seatNumber; //The number of seats in a car
    private int doorNumber; //The number of doors in a car
    private double weight; //How much the car weighs
    private String condition; //The overall condition of the car(New, used)
    private String vehicleIdentificationNumber; //A unique identifier for the car
    private int safetyRating; //The safety rating of the car based on crash tests and safety features
    private String features; //Additional features or options available in the car (e.g., sunroof, leather seats, navigation system)
    private String ownershipHistory; // Information about the car's previous owners or any accidents or damages it has experienced
    
    public Car(String MakeandModel, int year, String color, double mileage, double price, 
               String fuelType, String engineSize, String transmission, double milesPerGallon, int seatNumber,
               int doorNumber, double weight, String condition, String vehicleIdentificationNumber, int safetyRating,
               String features, String ownershipHistory) 
    {
        this.MakeandModel = MakeandModel;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
        this.fuelType = fuelType;
        this.engineSize = engineSize;
        this.transmission = transmission;
        this.milesPerGallon = milesPerGallon;
        this.seatNumber = seatNumber;
        this.doorNumber = doorNumber;
        this.weight = weight;
        this.condition = condition;
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
        this.safetyRating = safetyRating;
        this.features = features;
        this.ownershipHistory = ownershipHistory;
    }

    public Car() {
        
    }
    
    // Getters
    public String getMakeandModel() 
    {
        return MakeandModel;
    }
    
    public int getYear() 
    {
        return year;
    }
    
    public String getColor() 
    {
        return color;
    }
    
    public double getMileage() 
    {
        return mileage;
    }
    
    public double getPrice() 
    {
        return price;
    }
    
    public String getFuelType() 
    {
        return fuelType;
    }
    
    public String getEngineSize() 
    {
        return engineSize;
    }
    
    public String getTransmission() 
    {
        return transmission;
    }
    
    public double getMilesPerGallon() 
    {
        return milesPerGallon;
    }
    
    public int getSeatNumber() 
    {
        return seatNumber;
    }
    
    public int getDoorNumber() 
    {
        return doorNumber;
    }
    
    public double getWeight() 
    {
        return weight;
    }
    
    public String getCondition() 
    {
        return condition;
    }
    
    public String getVehicleIdentificationNumber() 
    {
        return vehicleIdentificationNumber;
    }
    
    public int getSafetyRating() 
    {
        return safetyRating;
    }
    
    public String getFeatures() 
    {
        return features;
    }
    
    public String getOwnershipHistory() 
    {
        return ownershipHistory;
    }
    
    // Setters
    public void setMakeandModel(String MakeandModel) 
    {
        this.MakeandModel = MakeandModel;
    }
    
    public void setYear(int year) 
    {
        this.year = year;
    }
    
    public void setColor(String color) 
    {
        this.color = color;
    }
    
    public void setMileage(double mileage) 
    {
        this.mileage = mileage;
    }
    
    public void setPrice(double price) 
    {
        this.price = price;
    }
    
    public void setFuelType(String fuelType) 
    {
        this.fuelType = fuelType;
    }
    
    public void setEngineSize(String engineSize) 
    {
        this.engineSize = engineSize;
    }
    
    public void setTransmission(String transmission) 
    {
        this.transmission = transmission;
    }
    
    public void setMilesPerGallon(double milesPerGallon) 
    {
        this.milesPerGallon = milesPerGallon;
    }
    
    public void setSeatNumber(int seatNumber) 
    {
        this.seatNumber = seatNumber;
    }
    
    public void setDoorNumber(int doorNumber) 
    {
        this.doorNumber = doorNumber;
    }
    
    public void setWeight(double weight) 
    {
        this.weight = weight;
    }
    
    public void setCondition(String condition) 
    {
        this.condition = condition;
    }
    
    public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) 
    {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
    }
    
    public void setSafetyRating(int safetyRating) 
    {
        this.safetyRating = safetyRating;
    }
    
    public void setFeatures(String features) 
    {
        this.features = features;
    }
    
    public void setOwnershipHistory(String ownershipHistory) 
    {
        this.ownershipHistory = ownershipHistory;
    }
    
    public double calculateDownPayment()
    {
        return this.price/5;
    }
    
    public double calculateMontlyPayment(int years)
    {
        double priceM = this.price - this.price/5;
        return priceM/(years*12);
    }
    
    //display the car in a more technical manner
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Make and Model: ").append(MakeandModel).append("\n");
        sb.append("Year: ").append(year).append("\n");
        sb.append("Color: ").append(color).append("\n");
        sb.append("Mileage: ").append(mileage).append(" miles").append("\n");
        sb.append("Price: $").append(price).append("\n");
        sb.append("Fuel Type: ").append(fuelType).append("\n");
        sb.append("Engine Size: ").append(engineSize).append("L").append("\n");
        sb.append("Transmission: ").append(transmission).append("\n");
        sb.append("Miles per Gallon: ").append(milesPerGallon).append("\n");
        sb.append("Seat Number: ").append(seatNumber).append("\n");
        sb.append("Door Number: ").append(doorNumber).append("\n");
        sb.append("Weight: ").append(weight).append(" lbs").append("\n");
        sb.append("Condition: ").append(condition).append("\n");
        sb.append("VIN: ").append(vehicleIdentificationNumber).append("\n");
        sb.append("Safety Rating: ").append(safetyRating).append("/5").append("\n");
        sb.append("Features: ").append(features).append("\n");
        sb.append("Ownership History: ").append(ownershipHistory).append("\n");
        return sb.toString();
    }
}