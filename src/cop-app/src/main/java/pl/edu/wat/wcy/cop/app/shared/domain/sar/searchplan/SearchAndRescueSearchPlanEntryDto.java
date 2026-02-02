package pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan;

import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;

import java.util.List;
// Carries search and rescue search plan entry data.

public class SearchAndRescueSearchPlanEntryDto {

    private SearchAndRescueSegmentDto rawSegment;
    private SearchUnitDomainDto searchUnit;
    private List<SearchUnitDomainDto> searchUnits;
    private SegmentWorksetDto segment;

    public SearchAndRescueSearchPlanEntryDto() {
    }

    public SearchAndRescueSearchPlanEntryDto(SearchAndRescueSegmentDto rawSegment, List<SearchUnitDomainDto> searchUnits, SegmentWorksetDto segment) {
        this.rawSegment = rawSegment;
        this.searchUnits = searchUnits;
        this.segment = segment;
    }

    public SearchAndRescueSearchPlanEntryDto(SearchAndRescueSegmentDto rawSegment, SearchUnitDomainDto searchUnit, SegmentWorksetDto segment) {
        this.rawSegment = rawSegment;
        this.searchUnit = searchUnit;
        this.segment = segment;
    }

    public SearchUnitDomainDto getSearchUnit() {
        if (searchUnit == null) {
            searchUnit = new SearchUnitDomainDto();
        }
        return searchUnit;
    }

    public void setSearchUnit(SearchUnitDomainDto searchUnit) {
        this.searchUnit = searchUnit;
    }

    public SearchAndRescueSegmentDto getRawSegment() {
        return rawSegment;
    }

    public void setRawSegment(SearchAndRescueSegmentDto rawSegment) {
        this.rawSegment = rawSegment;
    }

    public SegmentWorksetDto getSegment() {
        return segment;
    }

    public void setSegment(SegmentWorksetDto segment) {
        this.segment = segment;
    }

    public String getKey() {
        String segmentKey = getRawSegment() != null ? getRawSegment().getKey() : getSegment().getSegment().getName();
        return segmentKey + "_" + (getSearchUnit().getType() == null ? "" : getSearchUnit().getType() + "_" + getSearchUnit().getName());
    }

    public Double getSweptArea() {
        if (getSegment() == null) {
            return 0.0;
        }
        return getSegment().getSweptArea();
    }


    public double getPoa() {
        if (getSegment() == null) {
            return 0.0;
        }
        return getSegment().getPoa();
    }

    public List<SearchUnitDomainDto> getSearchUnits() {
        return searchUnits;
    }

    public void setSearchUnits(List<SearchUnitDomainDto> searchUnits) {
        this.searchUnits = searchUnits;
    }

    public double getEsr() {
        if (getSegment() == null) {
            return 0.0;
        }
        return getSegment().getEsr();
    }


    public double getPod() {
        if (getSegment() == null) {
            return 0.0;
        }
        return getSegment().getPod();
    }


    public double getEc() {
        if (getSegment() == null) {
            return 0.0;
        }
        return getSegment().getEc();
    }


}
