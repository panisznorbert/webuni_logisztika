package hu.webuni.logisztika.panisznorbert.repository;

import hu.webuni.logisztika.panisznorbert.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {


    Page<Address> findAll(Specification<Address> spec, Pageable page);
}
