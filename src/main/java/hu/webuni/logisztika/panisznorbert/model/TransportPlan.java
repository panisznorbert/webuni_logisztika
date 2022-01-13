package hu.webuni.logisztika.panisznorbert.model;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedEntityGraph(name = "TransporPlan.full",
        attributeNodes = {
                @NamedAttributeNode(value = "sections", subgraph = "sectionsGraph") },

        subgraphs = { @NamedSubgraph(name = "sectionsGraph", attributeNodes = @NamedAttributeNode("milestone"))
        })
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
}
