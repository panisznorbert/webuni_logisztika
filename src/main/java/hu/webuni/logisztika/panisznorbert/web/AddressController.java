package hu.webuni.logisztika.panisznorbert.web;

import hu.webuni.logisztika.panisznorbert.dto.AddressDto;
import hu.webuni.logisztika.panisznorbert.dto.AddressSearchDto;
import hu.webuni.logisztika.panisznorbert.mapper.AddressMapper;
import hu.webuni.logisztika.panisznorbert.model.Address;
import hu.webuni.logisztika.panisznorbert.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Comparator;
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

        if (addressDto.getId() == null){
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
    public ResponseEntity<List<AddressDto>> search(@RequestBody AddressSearchDto addressSearchDto,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) Integer size,
                                   @RequestParam(required = false) String sort){

        List<AddressDto> addressDtos;

        if (addressSearchDto.getCity() == null && addressSearchDto.getCountry() == null
                && addressSearchDto.getStreet() == null && addressSearchDto.getZipCode() == null){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        int count = 0;

        if (size == null) {
            addressDtos = addressMapper.addressesToDtos(addressService.search(addressSearchDto));
            count = addressDtos.size();
        } else {
            if (page == null) {
                page = 0;
            }
            Pageable pageable = PageRequest.of(page, size);
            Page<Address> addressPage = addressService.search(addressSearchDto, pageable);
            count = addressPage.getTotalPages();
            addressDtos =  addressMapper.addressesToDtos(addressPage.getContent());
        }

        String prop = "id";
        boolean asc = false;

        String[] sortProperties;

        if (sort == null || ",".equals(sort)) {
            addressDtoSort(addressDtos, prop, asc);

        } else {
            sortProperties = sort.split(",");

            if (sortProperties[0] != null && !sortProperties[0].isEmpty()){
                prop = sortProperties[0];
            }

            if (sortProperties.length>1 && sortProperties[1] != null && !sortProperties[1].isEmpty() && "asc".equals(sortProperties[1])){
                asc = true;
            }
        }

        addressDtoSort(addressDtos, prop, asc);
        HttpHeaders header = new HttpHeaders();
        header.add("X-Total-Count", Integer.toString(count));
        return ResponseEntity.ok().headers(header).body(addressDtos);
    }

    private void addressDtoSort(List<AddressDto> addressDtos, String prop, boolean asc){

        if ("country".equals(prop)){
            if (asc){
                addressDtos.sort(Comparator.comparing(AddressDto::getCountry).reversed());
            } else {
                addressDtos.sort(Comparator.comparing(AddressDto::getCountry));
            }
            return;
        }

        if ("city".equals(prop)){
            if (asc){
                addressDtos.sort(Comparator.comparing(AddressDto::getCity).reversed());
            } else {
                addressDtos.sort(Comparator.comparing(AddressDto::getCity));
            }
            return;
        }

        if ("street".equals(prop)){
            if (asc){
                addressDtos.sort(Comparator.comparing(AddressDto::getStreet).reversed());
            } else {
                addressDtos.sort(Comparator.comparing(AddressDto::getStreet));
            }
            return;
        }

        if ("zipCode".equals(prop)){
            if (asc){
                addressDtos.sort(Comparator.comparing(AddressDto::getZipCode).reversed());
            } else {
                addressDtos.sort(Comparator.comparing(AddressDto::getZipCode));
            }
            return;
        }

        if ("number".equals(prop)){
            if (asc){
                addressDtos.sort(Comparator.comparing(AddressDto::getNumber).reversed());
            } else {
                addressDtos.sort(Comparator.comparing(AddressDto::getNumber));
            }
            return;
        }

        if ("latitude".equals(prop)){
            if (asc){
                addressDtos.sort(Comparator.comparingDouble(AddressDto::getLatitude).reversed());
            } else {
                addressDtos.sort(Comparator.comparingDouble(AddressDto::getLatitude));
            }
            return;
        }

        if ("longitude".equals(prop)){
            if (asc){
                addressDtos.sort(Comparator.comparingDouble(AddressDto::getLongitude).reversed());
            } else {
                addressDtos.sort(Comparator.comparingDouble(AddressDto::getLongitude));
            }
            return;
        }

        //default
        if (asc){
            addressDtos.sort(Comparator.comparingLong(AddressDto::getId).reversed());
        } else {
            addressDtos.sort(Comparator.comparingLong(AddressDto::getId));
        }
    }
}
