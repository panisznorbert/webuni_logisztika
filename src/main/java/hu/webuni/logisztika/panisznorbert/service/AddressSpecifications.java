package hu.webuni.logisztika.panisznorbert.service;

import hu.webuni.logisztika.panisznorbert.model.Address;
import hu.webuni.logisztika.panisznorbert.model.Address_;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecifications {

    public static Specification<Address> hasCountry(String country){

        return (root, cq, cb) -> cb.equal(root.get(Address_.country), country);
    }

    public static Specification<Address> hasCity(String city){

        return (root, cq, cb) -> cb.like(cb.upper(root.get(Address_.city)), city.toUpperCase() + "%");
    }

    public static Specification<Address> hasStreet(String street){

        return (root, cq, cb) -> cb.like(cb.upper(root.get(Address_.street)), street.toUpperCase() + "%");
    }

    public static Specification<Address> hasZipCode(String zipCode){

        return (root, cq, cb) -> cb.equal(root.get(Address_.zipCode), zipCode);
    }

}
