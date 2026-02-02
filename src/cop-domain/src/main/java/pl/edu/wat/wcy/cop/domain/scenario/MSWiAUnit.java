/**
 * 
 */
package pl.edu.wat.wcy.cop.domain.scenario;

import javax.persistence.Entity;


@Entity
// Stores MSWiA unit details.
public class MSWiAUnit extends ScenarioPointObject {

	private String code;
	private String name;
	private String description;
	private String chapterDeliminator;
	private int formation;
	private int type;

	public MSWiAUnit() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChapterDeliminator() {
		return chapterDeliminator;
	}

	public void setChapterDeliminator(String chapterDeliminator) {
		this.chapterDeliminator = chapterDeliminator;
	}

	public int getFormation() {
		return formation;
	}

	public void setFormation(int formation) {
		this.formation = formation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
