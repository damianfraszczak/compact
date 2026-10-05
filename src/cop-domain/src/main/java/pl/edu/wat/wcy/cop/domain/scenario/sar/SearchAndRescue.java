package pl.edu.wat.wcy.cop.domain.scenario.sar;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.edu.wat.wcy.cop.domain.GeoPoint;
import pl.edu.wat.wcy.cop.domain.scenario.FeatureStyle;
import pl.edu.wat.wcy.cop.domain.scenario.ScenarioPointObject;
import pl.edu.wat.wcy.cop.domain.scenario.sar.enums.PersonBehaviour;
import pl.edu.wat.wcy.cop.domain.scenario.sar.enums.SearchAndRescueAlgorithm;
import pl.edu.wat.wcy.cop.domain.scenario.sar.resources.HumanResource;
import pl.edu.wat.wcy.cop.domain.scenario.sar.resources.TechnicalResource;
import pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.SearchAndRescueSearchPlan;
import pl.edu.wat.wcy.cop.domain.scenario.sar.searchplan.domain.SearchPlanDomain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Entity
// Stores data for a search-and-rescue operation.
public class SearchAndRescue extends ScenarioPointObject {
    @Column(length = 1024)
    private String description;
    @Column(length = 10240)
    private String name;
    @Enumerated(EnumType.STRING)
    private PersonBehaviour personBehaviour;
    @Enumerated(EnumType.STRING)
    private SearchAndRescueAlgorithm algorithm;
    private FeatureStyle style;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<SearchAndRescueCircleZone> circleZones;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SearchAndRescueSegment> areaZones;
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SearchAndRescueGridZone> gridZones;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SearchUnit> searchUnits;


    @Lob
    @Basic(fetch = FetchType.EAGER)
    private SearchPlanDomain searchAndRescueSearchPlans[];


    private Integer age;

    private Boolean mountainTerrain;

    @Transient
    private boolean regeneratePlan;
    @Transient
    private boolean regeneratePOC;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "direction_lat")),
            @AttributeOverride(name = "lon", column = @Column(name = "direction_lon")),
            @AttributeOverride(name = "text", column = @Column(name = "direction_text"))
    })
    private GeoPoint directionPoint;

    // private String strokeColor = "F4A460";
    //    private Integer strokeWidth = 1;
    //    private Double strokeAlpha = 1.0;
    //    private String fillColor = "F4A460";
    //    private Double fillAlpha = 0.2;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "strokeColor", column = @Column(name = "direction_strokeColor")),
            @AttributeOverride(name = "strokeWidth", column = @Column(name = "direction_strokeWidth")),
            @AttributeOverride(name = "strokeAlpha", column = @Column(name = "direction_strokeAlpha")),
            @AttributeOverride(name = "fillColor", column = @Column(name = "direction_fillColor")),
            @AttributeOverride(name = "fillAlpha", column = @Column(name = "direction_fillAlpha")),
            @AttributeOverride(name = "dashArray", column = @Column(name = "direction_dashArray")),
    })
    private FeatureStyle directionStyle;

//    @JoinColumn(name = "sar_id")
//    @Fetch(value = FetchMode.SUBSELECT)
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<HumanResource> humanResources;
//    @JoinColumn(name = "sar_id")
//    @Fetch(value = FetchMode.SUBSELECT)
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<TechnicalResource> technicalResources;


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

    public List<SearchAndRescueCircleZone> getCircleZones() {
        return circleZones;
    }

    public List<SearchAndRescueSegment> getAreaZones() {
        return areaZones;
    }

    public void setAreaZones(List<SearchAndRescueSegment> areaZones) {
        this.areaZones = areaZones;
    }

    public void setCircleZones(List<SearchAndRescueCircleZone> circleZones) {
        this.circleZones = circleZones;
    }

    public FeatureStyle getStyle() {
        return style;
    }

    public void setStyle(FeatureStyle style) {
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SearchUnit> getSearchUnits() {
        return searchUnits;
    }

    public void setSearchUnits(List<SearchUnit> searchUnits) {
        this.searchUnits = searchUnits;
    }

    public List<SearchAndRescueGridZone> getGridZones() {
        return gridZones;
    }

    public void setGridZones(List<SearchAndRescueGridZone> gridZones) {
        this.gridZones = gridZones;
    }

    public GeoPoint getDirectionPoint() {
        return directionPoint;
    }

    public void setDirectionPoint(GeoPoint directionPoint) {
        this.directionPoint = directionPoint;
    }

    public FeatureStyle getDirectionStyle() {
        return directionStyle;
    }

    public void setDirectionStyle(FeatureStyle directionStyle) {
        this.directionStyle = directionStyle;
    }

    public List<SearchPlanDomain> getSearchAndRescueSearchPlansList() {
        if (getSearchAndRescueSearchPlans() == null) {
            return new LinkedList<>();
        }
        return new LinkedList<>(Arrays.asList(getSearchAndRescueSearchPlans()));
    }
    public void setSearchAndRescueSearchPlans(List<SearchPlanDomain> plans) {
        this.setSearchAndRescueSearchPlans(plans.toArray(new SearchPlanDomain[plans.size()]));
    }

    public SearchPlanDomain[] getSearchAndRescueSearchPlans() {
        return searchAndRescueSearchPlans;
    }

    public void setSearchAndRescueSearchPlans(SearchPlanDomain[] searchAndRescueSearchPlans) {
        this.searchAndRescueSearchPlans = searchAndRescueSearchPlans;
    }

    public boolean getRegeneratePlan() {
        return regeneratePlan;
    }

    public void setRegeneratePlan(boolean regeneratePlan) {
        this.regeneratePlan = regeneratePlan;
    }

    public boolean isRegeneratePlan() {
        return regeneratePlan;
    }

    public boolean isRegeneratePOC() {
        return regeneratePOC;
    }

    public void setRegeneratePOC(boolean regeneratePOC) {
        this.regeneratePOC = regeneratePOC;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Boolean getMountainTerrain() {
        return mountainTerrain;
    }

    public void setMountainTerrain(Boolean mountainTerrain) {
        this.mountainTerrain = mountainTerrain;
    }
}
