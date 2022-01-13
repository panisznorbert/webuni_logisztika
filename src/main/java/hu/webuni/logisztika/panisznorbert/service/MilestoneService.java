package hu.webuni.logisztika.panisznorbert.service;

import hu.webuni.logisztika.panisznorbert.model.Milestone;
import hu.webuni.logisztika.panisznorbert.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MilestoneService {

    @Autowired
    MilestoneRepository milestoneRepository;

    public Optional<Milestone> findById(Long id){ return milestoneRepository.findById(id);}

}
