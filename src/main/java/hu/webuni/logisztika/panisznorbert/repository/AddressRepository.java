package hu.webuni.logisztika.panisznorbert.repository;

import hu.webuni.logisztika.panisznorbert.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
