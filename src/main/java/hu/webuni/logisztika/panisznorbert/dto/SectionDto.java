package hu.webuni.logisztika.panisznorbert.dto;


public class SectionDto {

    private Long id;

    private MilestoneDto fromMilestone;

    private MilestoneDto toMilestone;

    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MilestoneDto getFromMilestone() {
        return fromMilestone;
    }

    public void setFromMilestone(MilestoneDto fromMilestone) {
        this.fromMilestone = fromMilestone;
    }

    public MilestoneDto getToMilestone() {
        return toMilestone;
    }

    public void setToMilestone(MilestoneDto toMilestone) {
        this.toMilestone = toMilestone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
