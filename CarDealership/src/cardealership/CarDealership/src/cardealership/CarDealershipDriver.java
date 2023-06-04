/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cardealership;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author kevin
 */
public class CarDealershipDriver {

    public static Scanner scnr = new Scanner(System.in);
    public static CarDealership carDealership = new CarDealership();
    public static Customer customerInUse = new Customer();
    public static ArrayList<Customer> customerList = new ArrayList();
    public static Car carInUse = new Car();

    public static void main(String[] args) {

        System.out.println("**** Welcome to Kevin's Car Dealership ****");
        beginningSite();
    }

    private static void beginningSite() {
        int choice0 = 0; // Variable to store the user's choice

        try {
            // Display menu options
            System.out.println("1. Sign in");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            // Read user's choice
            choice0 = scnr.nextInt();

            // Validate user's choice
            if (choice0 < 1 || choice0 > 3) {
                throw new IllegalArgumentException("Invalid choice");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Please enter a valid number:");
            scnr.next();
            beginningSite(); // Consume the invalid input to prevent an infinite loop
        } catch (IllegalArgumentException ex) {
            System.out.println("Please enter either 1, 2, or 3:");
            beginningSite();
        }

        // Perform actions based on user's choice
        switch (choice0) {
            case 1 ->
                signIn();
            case 2 ->
                registerCustomer();
            case 3 ->
                refreshFiles();
        }
    } //the site that can either sign in, register, or exit the program

    private static void signIn() {
        // Method used to sign in a customer

        String userNameAttempt;
        String passWordAttempt;

        try {
            boolean found = false;
            System.out.println("Enter Username: ");
            userNameAttempt = scnr.next();

            // Iterate over the customer list to find a matching username
            for (Customer customerU : carDealership.getCustomerList()) {
                if (customerU.getUserName().equals(userNameAttempt)) {
                    found = true;
                    System.out.println("Enter password:");
                    passWordAttempt = scnr.next();

                    // Check if the entered password is correct
                    if (!customerU.getPassWord().equals(passWordAttempt)) {
                        throw new IllegalArgumentException("password is incorrect. ");
                    }

                    System.out.println("Welcome " + customerU.getFullName());
                    customerInUse = customerU;

                    // Determine the type of customer and call the respective main menu
                    if (customerU instanceof Admin) {
                        System.out.println("-----------------------------------");
                        adminMainMenu();
                    } else {
                        System.out.println("-----------------------------------");
                        customerMainMenu();
                    }
                    break;
                }
            }

            // If no customer with the entered username is found
            if (!found) {
                throw new IllegalArgumentException("username does not exist. ");
            }
        } catch (IllegalArgumentException ex) {
            boolean a = false;
            while (!a) {
                System.out.println(ex.getMessage() + "Would you like to retry?(1) register yourself?(2) or go back?(3) ");
                int input = scnr.nextInt();

                switch (input) {
                    case 1 -> {
                        System.out.println("-----------------------------------");
                        a = true;
                        signIn();
                        break;
                    }
                    case 2 -> {
                        System.out.println("-----------------------------------");
                        a = true;
                        registerCustomer();
                        break;
                    }
                    case 3 -> {
                        System.out.println("-----------------------------------");
                        a = true;
                        beginningSite();
                        break;
                    }
                }
            }
        }
    } //method used to sign into peoples accounts
    
    private static void customerMainMenu() {
        int choice1 = 0;
        try {
            while (choice1 == 0) {
                // Present 4 options for the user to choose from
                scnr.nextLine();
                System.out.println("Please select from one of the following menu options:");
                System.out.println("\t1. Check out cars");
                System.out.println("\t2. Check Profile");
                System.out.println("\t3. Edit Profile");
                System.out.println("\t4. Delete Account");
                System.out.println("\t5. Exit");

                choice1 = scnr.nextInt(); // Get the user's choice

                // Validate the choice
                if (choice1 < 1 || choice1 > 5) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Please enter a valid number:");
            scnr.next();
            customerMainMenu();
        } catch (IllegalArgumentException ex) {
            System.out.println("Please enter either 1, 2, 3, 4, or 5:");
            customerMainMenu();
        }

        // Execute the chosen action based on the user's choice
        switch (choice1) {
            case 1 -> {
                System.out.println("-----------------------------------");
                purchase();
                customerMainMenu();
                break;
            }

            case 2 -> {
                System.out.println("-----------------------------------");
                System.out.println("Name: " + customerInUse.getFullName());
                System.out.println("Username: " + customerInUse.getUserName() + "       Password: " + customerInUse.getPassWord());
                System.out.println("Address: " + customerInUse.getAddress() + "       Email: " + customerInUse.getEmail() + "       Phone Number: " + customerInUse.getPhoneNumber());
                System.out.println("Insurance providers: " + customerInUse.getInsuranceProviders() + "       Identification Document: " + customerInUse.getIdentificationDocument());
                System.out.println("Cars: " + customerInUse.getOwnedCars());
                System.out.println("-----------------------------------");
                customerMainMenu();
                break;
            }

            case 3 -> {
                System.out.println("-----------------------------------");
                editProfile();
                customerMainMenu();
                break;
            }

            case 4 -> {
                System.out.println("-----------------------------------");
                carDealership.getDeletedCustomers().add(customerInUse);
                carDealership.remove(customerInUse);
                beginningSite();
                break;
            }

            case 5 -> {
                System.out.println("-----------------------------------");
                beginningSite();
                break;
            }
        }
    } //method used to show the customer's main menu

    private static void adminMainMenu() {
        int choice1 = 0;
        try {
            while (choice1 == 0) {
                // Presenting 4 options for the user to choose
                scnr.nextLine();
                System.out.println("Please select from one of the following menu options:");
                System.out.println("\t1. View inventory");
                System.out.println("\t2. Add cars");
                System.out.println("\t3. Edit cars");
                System.out.println("\t4. Find Profiles");
                System.out.println("\t5. Exit");

                choice1 = scnr.nextInt(); // User chooses an option

                // Validating the user's choice
                if (choice1 < 1 || choice1 > 5) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Please enter a valid number:");
            scnr.next();
            adminMainMenu();
        } catch (IllegalArgumentException ex) {
            System.out.println("Please enter either 1, 2, 3, 4, or 5:");
            adminMainMenu();
        }

        // Performing actions based on the user's choice
        switch (choice1) {
            case 1 -> {
                // View inventory option
                System.out.println("-----------------------------------");
                int carNum = 1;
                for (Car car : carDealership.getDealershipCars()) {
                    System.out.println(carNum + ". " + car.getMakeandModel() + " VIN: " + car.getVehicleIdentificationNumber());
                    carNum++;
                }
                System.out.println("-----------------------------------");
                adminMainMenu();
                break;
            }

            case 2 -> {
                // Add cars option
                System.out.println("-----------------------------------");
                registerCar();
                adminMainMenu();
                break;
            }

            case 3 -> {
                // Edit cars option
                System.out.println("-----------------------------------");
                editCar();
                adminMainMenu();
                break;
            }

            case 4 -> {
                // Find Profiles option
                System.out.println("-----------------------------------");
                findProfile();
                adminMainMenu();
                break;
            }

            case 5 -> {
                // Exit option
                System.out.println("-----------------------------------");
                beginningSite();
                break;
            }
        }
    } //method used to show the admin's main menu

    private static void purchase() {
        int userChoice = 0; // Initialize choice outside of the try-catch block

        // Loop until a valid user choice is made
        while (userChoice == 0) {
            try {
                System.out.println("Select the genre of car you would like to look through:");
                System.out.println("\t1. Convertible");
                System.out.println("\t2. Electric");
                System.out.println("\t3. Luxury");
                System.out.println("\t4. Minivan");
                System.out.println("\t5. Pickup");
                System.out.println("\t6. SUV");
                System.out.println("\t7. Sedan");
                System.out.println("\t8. Other");
                System.out.println("\t9. Back to Menu");

                userChoice = scnr.nextInt();

                // Check if the user choice is within the valid range
                if (userChoice < 1 || userChoice > 9) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.next();
                purchase();
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a number 1-9:");
                purchase();
            }
        }

        ArrayList<Car> inventory = new ArrayList<>();
        String carType = "";

        // Populate inventory and set carType based on user choice
        switch (userChoice) {
            case 1 -> {
                inventory = carDealership.getConvertibleCars();
                carType = "convertibles";
            }
            case 2 -> {
                inventory = carDealership.getElectricCars();
                carType = "electric cars";
            }
            case 3 -> {
                inventory = carDealership.getLuxuryCars();
                carType = "luxury cars";
            }
            case 4 -> {
                inventory = carDealership.getMinivanCars();
                carType = "minivans";
            }
            case 5 -> {
                inventory = carDealership.getPickupCars();
                carType = "pickups";
            }
            case 6 -> {
                inventory = carDealership.getSuvCars();
                carType = "SUVs";
            }
            case 7 -> {
                inventory = carDealership.getSedanCars();
                carType = "Sedans";
            }
            case 8 -> {
                inventory = carDealership.getOtherCars();
                carType = "other cars";
            }
            case 9 ->
                customerMainMenu();
        }

        // Handle invalid choice case
        if (inventory.isEmpty()) {
            System.out.println("We do not have this type of car in stock, we are sorry.");
            customerMainMenu();
        }

        System.out.println("Here are the dealership's " + carType + ": ");

        // Display the inventory of cars
        int itemNum = 1;
        for (Car car : inventory) {
            System.out.println(itemNum + ". \n" + car.toString());
            itemNum++;
        }
        
        System.out.println(itemNum + ". Exit");
        
        int customerChoice = 0;

        try {
            System.out.println("Which of the " + carType + " would you like?");
            customerChoice = scnr.nextInt();
            
            if(customerChoice == (inventory.size()+2))
            {
                customerMainMenu();
            }
            
            // Check if the customer choice is within the valid range
            if (customerChoice < 1 || customerChoice > inventory.size() + 2) {
                throw new IllegalArgumentException("Invalid choice");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Please enter a valid number:");
            scnr.nextInt(); // Consume the invalid input to prevent an infinite loop
        } catch (IllegalArgumentException ex) {
            System.out.println("Please enter a number 1-" + inventory.size() + 1 + ":");
            customerChoice = 0; // Reset the choice variable to repeat the loop
        }

        // Add the chosen car to the customer's purchased cars and update the customer's owned cars list
        carDealership.getPurchasedCars().add(inventory.get(customerChoice - 1));
        customerInUse.addCars(inventory.get(customerChoice - 1).getMakeandModel());

        System.out.println("This will be your down payment for the car: $" + inventory.get(customerChoice - 1).calculateDownPayment());
        System.out.println("Input how many years you would like to take to pay off the car");

        int years = 0;
        try {
            years = scnr.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Please enter a valid number:");
            scnr.nextLine(); // Consume the invalid input to prevent an infinite loop
        } catch (IllegalArgumentException ex) {
            System.out.println("Please enter a number greater than 0");
            years = 0; // Reset the choice variable to repeat the loop
        }

        System.out.println("This will be your monthly payment for the car: $" + inventory.get(customerChoice - 1).calculateMontlyPayment(years));

        inventory.remove(customerChoice - 1);
        carDealership.refresh();
        customerMainMenu();
    } //method used to purchase a car

    private static void registerCustomer() {
        // Prompt for customer attributes
        scnr.nextLine();
        System.out.println("What is Your full name?");
        String name = scnr.nextLine();
        System.out.println("What will be your username?");
        String userName = scnr.nextLine();
        System.out.println("What will be your password?");
        String passWord = scnr.nextLine();
        System.out.println("What is your address?");
        String address = scnr.nextLine();
        System.out.println("What is your phone number?");
        String phoneNumber = scnr.nextLine();
        System.out.println("What is your email?");
        String email = scnr.nextLine();
        System.out.println("What will you be using as your identification documents?");
        String identificationDoc = scnr.nextLine();
        System.out.println("Who are your insurance providers?");
        String insuranceProviders = scnr.nextLine();

        // Create a new Customer object
        Customer customer = new Customer(name, userName, passWord, address, phoneNumber, email, identificationDoc, insuranceProviders, new ArrayList<>());

        // Add the customer to the customer list
        customerList.add(customer);

        // Add the customer to the new customers list in the car dealership
        carDealership.getNewCustomers().add(customer);

        // Go back to the beginning of the site
        System.out.println("-----------------------------------");
        beginningSite();
    } //method used to make a new customer

    private static void editProfile() //method used to edit profile
    {
        int userChoice = 0; //initialize choice outside of the try-catch block
        while (userChoice == 0) {
            try {
                System.out.println("Select the property to edit: ");
                System.out.println("\t1. Name");
                System.out.println("\t2. Username");
                System.out.println("\t3. Address");
                System.out.println("\t4. Email");
                System.out.println("\t5. Phone Number");
                System.out.println("\t6. Insurance providers");
                System.out.println("\t7. Identification Document");
                System.out.println("\t8. Back to Menu");

                userChoice = scnr.nextInt();
                if (userChoice < 1 || userChoice > 8) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.next(); //consume the invalid input to prevent an infinite loop
                editProfile();
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a number 1-8:");
                editProfile();
            }
        }

        String userInput;
        switch (userChoice) {
            case 1 -> {
                System.out.println("What is your new full name?");
                userInput = scnr.nextLine();

                customerInUse.setFullName(userInput);
                customerMainMenu();
            }

            case 2 -> {
                System.out.println("What will be your new username?");
                userInput = scnr.nextLine();

                customerInUse.setUserName(userInput);
                customerMainMenu();
            }

            case 3 -> {
                System.out.println("What is your new address?");
                userInput = scnr.nextLine();

                customerInUse.setAddress(userInput);
                customerMainMenu();
            }

            case 4 -> {
                System.out.println("What is your new email?");
                userInput = scnr.nextLine();

                customerInUse.setEmail(userInput);
                customerMainMenu();
            }

            case 5 -> {
                System.out.println("What is your new phone number?");
                userInput = scnr.nextLine();

                customerInUse.setPhoneNumber(userInput);
                customerMainMenu();
            }

            case 6 -> {
                System.out.println("who is your new insurance provider?");
                userInput = scnr.nextLine();

                customerInUse.setInsuranceProviders(userInput);
                customerMainMenu();
            }

            case 7 -> {
                System.out.println("What will be your identification document?");
                userInput = scnr.nextLine();

                customerInUse.setIdentificationDocument(userInput);
                customerMainMenu();
            }

            case 8 ->
                customerMainMenu();
        }
    }

    private static void registerCar() //method used to add the car
    {
        int userChoice = 0; //initialize choice outside of the try-catch block
        while (userChoice == 0) {
            try {
                //scnr.nextLine();
                System.out.println("Select the genre of car you would like to look through:");
                System.out.println("\t1. Convertible");
                System.out.println("\t2. Electric");
                System.out.println("\t3. Luxury");
                System.out.println("\t4. Minivan");
                System.out.println("\t5. Pickup");
                System.out.println("\t6. SUV");
                System.out.println("\t7. Sedan");
                System.out.println("\t8. Other");
                System.out.println("\t9. Back to Menu");

                userChoice = scnr.nextInt();
                if (userChoice < 1 || userChoice > 9) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.next(); //consume the invalid input to prevent an infinite loop
                registerCar();
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a number 1-9:");
                registerCar();
            }
        }

        String carType = "";
        switch (userChoice) {
            case 1 -> {
                carType = "convertible";
            }

            case 2 -> {
                carType = "electric";
            }

            case 3 -> {
                carType = "luxury";
            }

            case 4 -> {
                carType = "minivan";
            }

            case 5 -> {
                carType = "pickup";
            }

            case 6 -> {
                carType = "SUV";
            }

            case 7 -> {
                carType = "Sedan";
            }

            case 8 -> {
                carType = "Car";
            }

            case 9 ->
                adminMainMenu();
        }

        //attributes for the new car
        System.out.println("What is the make and model of the " + carType + "?");
        String MakeandModel = scnr.nextLine();
        int year = 0;
        while (year == 0) {
            try {
                System.out.println("What is the year the " + carType + " was manufactured?");
                year = Integer.parseInt(scnr.nextLine());

                if (year < 0) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.nextLine(); // consume the invalid input to prevent an infinite loop
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a valid number:");
                year = 0; // reset the variable to repeat the loop
            }
        }
        System.out.println("What is the color of the " + carType + "?");
        String color = scnr.nextLine();
        double mileage = 0;
        while (mileage == 0) {
            try {
                System.out.println("What is the total mileage of the " + carType + "?");
                mileage = Double.parseDouble(scnr.nextLine());

                if (mileage < 0) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.nextLine(); // consume the invalid input to prevent an infinite loop
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a valid number:");
                mileage = 0; // reset the variable to repeat the loop
            }
        }
        double price = 0;
        while (price == 0) {
            try {
                System.out.println("What is the price of the " + carType + "?");
                price = Double.parseDouble(scnr.nextLine());

                if (price < 0) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.nextLine(); // consume the invalid input to prevent an infinite loop
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a valid number:");
                price = 0; // reset the variable to repeat the loop
            }
        }
        System.out.println("What is the fuel type of the " + carType + "?");
        String fuelType = scnr.nextLine();
        System.out.println("What is the engine size of the " + carType + "?");
        String engineSize = scnr.nextLine();
        System.out.println("What is the transmission of the " + carType + "?");
        String transmission = scnr.nextLine();
        double milesPerGallon = 0;
        while (milesPerGallon == 0) {
            try {
                System.out.println("What is the number of miles the " + carType + " can travel on 1 gallon of fuel?");
                milesPerGallon = Double.parseDouble(scnr.nextLine());

                if (milesPerGallon < 0) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.nextLine(); // consume the invalid input to prevent an infinite loop
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a valid number:");
                milesPerGallon = 0; // reset the variable to repeat the loop
            }
        }
        int seatNumber = 0;
        while (seatNumber == 0) {
            try {
                System.out.println("What is the number of seats the " + carType + " has?");
                seatNumber = Integer.parseInt(scnr.nextLine());

                if (seatNumber < 1) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.nextLine(); // consume the invalid input to prevent an infinite loop
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a valid number:");
                seatNumber = 0; // reset the variable to repeat the loop
            }
        }

        int doorNumber = 0;
        while (doorNumber == 0) {
            try {
                System.out.println("What is the number of doors the " + carType + " has? (excluding the trunk)");
                doorNumber = Integer.parseInt(scnr.nextLine());

                if (doorNumber < 1) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.nextLine(); // consume the invalid input to prevent an infinite loop
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a valid number:");
                doorNumber = 0; // reset the variable to repeat the loop
            }
        }
        double weight = 0;
        while (weight == 0) {
            try {
                System.out.println("How much does the " + carType + " weigh?");
                weight = Double.parseDouble(scnr.nextLine());

                if (weight < 1) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a number");
                scnr.nextLine(); //consume the invalid input to prevent an infinite loop
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a real weight:");
                weight = 0; //reset the choice variable to repeat the loop
            }
        }
        System.out.println("What is the condition of the " + carType + "? (New, Used, Refurbished)");
        String condition = scnr.nextLine();
        System.out.println("Please type the VIN (vehicled identification number)");
        String vehicleIdentificationNumber = scnr.nextLine();
        int safetyRating = 0;
        while (safetyRating == 0) {
            try {
                System.out.println("Please type the safety rating (1-5)");
                safetyRating = Integer.parseInt(scnr.nextLine());

                if (safetyRating < 1 || safetyRating > 5) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a number");
                scnr.nextLine(); //consume the invalid input to prevent an infinite loop
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a number 1-5:");
                safetyRating = 0; //reset the choice variable to repeat the loop
            }
        }
        System.out.println("Please type all the additional features (be sure to use commas after each feature)");
        String features = scnr.nextLine();
        System.out.println("Please type all the information about the car's previous owners or any accidents or damages it has experienced");
        String ownershipHistory = scnr.nextLine();

        Car car = new Car(MakeandModel, year, color, mileage, price, fuelType, engineSize, transmission, milesPerGallon, seatNumber, doorNumber, weight, condition, vehicleIdentificationNumber, safetyRating, features, ownershipHistory);
        carDealership.addCar(carType, car);
        carDealership.getNewCars().add(car);
        carDealership.refresh();
        System.out.println("Thank-you. Your inventory has been updated");
        adminMainMenu();
    }

    private static void editCar() //method used to edit cars
    {
        System.out.println("Please type the VIN of the car you want to edit:");
        String VIN = scnr.nextLine();

        ArrayList<Car> inventory = carDealership.getDealershipCars();
        //Car carInUse;
        for (Car car : inventory) {
            if (car.getVehicleIdentificationNumber().equals(VIN)) {
                carInUse = car;

            }
        }

        int userChoice = 0; // initialize choice outside of the try-catch block
        while (userChoice == 0) {
            try {
                System.out.println("Select the property to edit: ");
                System.out.println("\t1. Make and Model");
                System.out.println("\t2. Year");
                System.out.println("\t3. Color");
                System.out.println("\t4. Mileage");
                System.out.println("\t5. Price");
                System.out.println("\t6. Fuel Type");
                System.out.println("\t7. Engine Size");
                System.out.println("\t8. Transmission");
                System.out.println("\t9. Miles per Gallon");
                System.out.println("\t10. Seat Number");
                System.out.println("\t11. Door Number");
                System.out.println("\t12. Weight");
                System.out.println("\t13. Condition");
                System.out.println("\t14. VIN");
                System.out.println("\t15. Safety Rating");
                System.out.println("\t16. Features");
                System.out.println("\t17. Ownership History");
                System.out.println("\t18. Exit");

                userChoice = scnr.nextInt();
                if (userChoice < 1 || userChoice > 18) {
                    throw new IllegalArgumentException("Invalid choice");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number:");
                scnr.nextInt(); // consume the invalid input to prevent an infinite loop
            } catch (IllegalArgumentException ex) {
                System.out.println("Please enter a number 1-18:");
                userChoice = 0; // reset the choice variable to repeat the loop
            }
        }

        String userInput;
        switch (userChoice) {
            case 1 -> {
                System.out.println("Enter the new make and model:");
                userInput = scnr.nextLine();
                carInUse.setMakeandModel(userInput);
            }
            case 2 -> {
                System.out.println("Enter the new year:");
                userInput = scnr.nextLine();
                int newYear = Integer.parseInt(userInput);
                carInUse.setYear(newYear);
            }
            case 3 -> {
                System.out.println("Enter the new color:");
                userInput = scnr.nextLine();
                carInUse.setColor(userInput);
            }
            case 4 -> {
                System.out.println("Enter the new mileage:");
                userInput = scnr.next();
                double newMileage = Double.parseDouble(userInput);
                carInUse.setMileage(newMileage);
            }
            case 5 -> {
                System.out.println("Enter the new price:");
                userInput = scnr.next();
                double newPrice = Double.parseDouble(userInput);
                carInUse.setPrice(newPrice);
            }
            case 6 -> {
                System.out.println("Enter the new fuel type:");
                userInput = scnr.nextLine();
                carInUse.setFuelType(userInput);
            }
            case 7 -> {
                System.out.println("Enter the new engine size:");
                userInput = scnr.nextLine();
                carInUse.setEngineSize(userInput);
            }
            case 8 -> {
                System.out.println("Enter the new transmission:");
                userInput = scnr.nextLine();
                carInUse.setTransmission(userInput);
            }
            case 9 -> {
                System.out.println("Enter the new miles per gallon:");
                userInput = scnr.next();
                double newMilesPerGallon = Double.parseDouble(userInput);
                carInUse.setMilesPerGallon(newMilesPerGallon);
            }
            case 10 -> {
                System.out.println("Enter the new seat number:");
                userInput = scnr.next();
                int newSeatNumber = Integer.parseInt(userInput);
                carInUse.setSeatNumber(newSeatNumber);
            }
            case 11 -> {
                System.out.println("Enter the new door number:");
                userInput = scnr.next();
                int newDoorNumber = Integer.parseInt(userInput);
                carInUse.setDoorNumber(newDoorNumber);
            }
            case 12 -> {
                System.out.println("Enter the new weight:");
                userInput = scnr.next();
                double newWeight = Double.parseDouble(userInput);
                carInUse.setWeight(newWeight);
            }
            case 13 -> {
                System.out.println("Enter the new condition:");
                userInput = scnr.nextLine();
                carInUse.setCondition(userInput);
            }
            case 14 -> {
                System.out.println("Enter the new VIN:");
                userInput = scnr.nextLine();
                carInUse.setVehicleIdentificationNumber(userInput);
            }
            case 15 -> {
                System.out.println("Enter the new safety rating:");
                userInput = scnr.next();
                int newSafetyRating = Integer.parseInt(userInput);
                carInUse.setSafetyRating(newSafetyRating);
            }
            case 16 -> {
                System.out.println("Enter the new features:");
                userInput = scnr.nextLine();
                carInUse.setFeatures(userInput);
            }
            case 17 -> {
                System.out.println("Enter the new ownership history:");
                userInput = scnr.nextLine();
                carInUse.setOwnershipHistory(userInput);
            }

        }
        adminMainMenu();
    }

    private static void findProfile() //method used to find customer profiles
    {
        int set = 1;
        boolean breaker = false;
        String userNameFind;
        while (!breaker) {
            scnr.nextLine();
            System.out.println("What is the full name of the profile you are looking for?");
            userNameFind = scnr.nextLine();

            for (Customer customer : carDealership.getCustomerList()) {
                if (customer.getFullName().equalsIgnoreCase(userNameFind)) {
                    System.out.println("-----------------------------------");
                    System.out.println("Name: " + customer.getFullName());
                    System.out.println("Username: " + customer.getUserName() + "       Password: " + customer.getPassWord());
                    System.out.println("Address: " + customer.getAddress() + "       Email: " + customer.getEmail() + "       Phone Number: " + customer.getPhoneNumber());
                    System.out.println("Insurance providers: " + customer.getInsuranceProviders() + "       Identification Document: " + customer.getIdentificationDocument());
                    System.out.println("Cars: " + customer.getOwnedCars());
                    System.out.println("Hit enter when finished viewing");
                    System.out.println("-----------------------------------");
                    breaker = true;
                }
            }
            if (!breaker) {
                System.out.println("We do not have this person in our database, please confirm the correct name and try again.");
            }
        }
        adminMainMenu();
    }

    private static void refreshFiles() //method used when ending hava file to refresh all the files read in the beginning so that the next file run is different
    {
        try //displays the entire pet inventory with their respected parameters in the pets file
        {
            // Convertible cars
            try (FileWriter writer = new FileWriter("C:\\Users\\kevin\\OneDrive\\Documents\\NetBeansProjects\\ManojKevinProject3\\cars.csv")) {
                // Convertible cars
                for (Car car : carDealership.getConvertibleCars()) {
                    writer.write("Convertible|"
                            + car.getMakeandModel() + "|"
                            + car.getYear() + "|"
                            + car.getColor() + "|"
                            + car.getMileage() + "|"
                            + car.getPrice() + "|"
                            + car.getFuelType() + "|"
                            + car.getEngineSize() + "|"
                            + car.getTransmission() + "|"
                            + car.getMilesPerGallon() + "|"
                            + car.getSeatNumber() + "|"
                            + car.getDoorNumber() + "|"
                            + car.getWeight() + "|"
                            + car.getCondition() + "|"
                            + car.getVehicleIdentificationNumber() + "|"
                            + car.getSafetyRating() + "|"
                            + car.getFeatures() + "|"
                            + car.getOwnershipHistory()
                            + "\n");
                }

                // Electric cars
                for (Car car : carDealership.getElectricCars()) {
                    writer.write("Electric|"
                            + car.getMakeandModel() + "|"
                            + car.getYear() + "|"
                            + car.getColor() + "|"
                            + car.getMileage() + "|"
                            + car.getPrice() + "|"
                            + car.getFuelType() + "|"
                            + car.getEngineSize() + "|"
                            + car.getTransmission() + "|"
                            + car.getMilesPerGallon() + "|"
                            + car.getSeatNumber() + "|"
                            + car.getDoorNumber() + "|"
                            + car.getWeight() + "|"
                            + car.getCondition() + "|"
                            + car.getVehicleIdentificationNumber() + "|"
                            + car.getSafetyRating() + "|"
                            + car.getFeatures() + "|"
                            + car.getOwnershipHistory()
                            + "\n");
                }

                // Luxury cars
                for (Car car : carDealership.getLuxuryCars()) {
                    writer.write("Luxury|"
                            + car.getMakeandModel() + "|"
                            + car.getYear() + "|"
                            + car.getColor() + "|"
                            + car.getMileage() + "|"
                            + car.getPrice() + "|"
                            + car.getFuelType() + "|"
                            + car.getEngineSize() + "|"
                            + car.getTransmission() + "|"
                            + car.getMilesPerGallon() + "|"
                            + car.getSeatNumber() + "|"
                            + car.getDoorNumber() + "|"
                            + car.getWeight() + "|"
                            + car.getCondition() + "|"
                            + car.getVehicleIdentificationNumber() + "|"
                            + car.getSafetyRating() + "|"
                            + car.getFeatures() + "|"
                            + car.getOwnershipHistory()
                            + "\n");
                }

                // Minivan cars
                for (Car car : carDealership.getMinivanCars()) {
                    writer.write("Minivan|"
                            + car.getMakeandModel() + "|"
                            + car.getYear() + "|"
                            + car.getColor() + "|"
                            + car.getMileage() + "|"
                            + car.getPrice() + "|"
                            + car.getFuelType() + "|"
                            + car.getEngineSize() + "|"
                            + car.getTransmission() + "|"
                            + car.getMilesPerGallon() + "|"
                            + car.getSeatNumber() + "|"
                            + car.getDoorNumber() + "|"
                            + car.getWeight() + "|"
                            + car.getCondition() + "|"
                            + car.getVehicleIdentificationNumber() + "|"
                            + car.getSafetyRating() + "|"
                            + car.getFeatures() + "|"
                            + car.getOwnershipHistory()
                            + "\n");
                }

                // Pickup cars
                for (Car car : carDealership.getPickupCars()) {
                    writer.write("Pickup|"
                            + car.getMakeandModel() + "|"
                            + car.getYear() + "|"
                            + car.getColor() + "|"
                            + car.getMileage() + "|"
                            + car.getPrice() + "|"
                            + car.getFuelType() + "|"
                            + car.getEngineSize() + "|"
                            + car.getTransmission() + "|"
                            + car.getMilesPerGallon() + "|"
                            + car.getSeatNumber() + "|"
                            + car.getDoorNumber() + "|"
                            + car.getWeight() + "|"
                            + car.getCondition() + "|"
                            + car.getVehicleIdentificationNumber() + "|"
                            + car.getSafetyRating() + "|"
                            + car.getFeatures() + "|"
                            + car.getOwnershipHistory()
                            + "\n");
                }

                // SUV cars
                for (Car car : carDealership.getSuvCars()) {
                    writer.write("SUV|"
                            + car.getMakeandModel() + "|"
                            + car.getYear() + "|"
                            + car.getColor() + "|"
                            + car.getMileage() + "|"
                            + car.getPrice() + "|"
                            + car.getFuelType() + "|"
                            + car.getEngineSize() + "|"
                            + car.getTransmission() + "|"
                            + car.getMilesPerGallon() + "|"
                            + car.getSeatNumber() + "|"
                            + car.getDoorNumber() + "|"
                            + car.getWeight() + "|"
                            + car.getCondition() + "|"
                            + car.getVehicleIdentificationNumber() + "|"
                            + car.getSafetyRating() + "|"
                            + car.getFeatures() + "|"
                            + car.getOwnershipHistory()
                            + "\n");
                }

                // Sedan cars
                for (Car car : carDealership.getSedanCars()) {
                    writer.write("Sedan|"
                            + car.getMakeandModel() + "|"
                            + car.getYear() + "|"
                            + car.getColor() + "|"
                            + car.getMileage() + "|"
                            + car.getPrice() + "|"
                            + car.getFuelType() + "|"
                            + car.getEngineSize() + "|"
                            + car.getTransmission() + "|"
                            + car.getMilesPerGallon() + "|"
                            + car.getSeatNumber() + "|"
                            + car.getDoorNumber() + "|"
                            + car.getWeight() + "|"
                            + car.getCondition() + "|"
                            + car.getVehicleIdentificationNumber() + "|"
                            + car.getSafetyRating() + "|"
                            + car.getFeatures() + "|"
                            + car.getOwnershipHistory()
                            + "\n");
                }

                // Other cars
                for (Car car : carDealership.getOtherCars()) {
                    writer.write("Other|"
                            + car.getMakeandModel() + "|"
                            + car.getYear() + "|"
                            + car.getColor() + "|"
                            + car.getMileage() + "|"
                            + car.getPrice() + "|"
                            + car.getFuelType() + "|"
                            + car.getEngineSize() + "|"
                            + car.getTransmission() + "|"
                            + car.getMilesPerGallon() + "|"
                            + car.getSeatNumber() + "|"
                            + car.getDoorNumber() + "|"
                            + car.getWeight() + "|"
                            + car.getCondition() + "|"
                            + car.getVehicleIdentificationNumber() + "|"
                            + car.getSafetyRating() + "|"
                            + car.getFeatures() + "|"
                            + car.getOwnershipHistory()
                            + "\n");
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found :(");
        } catch (IOException ex) {
            System.out.println("This is an IO Exception");
        }

        //The End of Day file shows the new customers added, new cars added, and cars and customers deleted
        try {
            FileWriter writer01 = new FileWriter("C:\\Users\\kevin\\OneDrive\\Documents\\NetBeansProjects\\ManojKevinProject3\\EndOfDayReport.txt", true);

            writer01.write("\n");
            writer01.write("New Customers: \n");
            for (Customer s : carDealership.getNewCustomers()) {
                writer01.write(s.getFullName() + "\n");
            }

            writer01.write("\n");
            writer01.write("Deleted Customers: \n");
            for (Customer s : carDealership.getDeletedCustomers()) {
                writer01.write(s.getFullName() + "\n");
            }

            writer01.write("\n");
            writer01.write("New Cars: \n");
            for (Car s : carDealership.getNewCars()) {
                writer01.write(s.getMakeandModel() + "\n");
            }

            writer01.write("\n");
            writer01.write("Purchased Cars: \n");
            for (Car s : carDealership.getPurchasedCars()) {
                writer01.write(s.getMakeandModel() + "\n");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found :(");
        } catch (IOException ex) {
            System.out.println("This is an IO Exception");
        }

        //All customers in the member and premium member arraylist are writtin in the customers.csv file with their respected parameters/
        try {
            FileWriter writer = new FileWriter("C:\\Users\\kevin\\OneDrive\\Documents\\NetBeansProjects\\ManojKevinProject3\\customers.csv");

            for (Customer m : carDealership.getCustomerList()) {
                if (m instanceof Admin) {
                    writer.write("Admin,"
                            + m.getFullName() + ","
                            + m.getUserName() + ","
                            + m.getPassWord() + ","
                            + m.getAddress() + ","
                            + m.getPhoneNumber() + ","
                            + m.getEmail() + ","
                            + m.getIdentificationDocument() + ","
                            + m.getInsuranceProviders() + ","
                            + m.getOwnedCars() + "\n"
                    );
                } else {
                    writer.write("Customer,"
                            + m.getFullName() + ","
                            + m.getUserName() + ","
                            + m.getPassWord() + ","
                            + m.getAddress() + ","
                            + m.getPhoneNumber() + ","
                            + m.getEmail() + ","
                            + m.getIdentificationDocument() + ","
                            + m.getInsuranceProviders() + ","
                            + m.getOwnedCars() + "\n"
                    );
                }
            }
            writer.close();
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }
}
