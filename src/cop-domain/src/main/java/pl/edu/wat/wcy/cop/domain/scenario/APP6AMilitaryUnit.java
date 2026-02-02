/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import pl.edu.wat.wcy.cop.domain.scenario.enums.app6a.AffiliationEnum;
import pl.edu.wat.wcy.cop.domain.scenario.enums.app6a.BattleDimensionEnum;
import pl.edu.wat.wcy.cop.domain.scenario.enums.app6a.UnitSizeEnum;
import pl.edu.wat.wcy.cop.domain.scenario.enums.app6a.UnitTypeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
// Stores APP-6A military unit details.
public class APP6AMilitaryUnit extends ScenarioPointObject {

	private String description;
	private String nameAdditional;

	private String code;
	private String codingScheme;

	private String status;

	@Enumerated(EnumType.STRING)
	private AffiliationEnum affiliation;
	@Enumerated(EnumType.STRING)
	private BattleDimensionEnum battleDimension;
	private String countryCode;
	private String functionId;
	private String orderOfBattle;
	@Enumerated(EnumType.STRING)
	private UnitSizeEnum mobilitySize;
	@Enumerated(EnumType.STRING)
	private UnitTypeEnum unitType;
	// to enable data migration
	private Boolean headquarters;

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

	public AffiliationEnum getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(AffiliationEnum affiliation) {
		this.affiliation = affiliation;
	}

	public BattleDimensionEnum getBattleDimension() {
		return battleDimension;
	}

	public void setBattleDimension(BattleDimensionEnum battleDimension) {
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

	public UnitSizeEnum getMobilitySize() {
		return mobilitySize;
	}

	public void setMobilitySize(UnitSizeEnum mobilitySize) {
		this.mobilitySize = mobilitySize;
	}

	public UnitTypeEnum getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitTypeEnum unitType) {
		this.unitType = unitType;
	}

	/**
	 * @return the headquarters
	 */
	public Boolean getHeadquarters() {
		return headquarters;
	}

	/**
	 * @param headquarters
	 *            the headquarters to set
	 */
	public void setHeadquarters(Boolean headquarters) {
		this.headquarters = headquarters;
	}

}
