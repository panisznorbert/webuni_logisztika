package hu.webuni.logisztika.panisznorbert.service;

import hu.webuni.logisztika.panisznorbert.dto.AddressSearchDto;
import hu.webuni.logisztika.panisznorbert.model.Address;
import hu.webuni.logisztika.panisznorbert.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Address> search(AddressSearchDto addressSearchDto){
        return null;
    }
}
