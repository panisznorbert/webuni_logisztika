package hu.webuni.logisztika.panisznorbert.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class TransportPlanDto {

    @JsonView(View.BaseData.class)
    private Long id;

    @JsonView(View.BaseData.class)
    private int expectedRevenue;

    @JsonView(View.BaseData.class)
    private List<SectionDto> sections;

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

    public List<SectionDto> getSections() {
        return sections;
    }

    public void setSections(List<SectionDto> sections) {
        this.sections = sections;
    }
}
