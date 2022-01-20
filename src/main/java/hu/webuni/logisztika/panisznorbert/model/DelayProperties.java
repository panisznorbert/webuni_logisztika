package hu.webuni.logisztika.panisznorbert.model;

public class DelayProperties {

    Long milestoneId;

    int delay;

    public DelayProperties(Long milestoneId, int delay) {
        this.milestoneId = milestoneId;
        this.delay = delay;
    }

    public Long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Long milestoneId) {
        this.milestoneId = milestoneId;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
