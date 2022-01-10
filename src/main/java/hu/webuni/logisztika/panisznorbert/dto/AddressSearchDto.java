package hu.webuni.logisztika.panisznorbert.dto;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;


public class AddressSearchDto {

    @JsonView(View.BaseData.class)
    @Column(length = 2)
    private String country;

    @JsonView(View.BaseData.class)
    private String city;

    @JsonView(View.BaseData.class)
    private String street;

    @JsonView(View.BaseData.class)
    private String zipCode;

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
}
