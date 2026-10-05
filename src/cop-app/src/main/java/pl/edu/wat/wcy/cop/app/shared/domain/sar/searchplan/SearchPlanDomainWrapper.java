package pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan;

import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.SearchAndRescueSegmentDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.Qualification;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.resources.HumanResourceDto;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
// Represents search plan domain wrapper.

public class SearchPlanDomainWrapper {
    private transient SearchPlanDomainDto domain;
    private transient SearchAndRescueDto sar;
    private transient List<SearchAndRescueSearchPlanEntryDto> searchAndRescueSearchPlanDetailed;

    private int numberOfExperienced;
    private int numberOfUnexperienced;

    public SearchPlanDomainWrapper() {
    }

    public SearchPlanDomainWrapper(SearchPlanDomainDto domain, SearchAndRescueDto sar) {
        this.domain = domain;
        this.sar = sar;
    }

    public int getNumberOfExperienced() {
        return domain.getHumanResources().stream().filter(x -> Qualification.HIGH == x.getQualification())
                .mapToInt(x -> x.getNumber()).sum();
    }

    public void setNumberOfExperienced(int numberOfExperienced) {
        List<HumanResourceDto> hh = domain.getHumanResources().stream().filter(x -> Qualification.LOW == x.getQualification())
                .collect(Collectors.toList());
        hh.add(new HumanResourceDto(numberOfExperienced, Qualification.HIGH));
        domain.setHumanResources(hh);
    }

    public int getNumberOfUnexperienced() {
        return domain.getHumanResources().stream().filter(x -> Qualification.LOW == x.getQualification())
                .mapToInt(x -> x.getNumber()).sum();
    }

    public void setNumberOfUnexperienced(int numberOfUnexperienced) {
        List<HumanResourceDto> hh = domain.getHumanResources().stream().filter(x -> Qualification.HIGH == x.getQualification())
                .collect(Collectors.toList());
        hh.add(new HumanResourceDto(numberOfUnexperienced, Qualification.LOW));
        domain.setHumanResources(hh);
    }

    public SearchPlanDomainDto getDomain() {
        return domain;
    }

    public void setDomain(SearchPlanDomainDto domain) {
        this.domain = domain;
    }

    public SearchAndRescueDto getSar() {
        return sar;
    }

    public void setSar(SearchAndRescueDto sar) {
        this.sar = sar;
    }

    public List<SearchAndRescueSearchPlanEntryDto> getSearchAndRescueSearchPlanDetailed() {
        if (domain.getSegmentWorksets() != null && !domain.getSegmentWorksets().isEmpty()) {
            searchAndRescueSearchPlanDetailed = new LinkedList<>();
            domain.getSegmentWorksets().forEach(segmentWorker -> {
//                if (segmentWorker.getUnits() != null && !segmentWorker.getUnits().isEmpty()) {
//                    segmentWorker.getUnits().forEach(searchUnit -> {
//                        searchAndRescueSearchPlanDetailed.add(new SearchAndRescueSearchPlanEntryDto(
//                                sar.getAreaZones().stream().filter(x -> x.getName() == segmentWorker.getSegment().getName()).findFirst().orElse(null),
//                                searchUnit,
//                                segmentWorker
//                        ));
//                    });
//                } else {
//                    searchAndRescueSearchPlanDetailed.add(new SearchAndRescueSearchPlanEntryDto(
//                            sar.getAreaZones().stream().filter(x -> x.getName() == segmentWorker.getSegment().getName()).findFirst().orElse(null),
//                            new SearchUnitDomainDto(),
//                            segmentWorker
//                    ));
//                }
                searchAndRescueSearchPlanDetailed.add(new SearchAndRescueSearchPlanEntryDto(
                        sar.getAreaZones().stream().filter(x -> x.getName() == segmentWorker.getSegment().getName()).findFirst().orElse(null),
                        segmentWorker.getUnits(),
                        segmentWorker
                ));


            });
        } else {
            searchAndRescueSearchPlanDetailed = new LinkedList<>();
            List<SearchAndRescueSegmentDto> az = sar.getAreaZones();
            az.forEach(zone -> {
                searchAndRescueSearchPlanDetailed.add(new SearchAndRescueSearchPlanEntryDto(
                        zone,
                        new SearchUnitDomainDto(),
                        new SegmentWorksetDto(zone.getPoc(), new SegmentDomainDto(
                                zone.getName(),
                                zone.getDescription(),
                                zone.getArea(),
                                zone.getTerrainCategory()
                        ))
                ));
            });
        }
        return searchAndRescueSearchPlanDetailed;
    }

    public List<SegmentWorksetDto> getSegmentWorksets() {
        return domain.getSegmentWorksets();
    }

    public void setSegmentWorksets(List<SegmentWorksetDto> segmentWorksets) {
        domain.setSegmentWorksets(segmentWorksets);
    }


    public long getProcessingTime() {
        return domain.getProcessingTime();
    }

    public void setProcessingTime(long processingTime) {
        domain.setProcessingTime(processingTime);
    }
}
