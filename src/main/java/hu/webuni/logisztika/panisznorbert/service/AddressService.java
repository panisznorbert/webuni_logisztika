package hu.webuni.logisztika.panisznorbert.service;

import hu.webuni.logisztika.panisznorbert.dto.AddressSearchDto;
import hu.webuni.logisztika.panisznorbert.model.Address;
import hu.webuni.logisztika.panisznorbert.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Transactional
    public Address save(Address address){
        return addressRepository.save(address);
    }

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Long id){
        return addressRepository.findById(id);
    }

    public void deleteAddress(Long id){
        addressRepository.deleteById(id);
    }

    @Transactional
    public Address update(Address address){
        if (address.getId() == null || !addressRepository.existsById(address.getId())){
            return null;
        }
        return addressRepository.save(address);
    }

    public Page<Address> search(AddressSearchDto addressSearchDto, Pageable page){

        return addressRepository.findAll(createSpec(addressSearchDto), page);

    }

    public List<Address> search(AddressSearchDto addressSearchDto){

        return addressRepository.findAll(createSpec(addressSearchDto));

    }

    private Specification createSpec(AddressSearchDto addressSearchDto){

        String country = addressSearchDto.getCountry();
        String city = addressSearchDto.getCity();
        String street = addressSearchDto.getStreet();
        String zipCode = addressSearchDto.getZipCode();

        Specification<Address> spec = Specification.where(null);

        if(StringUtils.hasText(country)){
            spec = spec.and(AddressSpecifications.hasCountry(country));
        }

        if(StringUtils.hasText(city)){
            spec = spec.and(AddressSpecifications.hasCity(city));
        }

        if(StringUtils.hasText(street)){
            spec = spec.and(AddressSpecifications.hasStreet(street));
        }

        if(StringUtils.hasText(zipCode)){
            spec = spec.and(AddressSpecifications.hasZipCode(zipCode));
        }

        return spec;
    }
}
