package hu.webuni.logisztika.panisznorbert.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address{

    @Id
    @GeneratedValue
    private Long id;

    private String country;

    private String city;

    private String street;

    private String zipCode;

    private String number;

    private double latitude;

    private double longitude;

    public Address(){}

    public Address(String country, String city, String street, String zipCode, String number, double latitude, double longitude) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.number = number;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(Long id, String country, String city, String street, String zipCode, String number, double latitude, double longitude) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.number = number;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
