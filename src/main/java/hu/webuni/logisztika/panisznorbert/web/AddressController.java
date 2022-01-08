package hu.webuni.logisztika.panisznorbert.web;

import hu.webuni.logisztika.panisznorbert.dto.AddressDto;
import hu.webuni.logisztika.panisznorbert.dto.AddressSearchDto;
import hu.webuni.logisztika.panisznorbert.mapper.AddressMapper;
import hu.webuni.logisztika.panisznorbert.model.Address;
import hu.webuni.logisztika.panisznorbert.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final static Logger LOGGER = Logger.getLogger(AddressController.class.getName());

    @Autowired
    AddressService addressService;

    @Autowired
    AddressMapper addressMapper;


    @PostMapping
    public Long createAddress(@RequestBody @Valid AddressDto addressDto){

        if (addressDto.getId() == 0){
            return addressMapper.addressToDto(addressService.save(addressMapper.dtoToAddress(addressDto))).getId();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<AddressDto> getAddresses(){

        List<Address> addresses = addressService.findAll();

        return addressMapper.addressesToDtos(addresses);
    }

    @GetMapping("/{id}")
    public AddressDto getById(@PathVariable long id){

        Address address = addressService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return addressMapper.addressToDto(address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable long id){

        try {
            addressService.deleteAddress(id);
        } catch (EmptyResultDataAccessException ignored){}
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> modifyAddress(@PathVariable long id, @RequestBody @Valid AddressDto addressDto){

        if (!addressDto.getId().equals(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Address updatedAddress = addressService.update(addressMapper.dtoToAddress(addressDto));

        if (updatedAddress == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(addressMapper.addressToDto(updatedAddress));
    }

    @PostMapping("/search")
    public List<AddressDto> search(@RequestBody AddressSearchDto addressSearchDto){

        return addressMapper.addressesToDtos(addressService.search(addressSearchDto));
    }
}
