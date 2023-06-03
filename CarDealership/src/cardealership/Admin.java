/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealership;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class Admin extends Customer
{
    public Admin(String fullName, String userName, String passWord, String address, String phoneNumber, String email, String identificationDocument, String insuranceProviders, ArrayList<String> ownedCars) {
        super(fullName, userName, passWord, address, phoneNumber, email, identificationDocument, insuranceProviders, ownedCars);
    }
}
