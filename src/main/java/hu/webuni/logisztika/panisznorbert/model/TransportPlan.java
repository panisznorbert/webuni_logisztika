package hu.webuni.logisztika.panisznorbert.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class TransportPlan{

    @Id
    @GeneratedValue
    private Long id;

    private int expectedRevenue;

    @OneToMany
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
}
