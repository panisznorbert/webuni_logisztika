package hu.webuni.logisztika.panisznorbert.web;

import static org.assertj.core.api.Assertions.assertThat;

import hu.webuni.logisztika.panisznorbert.dto.LoginDto;
import hu.webuni.logisztika.panisznorbert.model.*;
import hu.webuni.logisztika.panisznorbert.repository.AddressRepository;
import hu.webuni.logisztika.panisznorbert.repository.MilestoneRepository;
import hu.webuni.logisztika.panisznorbert.repository.SectionRepository;
import hu.webuni.logisztika.panisznorbert.repository.TransportPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransportPlanControllerIT {

    private static final String BASE_URI="/api/transportPlans";

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    TransportPlanRepository transportPlanRepository;

    @Autowired
    MilestoneRepository milestoneRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SectionRepository sectionRepository;

    private String jwt;

    private TransportPlan transportPlan1;

    private TransportPlan transportPlan2;

    @BeforeEach
    public void init(){


        LoginDto body = new LoginDto();
        body.setUsername("user2");
        body.setPassword("pass2");
        jwt = webTestClient.post()
                .uri("/api/login")
                .bodyValue(body)
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        createTestData1();
        createTestData2();

    }

    private void createTestData1(){

        transportPlan1 = saveTransportPlan(12000, null);

        Address address1 = saveAddress("HU", "Budapest", "street1", "1157", "18", 123, 123);
        Milestone milestone1 = saveMilestone(address1, LocalDateTime.now().plusMonths(2));
        Milestone milestone2 = saveMilestone(address1, LocalDateTime.now().plusMonths(5));
        Milestone milestone3 = saveMilestone(address1, LocalDateTime.now().plusMonths(8));
        Milestone milestone4 = saveMilestone(address1, LocalDateTime.now().plusMonths(10));
        List<Section> sections = new ArrayList<Section>();
        sections.add(saveSection(milestone1, milestone2, 1, transportPlan1));
        sections.add(saveSection(milestone3, milestone4, 2, transportPlan1));

        transportPlan1.setSections(sections);

        transportPlanRepository.save(transportPlan1);


    }

    private void createTestData2(){

        transportPlan2 = saveTransportPlan(340000, null);

        Address address1 = saveAddress("HU", "Budapest", "street2", "1137", "9", 234, 456);
        Milestone milestone1 = saveMilestone(address1, LocalDateTime.now().plusMonths(2));
        Milestone milestone2 = saveMilestone(address1, LocalDateTime.now().plusMonths(7));
        Milestone milestone3 = saveMilestone(address1, LocalDateTime.now().plusMonths(5));
        Milestone milestone4 = saveMilestone(address1, LocalDateTime.now().plusMonths(6));
        Milestone milestone5 = saveMilestone(address1, LocalDateTime.now().plusMonths(10));
        Milestone milestone6 = saveMilestone(address1, LocalDateTime.now().plusMonths(11));
        List<Section> sections = new ArrayList<Section>();
        sections = new ArrayList<Section>();
        sections.add(saveSection(milestone1, milestone2, 1, transportPlan2));
        sections.add(saveSection(milestone3, milestone4, 2, transportPlan2));
        sections.add(saveSection(milestone5, milestone6, 3, transportPlan2));

        transportPlan2.setSections(sections);

        transportPlanRepository.save(transportPlan2);

    }

    @Transactional
    private Address saveAddress(String country, String city, String street, String zipCode, String number, double latitude, double longitude){
        return addressRepository.save(new Address(country, city, street, zipCode, number, latitude, longitude));
    }

    @Transactional
    private Milestone saveMilestone(Address address, LocalDateTime plannedTime){
        return milestoneRepository.save(new Milestone(address, plannedTime));
    }

    @Transactional
    private Section saveSection(Milestone fromMilestone, Milestone toMilestone, Integer number, TransportPlan transportPlan){
        return sectionRepository.save(new Section(fromMilestone, toMilestone, number, transportPlan));
    }

    @Transactional
    private TransportPlan saveTransportPlan(int expectedRevenue, List<Section> sections){
        return transportPlanRepository.save(new TransportPlan(expectedRevenue, sections));
    }



    //nemlétező transportPlan - negatív teszt
    @Test
    void testThatNonExistTransportPlan(){

        DelayProperties delayProperties = new DelayProperties(1L, 2);

        long transportPlanId = 10L;

        startPost(transportPlanId, delayProperties)
                .expectStatus()
                .isNotFound();

    }

    //nemlétező mérföldkő - negatív teszt
    @Test
    void testThatNonExistMileStone(){

        DelayProperties delayProperties = new DelayProperties(1L, 2);

        long transportPlanId = transportPlan1.getId();

        startPost(transportPlanId, delayProperties)
                .expectStatus()
                .isNotFound();

    }

    //a mérföldkő nem része a transportPlan-nek - negatív teszt
    @Test
    void testThatMileStoneIsNotPartOfTransportPlan(){

        long milestoneId = transportPlan2.getSections().get(0).getFromMilestone().getId();

        long transportPlanId = transportPlan1.getId();

        DelayProperties delayProperties = new DelayProperties(milestoneId, 2);

        startPost(transportPlanId, delayProperties)
                .expectStatus()
                .isBadRequest();
    }

    //kezdő mérföldkő növelése - pozitív teszt
    @Test
    void testThatIncraseFromMilestoneWithDelay(){

        LocalDateTime beforeFromMilestonePlannedTime = transportPlan2.getSections().get(0).getFromMilestone().getPlannedTime().plusMinutes(60);
        LocalDateTime beforeToMilestonePlannedTime = transportPlan2.getSections().get(0).getToMilestone().getPlannedTime().plusMinutes(60);

        int beforRevenue = transportPlan2.getExpectedRevenue();

        long milestoneId = transportPlan2.getSections().get(0).getFromMilestone().getId();

        DelayProperties delayProperties = new DelayProperties(milestoneId, 60);

        startPost(transportPlan2.getId(), delayProperties)
                .expectStatus()
                .isOk();

        Milestone afterFromMilestone = milestoneRepository.findById(transportPlan2.getSections().get(0).getFromMilestone().getId()).get();

        Milestone afterToMilestone = milestoneRepository.findById(transportPlan2.getSections().get(0).getToMilestone().getId()).get();

        assertThat(beforeFromMilestonePlannedTime.withNano(0))
                .isEqualTo(afterFromMilestone.getPlannedTime().withNano(0));

        assertThat(beforeToMilestonePlannedTime.withNano(0))
                .isEqualTo(afterToMilestone.getPlannedTime().withNano(0));

        assertThat(beforRevenue*0.9).isEqualTo(transportPlanRepository.findById(transportPlan2.getId()).get().getExpectedRevenue());
    }

    //végmérföldő növelése - pozitív teszt
    @Test
    void testThatIncraseToMilestoneWithDelay(){


        LocalDateTime beforeToMilestonePlannedTime = transportPlan2.getSections().get(0).getToMilestone().getPlannedTime().plusMinutes(60);
        LocalDateTime beforeNextFromMilestonePlannedTime = transportPlan2.getSections().get(1).getFromMilestone().getPlannedTime().plusMinutes(60);

        int beforRevenue = transportPlan2.getExpectedRevenue();

        long milestoneId = transportPlan2.getSections().get(0).getToMilestone().getId();

        DelayProperties delayProperties = new DelayProperties(milestoneId, 60);

        startPost(transportPlan2.getId(), delayProperties)
                .expectStatus()
                .isOk();

        Milestone afterToMilestone = milestoneRepository.findById(transportPlan2.getSections().get(0).getToMilestone().getId()).get();
        Section nextSection = transportPlan2.getSections().stream().filter(s -> s.getNumber().equals(transportPlan2.getSections().get(0).getNumber()+1)).findFirst().orElse(null);
        Milestone afterNextFromMilestone = milestoneRepository.findById(nextSection.getFromMilestone().getId()).get();

        assertThat(beforeNextFromMilestonePlannedTime.withNano(0))
                .isEqualTo(afterNextFromMilestone.getPlannedTime().withNano(0));

        assertThat(beforeToMilestonePlannedTime.withNano(0))
                .isEqualTo(afterToMilestone.getPlannedTime().withNano(0));

        assertThat(beforRevenue*0.9).isEqualTo(transportPlanRepository.findById(transportPlan2.getId()).get().getExpectedRevenue());

    }

    private WebTestClient.ResponseSpec startPost(long transportPlanId, DelayProperties delayProperties) {

        String path = BASE_URI + "/" + transportPlanId + "/delay";

        return webTestClient
                .post()
                .uri(path)
                .headers(headers -> headers.setBearerAuth(jwt))
                .bodyValue(delayProperties)
                .exchange();
    }
}
