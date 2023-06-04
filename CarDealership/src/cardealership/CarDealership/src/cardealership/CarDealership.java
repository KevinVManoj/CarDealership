/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership;

import cardealership.carFile.Convertible;
import cardealership.carFile.Electric;
import cardealership.carFile.Luxury;
import cardealership.carFile.Minivan;
import cardealership.carFile.Pickup;
import cardealership.carFile.SUV;
import cardealership.carFile.Sedan;
import java.io.*;
import java.util.*;

/**
 * The CarDealership class represents a car dealership system. It manages the
 * list of cars, customers, and performs various operations.
 *
 * Author: Kevin Manoj
 */
public class CarDealership {

    // The list of cars in the dealership
    private ArrayList<Car> dealershipCars = new ArrayList();

    // Lists to store cars of different types
    private ArrayList<Car> convertibleCars = new ArrayList();
    private ArrayList<Car> electricCars = new ArrayList();
    private ArrayList<Car> luxuryCars = new ArrayList();
    private ArrayList<Car> minivanCars = new ArrayList();
    private ArrayList<Car> pickupCars = new ArrayList();
    private ArrayList<Car> suvCars = new ArrayList();
    private ArrayList<Car> sedanCars = new ArrayList();
    private ArrayList<Car> otherCars = new ArrayList();

    // The list of customers in the dealership
    private ArrayList<Customer> customerList = new ArrayList();

    // The list of admin customers in the dealership
    private ArrayList<Customer> adminList = new ArrayList();

    // The list of purchased cars
    private ArrayList<Car> purchasedCars = new ArrayList();

    // The list of new customers added in a session
    private ArrayList<Customer> newCustomers = new ArrayList();

    // The list of deleted customers in a session
    private ArrayList<Customer> deletedCustomers = new ArrayList();

    // The list of new cars added in a session
    private ArrayList<Car> newCars = new ArrayList();

    /**
     * Creates a new CarDealership object and starts the dealership system.
     */
    public CarDealership() {
        start();
    }

