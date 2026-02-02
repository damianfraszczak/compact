/**
 *
 */
package pl.edu.wat.wcy.cop.app.shared.response.capability;

import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.AffiliationEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.BattleDimensionEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitSizeEnumDto;
import pl.edu.wat.wcy.cop.app.shared.domain.enums.app6a.UnitTypeEnumDto;
// Represents app 6 a military unit capability.


public class App6AMilitaryUnitCapability extends ScenarioPointObjectCapability {
    private String code = ResponseUtils.STRING_NAME;
    private String nameAdditional = ResponseUtils.STRING_NAME;
    private String description = ResponseUtils.STRING_NAME;

    private String codingScheme = ResponseUtils.STRING_NAME;

    private String status = ResponseUtils.STRING_NAME;

    private String affiliation = ResponseUtils.oneOfOptions(AffiliationEnumDto.values());

    private String battleDimension = ResponseUtils.oneOfOptions(BattleDimensionEnumDto.values());
    private String countryCod = ResponseUtils.STRING_NAME;
    private String functionId = ResponseUtils.STRING_NAME;
    private String orderOfBattle = ResponseUtils.STRING_NAME;
    private String mobilitySize = ResponseUtils.oneOfOptions(UnitSizeEnumDto.values());
    private String unitType = ResponseUtils.oneOfOptions(UnitTypeEnumDto.values());

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the nameAdditional
     */
    public String getNameAdditional() {
        return nameAdditional;
    }

    /**
     * @param nameAdditional
     *            the nameAdditional to set
     */
    public void setNameAdditional(String nameAdditional) {
        this.nameAdditional = nameAdditional;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the codingScheme
     */
    public String getCodingScheme() {
        return codingScheme;
    }

    /**
     * @param codingScheme
     *            the codingScheme to set
     */
    public void setCodingScheme(String codingScheme) {
        this.codingScheme = codingScheme;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the affiliation
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * @param affiliation
     *            the affiliation to set
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    /**
     * @return the battleDimension
     */
    public String getBattleDimension() {
        return battleDimension;
    }

    /**
     * @param battleDimension
     *            the battleDimension to set
     */
    public void setBattleDimension(String battleDimension) {
        this.battleDimension = battleDimension;
    }

    /**
     * @return the countryCod
     */
    public String getCountryCod() {
        return countryCod;
    }

    /**
     * @param countryCod
     *            the countryCod to set
     */
    public void setCountryCod(String countryCod) {
        this.countryCod = countryCod;
    }

    /**
     * @return the functionId
     */
    public String getFunctionId() {
        return functionId;
    }

    /**
     * @param functionId
     *            the functionId to set
     */
    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    /**
     * @return the orderOfBattle
     */
    public String getOrderOfBattle() {
        return orderOfBattle;
    }

    /**
     * @param orderOfBattle
     *            the orderOfBattle to set
     */
    public void setOrderOfBattle(String orderOfBattle) {
        this.orderOfBattle = orderOfBattle;
    }

    /**
     * @return the mobilitySize
     */
    public String getMobilitySize() {
        return mobilitySize;
    }

    /**
     * @param mobilitySize
     *            the mobilitySize to set
     */
    public void setMobilitySize(String mobilitySize) {
        this.mobilitySize = mobilitySize;
    }

    /**
     * @return the unitType
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * @param unitType
     *            the unitType to set
     */
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

}
