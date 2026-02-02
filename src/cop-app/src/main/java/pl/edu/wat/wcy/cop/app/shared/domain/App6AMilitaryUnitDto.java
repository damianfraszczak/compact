/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.domain;

import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.AffiliationEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.BattleDimensionEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitSizeEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitTypeEnumDto;
// Carries app 6 a military unit data.


public class App6AMilitaryUnitDto extends ScenarioPointObjectDto implements ISymbolOnTheMapWithCode<String> {

    private String code;
    private String nameAdditional;

    private String description;

    private String codingScheme;

    private String status;

    private AffiliationEnumDto affiliation = AffiliationEnumDto.FRIEND;

    private BattleDimensionEnumDto battleDimension = BattleDimensionEnumDto.GROUND;
    private String countryCode;
    private String functionId;
    private String orderOfBattle;
    private UnitSizeEnumDto mobilitySize = UnitSizeEnumDto.PLATOON;
    private UnitTypeEnumDto unitType = UnitTypeEnumDto.ARMOUR;
    private boolean headquarters;

    public App6AMilitaryUnitDto() {
        super();
    }

    /**
     * @param code
     * @param nameAdditional
     * @param point
     */
    public App6AMilitaryUnitDto(String code, String nameAdditional, GeoPointDto point) {
        this(point, code, nameAdditional);
    }

    /**
     *
     * @param point
     * @param code
     * @param nameAdditional
     */
    public App6AMilitaryUnitDto(GeoPointDto point, String code, String nameAdditional) {
        this(point, new MapSymbolDto(), code, nameAdditional);
    }

    /**
     * @param point
     */
    public App6AMilitaryUnitDto(GeoPointDto point) {
        super(point);
    }

    /**
     *
     * @param point
     * @param mapSymbol
     * @param code
     * @param nameAdditional
     */
    public App6AMilitaryUnitDto(GeoPointDto point, MapSymbolDto mapSymbol, String code, String nameAdditional) {
        super(point, mapSymbol);
        this.code = code;
        this.nameAdditional = nameAdditional;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodingScheme() {
        return codingScheme;
    }

    public void setCodingScheme(String codingScheme) {
        this.codingScheme = codingScheme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameAdditional() {
        return nameAdditional;
    }

    public void setNameAdditional(String nameAdditional) {
        this.nameAdditional = nameAdditional;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AffiliationEnumDto getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(AffiliationEnumDto affiliation) {
        this.affiliation = affiliation;
    }

    public BattleDimensionEnumDto getBattleDimension() {
        return battleDimension;
    }

    public void setBattleDimension(BattleDimensionEnumDto battleDimension) {
        this.battleDimension = battleDimension;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getOrderOfBattle() {
        return orderOfBattle;
    }

    public void setOrderOfBattle(String orderOfBattle) {
        this.orderOfBattle = orderOfBattle;
    }

    public UnitSizeEnumDto getMobilitySize() {
        return mobilitySize;
    }

    public void setMobilitySize(UnitSizeEnumDto mobilitySize) {
        this.mobilitySize = mobilitySize;
    }

    public UnitTypeEnumDto getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitTypeEnumDto unitType) {
        this.unitType = unitType;
    }

    /*
     * (non-Javadoc)
     *
     * @see pl.edu.wat.wcy.cop.app.shared.domain.MapPointSymbol#getName()
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return getNameAdditional();
    }

    /**
     * @return
     */
    public boolean isHeadquarters() {
        return headquarters;
    }

    /**
     * @param headquarters
     *            the headquarters to set
     */
    public void setHeadquarters(boolean headquarters) {
        this.headquarters = headquarters;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getCode() + " " + getDescription();
    }
}
