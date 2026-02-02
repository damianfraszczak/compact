package pl.edu.wat.wcy.cop.app.shared.domain.sar;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gwt.user.client.rpc.GwtTransient;
import pl.edu.wat.wcy.cop.app.client.utils.StringUtils;
import pl.edu.wat.wcy.cop.app.shared.domain.FeatureStyleDto;
import pl.edu.wat.wcy.cop.app.shared.domain.GeoPointDto;
import pl.edu.wat.wcy.cop.app.shared.domain.ScenarioPointObjectDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.PersonBehaviour;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.enums.SearchAndRescueAlgorithm;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan.SearchPlanDomainDto;
import pl.edu.wat.wcy.cop.app.shared.domain.sar.searchplan.SearchPlanDomainWrapper;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
// Carries search and rescue data.

public class SearchAndRescueDto extends ScenarioPointObjectDto {
    private static int NEW_OBJ_COUNT = 1;
    private String name;
    private String description;
    private boolean regeneratePlan;

    private boolean regeneratePoc;

    private PersonBehaviour personBehaviour = PersonBehaviour.ABANDONED_VEHICLE;
    private SearchAndRescueAlgorithm algorithm = SearchAndRescueAlgorithm.SIMPLE;
    private boolean mountainTerrain = false;
    private List<SearchAndRescueCircleZoneDto> circleZones;
    private List<SearchAndRescueSegmentDto> areaZones;
    private List<SearchAndRescueGridZoneDto> gridZones;
    private List<SearchUnitDto> searchUnits;

    private List<SearchPlanDomainDto> searchAndRescueSearchPlans;
    @GwtTransient
    @JsonIgnore
    private transient List<SearchPlanDomainWrapper> searchAndRescueSearchPlansWrapper;

    @JsonIgnore
    public List<SearchPlanDomainWrapper> getSearchAndRescueSearchPlansWrapper() {
        searchAndRescueSearchPlansWrapper = getSearchAndRescueSearchPlans().stream().map(x -> new SearchPlanDomainWrapper(x, this)).collect(Collectors.toList());
        return searchAndRescueSearchPlansWrapper;
    }


    private FeatureStyleDto style = new FeatureStyleDto();

    private GeoPointDto directionPoint = new GeoPointDto();
    private FeatureStyleDto directionStyle = new FeatureStyleDto();

    private int age;


    public SearchAndRescueDto() {
    }

    public SearchAndRescueDto(GeoPointDto point) {
        super(point);
    }

    public SearchAndRescueDto(GeoPointDto point, SearchAndRescueAlgorithm algorithm) {
        super(point);
        this.algorithm = algorithm;
    }

    public String getName() {
        if (StringUtils.isNullOrEmpty(name)) {
            setName("Strefa #" + NEW_OBJ_COUNT++);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PersonBehaviour getPersonBehaviour() {
        return personBehaviour;
    }

    public void setPersonBehaviour(PersonBehaviour personBehaviour) {
        this.personBehaviour = personBehaviour;
    }

    public SearchAndRescueAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(SearchAndRescueAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public List<SearchAndRescueCircleZoneDto> getCircleZones() {
        if (circleZones == null) {
            circleZones = new LinkedList<>();
        }
        return circleZones;
    }

    public List<SearchPlanDomainDto> getSearchAndRescueSearchPlans() {
        if(searchAndRescueSearchPlans == null){
            searchAndRescueSearchPlans = new LinkedList<>();
        }
        return searchAndRescueSearchPlans;
    }

    public void setSearchAndRescueSearchPlans(List<SearchPlanDomainDto> searchAndRescueSearchPlans) {
        this.searchAndRescueSearchPlans = searchAndRescueSearchPlans;
    }

    public void setCircleZones(List<SearchAndRescueCircleZoneDto> circleZones) {
        this.circleZones = circleZones;
    }

    public FeatureStyleDto getStyle() {
        return style;
    }

    public void setStyle(FeatureStyleDto style) {
        this.style = style;
    }

    public List<SearchAndRescueSegmentDto> getAreaZones() {

        if (areaZones == null) {
            areaZones = new LinkedList<>();
        }
        return areaZones;
    }

    public void setAreaZones(List<SearchAndRescueSegmentDto> areaZones) {
        this.areaZones = areaZones;
    }

    public List<SearchUnitDto> getSearchUnits() {
        if (searchUnits == null) {
            searchUnits = new LinkedList<>();
        }
        return searchUnits;
    }

    public List<SearchAndRescueGridZoneDto> getGridZones() {
        if (gridZones == null) {
            gridZones = new LinkedList<>();
        }
        return gridZones;
    }

    public void setGridZones(List<SearchAndRescueGridZoneDto> gridZones) {
        this.gridZones = gridZones;
    }

    public void setSearchUnits(List<SearchUnitDto> searchUnits) {
        this.searchUnits = searchUnits;
    }


    public GeoPointDto getDirectionPoint() {

        if (directionPoint == null) {
            directionPoint = new GeoPointDto();
        }
        return directionPoint;
    }

    public void setDirectionPoint(GeoPointDto directionPoint) {
        this.directionPoint = directionPoint;
    }

    public FeatureStyleDto getDirectionStyle() {
        if (directionStyle == null) {
            directionStyle = new FeatureStyleDto();
        }
        return directionStyle;
    }

    public void setDirectionStyle(FeatureStyleDto directionStyle) {
        this.directionStyle = directionStyle;
    }

//    public double getProbabilityRoW() {
//        return getSearchAndRescueSearchPlan() != null ? getSearchAndRescueSearchPlan().getProbabilityRoW() : 0;
//    }
//
//    public double getProbabilityOfSuccess(){
//        return getSearchAndRescueSearchPlan() != null ? getSearchAndRescueSearchPlan().getProbabilityOfSuccess() : 0;
//    }
//
//    public void setProbabilityOfSuccess(double probabilityOfSuccess) {
//        if(this.getSearchAndRescueSearchPlan() != null){
//            getSearchAndRescueSearchPlan().setProbabilityOfSuccess(probabilityOfSuccess);
//        }
//    }
//    public void setProbabilityRoW(double probabilityRoW) {
//
//        if(this.getSearchAndRescueSearchPlan() != null){
//            getSearchAndRescueSearchPlan().setProbabilityRoW(probabilityRoW);
//        }
//    }


    public boolean isRegeneratePlan() {
        return regeneratePlan;
    }

    public void setRegeneratePlan(boolean regeneratePlan) {
        this.regeneratePlan = regeneratePlan;
    }

    public boolean isRegeneratePoc() {
        return regeneratePoc;
    }

    public void setRegeneratePoc(boolean regeneratePoc) {
        this.regeneratePoc = regeneratePoc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public boolean isMountainTerrain() {
        return mountainTerrain;
    }

    public void setMountainTerrain(boolean mountainTerrain) {
        this.mountainTerrain = mountainTerrain;
    }
}
