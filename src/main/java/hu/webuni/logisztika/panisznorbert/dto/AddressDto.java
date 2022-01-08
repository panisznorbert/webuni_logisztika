package hu.webuni.logisztika.panisznorbert.dto;

import com.fasterxml.jackson.annotation.JsonView;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressDto {

    @JsonView(View.BaseData.class)
    private Long id;

    @NotNull
    @NotEmpty
    @JsonView(View.BaseData.class)
    private String country;

    @NotNull
    @NotEmpty
    @JsonView(View.BaseData.class)
    private String city;

    @NotNull
    @NotEmpty
    @JsonView(View.BaseData.class)
    private String street;

    @NotNull
    @NotEmpty
    @JsonView(View.BaseData.class)
    private String zipCode;

    @NotNull
    @NotEmpty
    @JsonView(View.BaseData.class)
    private String number;

    @JsonView(View.BaseData.class)
    private double latitude;

    @JsonView(View.BaseData.class)
    private double longitude;

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
