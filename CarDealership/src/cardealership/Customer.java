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
public class Customer 
{
    private String fullName;
    private String userName;
    private String passWord;
    private String address;
    private String phoneNumber;
    private String email;
    private String identificationDocument;
    private String insuranceProviders;
    private ArrayList<String> ownedCars = new ArrayList();
    
    // Constructor
    public Customer(String fullName, String userName, String passWord, String address, String phoneNumber, String email, String identificationDocument, String insuranceProviders, ArrayList<String> ownedCars) 
    {
        this.fullName = fullName;
        this.userName = userName;
        this.passWord = passWord;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.identificationDocument = identificationDocument;
        this.insuranceProviders = insuranceProviders;
        this.ownedCars = ownedCars;
    }

    public Customer() {
        
    }

    
    // Getters
    public String getFullName() 
    {
        return fullName;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public String getPassWord()
    {
        return passWord;
    }
    
    public String getAddress() 
    {
        return address;
    }
    
    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    
    public String getEmail() 
    {
        return email;
    }
    
    public String getIdentificationDocument() 
    {
        return identificationDocument;
    }
    
    public String getInsuranceProviders() 
    {
        return insuranceProviders;
    }
    
    public ArrayList<String> getOwnedCars()
    {
        return ownedCars;
    }
    
    // Setters
    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
    
    public void setAddress(String address) 
    {
        this.address = address;
    }
    
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }
    
    public void setEmail(String email) 
    {
        this.email = email;
    }
    
    public void setIdentificationDocument(String identificationDocument) 
    {
        this.identificationDocument = identificationDocument;
    }
    
    public void setInsuranceProviders(String insuranceProviders) 
    {
        this.insuranceProviders = insuranceProviders;
    }
    
    public void setOwnedCars(ArrayList<String> ownedCars)
    {
        this.ownedCars = ownedCars;
    }
    
    public void addCars(String car)
    {
        ownedCars.add(car);
    }
}
