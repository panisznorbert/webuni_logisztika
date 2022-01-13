package hu.webuni.logisztika.panisznorbert.mapper;

import hu.webuni.logisztika.panisznorbert.dto.AddressDto;
import hu.webuni.logisztika.panisznorbert.model.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto addressToDto(Address address);

    Address dtoToAddress(AddressDto addressDto);

    List<AddressDto> addressesToDtos(List<Address> addresses);

    List<Address> dtosToAddresses(List<AddressDto> addressDtos);
}
