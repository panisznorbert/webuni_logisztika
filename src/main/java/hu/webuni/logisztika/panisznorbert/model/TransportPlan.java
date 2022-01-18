package hu.webuni.logisztika.panisznorbert.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TransportPlan{

    @Id
    @GeneratedValue
    private Long id;

    private int expectedRevenue;

    @OneToMany(mappedBy = "transportPlan")
    private List<Section> sections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getExpectedRevenue() {
        return expectedRevenue;
    }

    public void setExpectedRevenue(int expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public TransportPlan(){}

    public TransportPlan(int expectedRevenue, List<Section> sections) {
        this.expectedRevenue = expectedRevenue;
        this.sections = sections;
    }

    public TransportPlan(Long id, int expectedRevenue, List<Section> sections) {
        this.id = id;
        this.expectedRevenue = expectedRevenue;
        this.sections = sections;
    }
}
