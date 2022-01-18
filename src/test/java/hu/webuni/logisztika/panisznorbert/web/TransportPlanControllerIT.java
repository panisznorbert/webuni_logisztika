package hu.webuni.logisztika.panisznorbert.web;

import hu.webuni.logisztika.panisznorbert.model.Address;
import hu.webuni.logisztika.panisznorbert.model.Milestone;
import hu.webuni.logisztika.panisznorbert.model.Section;
import hu.webuni.logisztika.panisznorbert.model.TransportPlan;
import hu.webuni.logisztika.panisznorbert.repository.AddressRepository;
import hu.webuni.logisztika.panisznorbert.repository.MilestoneRepository;
import hu.webuni.logisztika.panisznorbert.repository.SectionRepository;
import hu.webuni.logisztika.panisznorbert.repository.TransportPlanRepository;
import hu.webuni.logisztika.panisznorbert.service.TransportPlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransportPlanControllerIT {

    private static final String BASE_URI="/api/transportPlans";

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    TransportPlanService transportPlanService;

    @Autowired
    TransportPlanRepository transportPlanRepository;

    @Autowired
    MilestoneRepository milestoneRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SectionRepository sectionRepository;

    @BeforeEach
    public void init(){
        transportPlanRepository.deleteAll();
        sectionRepository.deleteAll();
        milestoneRepository.deleteAll();
        addressRepository.deleteAll();

        //TestData1
        Address address1 = addressRepository.save(new Address("HU", "Budapest", "street1", "1157", "18", 123, 123));

        Milestone milestone1 = milestoneRepository.save(new Milestone(address1, LocalDateTime.now().minusMonths(2)));
        Milestone milestone2 = milestoneRepository.save(new Milestone(address1, LocalDateTime.now().minusMonths(5)));
        Milestone milestone3 = milestoneRepository.save(new Milestone(address1, LocalDateTime.now().minusMonths(8)));
        Milestone milestone4 = milestoneRepository.save(new Milestone(address1, LocalDateTime.now().minusMonths(10)));

        List<Section> sections1 = new ArrayList<Section>();

        sections1.add(sectionRepository.save(new Section(milestone1, milestone2, 1)));
        sections1.add(sectionRepository.save(new Section(milestone3, milestone4, 2)));

        transportPlanRepository.save(new TransportPlan(12000, sections1));

        //TestData2
        Address address2 = addressRepository.save(new Address("HU", "Budapest", "street2", "1157", "22", 123, 123));

        Milestone milestone5 = milestoneRepository.save(new Milestone(address2, LocalDateTime.now().minusMonths(8)));
        Milestone milestone6= milestoneRepository.save(new Milestone(address2, LocalDateTime.now().minusMonths(10)));
        Milestone milestone7 = milestoneRepository.save(new Milestone(address2, LocalDateTime.now().minusMonths(11)));
        Milestone milestone8= milestoneRepository.save(new Milestone(address2, LocalDateTime.now().minusMonths(12)));
        Milestone milestone9 = milestoneRepository.save(new Milestone(address2, LocalDateTime.now().minusMonths(13)));
        Milestone milestone10= milestoneRepository.save(new Milestone(address2, LocalDateTime.now().minusMonths(14)));

        List<Section> sections2 = new ArrayList<Section>();

        sections2.add(sectionRepository.save(new Section(milestone5, milestone6, 1)));
        sections2.add(sectionRepository.save(new Section(milestone7, milestone8, 2)));
        sections2.add(sectionRepository.save(new Section(milestone9, milestone10, 3)));

        transportPlanRepository.save(new TransportPlan(200000, sections2));
    }

    @Test
    void testSetDelay(){


    }
}
