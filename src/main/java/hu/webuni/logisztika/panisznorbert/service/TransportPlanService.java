package hu.webuni.logisztika.panisznorbert.service;

import hu.webuni.logisztika.panisznorbert.config.DelayConfigProperties;
import hu.webuni.logisztika.panisznorbert.model.Milestone;
import hu.webuni.logisztika.panisznorbert.model.Section;
import hu.webuni.logisztika.panisznorbert.model.TransportPlan;
import hu.webuni.logisztika.panisznorbert.repository.MilestoneRepository;
import hu.webuni.logisztika.panisznorbert.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TransportPlanService {

    @Autowired
    TransportPlanRepository transportPlanRepository;

    @Autowired
    MilestoneRepository milestoneRepository;

    @Autowired
    DelayConfigProperties config;

    public Optional<TransportPlan> findById(long id){ return transportPlanRepository.findById(id);}

    @Transactional
    public boolean transportPlanIncludeMilestone(TransportPlan transportPlan, long milestoneId, int delay){

        for (Section section : transportPlan.getSections()){

            if (section.getFromMilestone().getId().equals(milestoneId)){

                Milestone milestone = section.getFromMilestone();
                milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay));
                milestoneRepository.save(milestone);

                milestone = section.getToMilestone();
                milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay));
                milestoneRepository.save(milestone);

                return true;
            }

            if (section.getToMilestone().getId().equals(milestoneId)){

                Milestone milestone = section.getToMilestone();
                milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay));
                milestoneRepository.save(milestone);

                Section nextSection = transportPlan.getSections().stream().filter(s -> s.getNumber().equals(section.getNumber()+1)).findFirst().orElse(null);

                if (nextSection != null){

                    milestone = nextSection.getFromMilestone();
                    milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay));
                    milestoneRepository.save(milestone);
                }

                return true;
            }
        }

        return false;
    }

    @Transactional
    public void setPriceFromDelay(TransportPlan transportPlan, int delay){
        if (delay >= 120){
            transportPlan.setExpectedRevenue(transportPlan.getExpectedRevenue()*(100-config.getDelay().getDelay_120_minutes())/100);
            transportPlanRepository.save(transportPlan);
            return;
        }

        if (delay >= 60){
            transportPlan.setExpectedRevenue(transportPlan.getExpectedRevenue()*(100-config.getDelay().getDelay_60_minutes())/100);
            transportPlanRepository.save(transportPlan);
            return;
        }

        if (delay >= 30){
            transportPlan.setExpectedRevenue(transportPlan.getExpectedRevenue()*(100-config.getDelay().getDelay_30_minutes())/100);
            transportPlanRepository.save(transportPlan);
        }

    }
}