    /**
     * The start method reads the car and customer files and generates the end
     * of day report.
     */
    public void start() {
        Scanner fileScanner;
        try {
            // Read customer file
            fileScanner = new Scanner(new File("customers.csv"));
            String line;
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine();
                String[] customers = line.split(",");
                if (customers[0].equals("Customer")) {
                    // Create a new Customer object and add it to the customerList
                    Customer aCustomer = new Customer(customers[1], customers[2], customers[3], customers[4], customers[5], customers[6], customers[7], customers[8], new ArrayList<>());
                    customerList.add(aCustomer);
                } else if (customers[0].equals("Admin")) {
                    // Create a new Admin object and add it to the customerList
                    Customer aAdmin = new Admin(customers[1], customers[2], customers[3], customers[4], customers[5], customers[6], customers[7], customers[8], new ArrayList<>());
                    customerList.add(aAdmin);
                }
            }

            // Read car file
            fileScanner = new Scanner(new File("cars.csv"));
            while (fileScanner.hasNext()) {
                line = fileScanner.nextLine();
                String[] cars = line.split("\\|");

                switch (cars[0]) {
                    case "Convertible" -> {
                        // Create a new Convertible car object and add it to the dealershipCars and convertibleCars lists
                        Car aCar = new Convertible(cars[1], Integer.parseInt(cars[2]), cars[3], Double.parseDouble(cars[4]),
                                Double.parseDouble(cars[5]), cars[6], cars[7], cars[8],
                                Double.parseDouble(cars[9]), Integer.parseInt(cars[10]), Integer.parseInt(cars[11]),
                                Double.parseDouble(cars[12]), cars[13], cars[14], Integer.parseInt(cars[15]),
                                cars[16], cars[17]);
                        dealershipCars.add(aCar);
                        convertibleCars.add(aCar);
                    }
                    case "Electric" -> {
                        // Create a new Electric car object and add it to the dealershipCars and electricCars lists
                        Car aCar = new Electric(cars[1], Integer.parseInt(cars[2]), cars[3], Double.parseDouble(cars[4]),
                                Double.parseDouble(cars[5]), cars[6], cars[7], cars[8],
                                Double.parseDouble(cars[9]), Integer.parseInt(cars[10]), Integer.parseInt(cars[11]),
                                Double.parseDouble(cars[12]), cars[13], cars[14], Integer.parseInt(cars[15]),
                                cars[16], cars[17]);
                        dealershipCars.add(aCar);
                        electricCars.add(aCar);
                    }
                    case "Luxury" -> {
                        // Create a new Luxury car object and add it to the dealershipCars and luxuryCars lists
                        Car aCar = new Luxury(cars[1], Integer.parseInt(cars[2]), cars[3], Double.parseDouble(cars[4]),
                                Double.parseDouble(cars[5]), cars[6], cars[7], cars[8],
                                Double.parseDouble(cars[9]), Integer.parseInt(cars[10]), Integer.parseInt(cars[11]),
                                Double.parseDouble(cars[12]), cars[13], cars[14], Integer.parseInt(cars[15]),
                                cars[16], cars[17]);
                        dealershipCars.add(aCar);
                        luxuryCars.add(aCar);
                    }
                    case "Minivan" -> {
                        // Create a new Minivan car object and add it to the dealershipCars and minivanCars lists
                        Car aCar = new Minivan(cars[1], Integer.parseInt(cars[2]), cars[3], Double.parseDouble(cars[4]),
                                Double.parseDouble(cars[5]), cars[6], cars[7], cars[8],
                                Double.parseDouble(cars[9]), Integer.parseInt(cars[10]), Integer.parseInt(cars[11]),
                                Double.parseDouble(cars[12]), cars[13], cars[14], Integer.parseInt(cars[15]),
                                cars[16], cars[17]);
                        dealershipCars.add(aCar);
                        minivanCars.add(aCar);
                    }
                    case "Pickup" -> {
                        // Create a new Pickup car object and add it to the dealershipCars and pickupCars lists
                        Car aCar = new Pickup(cars[1], Integer.parseInt(cars[2]), cars[3], Double.parseDouble(cars[4]),
                                Double.parseDouble(cars[5]), cars[6], cars[7], cars[8],
                                Double.parseDouble(cars[9]), Integer.parseInt(cars[10]), Integer.parseInt(cars[11]),
                                Double.parseDouble(cars[12]), cars[13], cars[14], Integer.parseInt(cars[15]),
                                cars[16], cars[17]);
                        dealershipCars.add(aCar);
                        pickupCars.add(aCar);
                    }
                    case "SUV" -> {
                        // Create a new SUV car object and add it to the dealershipCars and suvCars lists
                        Car aCar = new SUV(cars[1], Integer.parseInt(cars[2]), cars[3], Double.parseDouble(cars[4]),
                                Double.parseDouble(cars[5]), cars[6], cars[7], cars[8],
                                Double.parseDouble(cars[9]), Integer.parseInt(cars[10]), Integer.parseInt(cars[11]),
                                Double.parseDouble(cars[12]), cars[13], cars[14], Integer.parseInt(cars[15]),
                                cars[16], cars[17]);
                        dealershipCars.add(aCar);
                        suvCars.add(aCar);
                    }
                    case "Sedan" -> {
                        // Create a new Sedan car object and add it to the dealershipCars and sedanCars lists
                        Car aCar = new Sedan(cars[1], Integer.parseInt(cars[2]), cars[3], Double.parseDouble(cars[4]),
                                Double.parseDouble(cars[5]), cars[6], cars[7], cars[8],
                                Double.parseDouble(cars[9]), Integer.parseInt(cars[10]), Integer.parseInt(cars[11]),
                                Double.parseDouble(cars[12]), cars[13], cars[14], Integer.parseInt(cars[15]),
                                cars[16], cars[17]);
                        dealershipCars.add(aCar);
                        sedanCars.add(aCar);
                    }
                    case "other" -> {
                        // Create a new Car object (since "other" is not a specific type) and add it to the dealershipCars and otherCars lists
                        Car aCar = new Sedan(cars[1], Integer.parseInt(cars[2]), cars[3], Double.parseDouble(cars[4]),
                                Double.parseDouble(cars[5]), cars[6], cars[7], cars[8],
                                Double.parseDouble(cars[9]), Integer.parseInt(cars[10]), Integer.parseInt(cars[11]),
                                Double.parseDouble(cars[12]), cars[13], cars[14], Integer.parseInt(cars[15]),
                                cars[16], cars[17]);
                        dealershipCars.add(aCar);
                        otherCars.add(aCar);
                    }

                    default -> {
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found :(");
        }

        try {
            try (FileWriter writer01 = new FileWriter("C:\\Users\\kevin\\OneDrive\\Documents\\NetBeansProjects\\ManojKevinProject3\\EndOfDayReport.txt")) {
                writer01.write("""
                                               End of day report:
                                               """);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found :(");
        } catch (IOException ex) {
            System.out.println("IO Exception Error");
        }
    }

    // Getters
    /**
     * Returns the list of all cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getDealershipCars() {
        return dealershipCars;
    }

    /**
     * Returns the list of all convertible cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getConvertibleCars() {
        return convertibleCars;
    }

    /**
     * Returns the list of all electric cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getElectricCars() {
        return electricCars;
    }

    /**
     * Returns the list of all luxury cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getLuxuryCars() {
        return luxuryCars;
    }

    /**
     * Returns the list of all minivan cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getMinivanCars() {
        return minivanCars;
    }

    /**
     * Returns the list of all pickup cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getPickupCars() {
        return pickupCars;
    }

    /**
     * Returns the list of all SUV cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getSuvCars() {
        return suvCars;
    }

    /**
     * Returns the list of all sedan cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getSedanCars() {
        return sedanCars;
    }

    /**
     * Returns the list of all other cars (not specific types) in the
     * dealership.
     * @return 
     */
    public ArrayList<Car> getOtherCars() {
        return otherCars;
    }

    /**
     * Returns the list of all customers in the dealership.
     * @return 
     */
    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * Returns the list of all administrators in the dealership.
     * @return 
     */
    public ArrayList<Customer> getAdminList() {
        return adminList;
    }

    /**
     * Returns the list of all purchased cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getPurchasedCars() {
        return purchasedCars;
    }

    /**
     * Returns the list of all newly registered customers in the dealership.
     * @return 
     */
    public ArrayList<Customer> getNewCustomers() {
        return newCustomers;
    }

    /**
     * Returns the list of all deleted customers in the dealership.
     * @return 
     */
    public ArrayList<Customer> getDeletedCustomers() {
        return deletedCustomers;
    }

    /**
     * Returns the list of all newly added cars in the dealership.
     * @return 
     */
    public ArrayList<Car> getNewCars() {
        return newCars;
    }

// Setters
    /**
     * Sets the list of all cars in the dealership.
     * @param dealershipCars
     */
    public void setDealershipCars(ArrayList<Car> dealershipCars) {
        this.dealershipCars = dealershipCars;
    }

    /**
     * Sets the list of all convertible cars in the dealership.
     * @param convertibleCars
     */
    public void setConvertibleCars(ArrayList<Car> convertibleCars) {
        this.convertibleCars = convertibleCars;
    }

    /**
     * Sets the list of all electric cars in the dealership.
     * @param electricCars
     */
    public void setElectricCars(ArrayList<Car> electricCars) {
        this.electricCars = electricCars;
    }

    /**
     * Sets the list of all luxury cars in the dealership.
     * @param luxuryCars
     */
    public void setLuxuryCars(ArrayList<Car> luxuryCars) {
        this.luxuryCars = luxuryCars;
    }

    /**
     * Sets the list of all minivan cars in the dealership.
     * @param minivanCars
     */
    public void setMinivanCars(ArrayList<Car> minivanCars) {
        this.minivanCars = minivanCars;
    }

    /**
     * Sets the list of all pickup cars in the dealership.
     * @param pickupCars
     */
    public void setPickupCars(ArrayList<Car> pickupCars) {
        this.pickupCars = pickupCars;
    }

    /**
     * Sets the list of all SUV cars in the dealership.
     * @param suvCars
     */
    public void setSuvCars(ArrayList<Car> suvCars) {
        this.suvCars = suvCars;
    }

    /**
     * Sets the list of all sedan cars in the dealership.
     * @param sedanCars
     */
    public void setSedanCars(ArrayList<Car> sedanCars) {
        this.sedanCars = sedanCars;
    }

    /**
     * Sets the list of all other cars (not specific types) in the dealership.
     * @param otherCars
     */
    public void setOtherCars(ArrayList<Car> otherCars) {
        this.otherCars = otherCars;
    }

    /**
     * Sets the list of all customers in the dealership.
     * @param customerList
     */
    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * Sets the list of all administrators in the dealership.
     * @param adminList
     */
    public void setAdminList(ArrayList<Customer> adminList) {
        this.adminList = adminList;
    }

    /**
     * Sets the list of all purchased cars in the dealership.
     * @param purchasedCars
     */
    public void setPurchasedCars(ArrayList<Car> purchasedCars) {
        this.purchasedCars = purchasedCars;
    }

    /**
     * Sets the list of all newly registered customers in the dealership.
     * @param newCustomers
     */
    public void setNewCustomers(ArrayList<Customer> newCustomers) {
        this.newCustomers = newCustomers;
    }

    /**
     * Sets the list of all deleted customers in the dealership.
     * @param deletedCustomers
     */
    public void setDeletedCustomers(ArrayList<Customer> deletedCustomers) {
        this.deletedCustomers = deletedCustomers;
    }

    /**
     * Sets the list of all newly added cars in the dealership.
     * @param newCars
     */
    public void setNewCars(ArrayList<Car> newCars) {
        this.newCars = newCars;
    }

    /**
     * Refreshes the dealership car list by combining individual car type lists.
     */
    public void refresh() {
        dealershipCars.clear();
        dealershipCars.addAll(convertibleCars);
        dealershipCars.addAll(electricCars);
        dealershipCars.addAll(luxuryCars);
        dealershipCars.addAll(minivanCars);
        dealershipCars.addAll(pickupCars);
        dealershipCars.addAll(suvCars);
        dealershipCars.addAll(sedanCars);
        dealershipCars.addAll(otherCars);
    }

    /**
     * Removes a customer from the customer list.
     * @param customer
     */
    public void remove(Customer customer) {
        customerList.remove(customer);
    }

    /**
     * Adds a car to the dealership based on its type.The type parameter
 specifies the type of car being added.
     * @param type
     * @param car
     */
    public void addCar(String type, Car car) {
        if (type.equalsIgnoreCase("convertible")) {
            convertibleCars.add(car);
        } else if (type.equalsIgnoreCase("electric")) {
            electricCars.add(car);
        } else if (type.equalsIgnoreCase("luxury")) {
            luxuryCars.add(car);
        } else if (type.equalsIgnoreCase("minivan")) {
            minivanCars.add(car);
        } else if (type.equalsIgnoreCase("pickup")) {
            pickupCars.add(car);
        } else if (type.equalsIgnoreCase("SUV")) {
            suvCars.add(car);
        } else if (type.equalsIgnoreCase("sedan")) {
            sedanCars.add(car);
        } else if (type.equalsIgnoreCase("Car")) {
            otherCars.add(car);
        }
    }
}
