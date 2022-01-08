package hu.webuni.logisztika.panisznorbert.dto;

import com.fasterxml.jackson.annotation.JsonView;


public class AddressSearchDto {

    @JsonView(View.BaseData.class)
    private String country;

    @JsonView(View.BaseData.class)
    private String city;

    @JsonView(View.BaseData.class)
    private String street;

    @JsonView(View.BaseData.class)
    private String zipCode;
}
