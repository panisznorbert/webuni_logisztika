package hu.webuni.logisztika.panisznorbert.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Section{

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Milestone fromMilestone;

    @OneToOne
    private Milestone toMilestone;

    private int number;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
