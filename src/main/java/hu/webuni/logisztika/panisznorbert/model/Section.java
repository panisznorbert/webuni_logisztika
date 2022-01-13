package hu.webuni.logisztika.panisznorbert.model;

import javax.persistence.*;

@Entity
public class Section{

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Milestone fromMilestone;

    @OneToOne
    private Milestone toMilestone;

    private Integer number;

    @ManyToOne
    TransportPlan transportPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Milestone getFromMilestone() {
        return fromMilestone;
    }

    public void setFromMilestone(Milestone fromMilestone) {
        this.fromMilestone = fromMilestone;
    }

    public Milestone getToMilestone() {
        return toMilestone;
    }

    public void setToMilestone(Milestone toMilestone) {
        this.toMilestone = toMilestone;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public TransportPlan getTransportPlan() {
        return transportPlan;
    }

    public void setTransportPlan(TransportPlan transportPlan) {
        this.transportPlan = transportPlan;
    }


}
