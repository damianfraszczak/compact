package pl.edu.wat.wcy.cop.domain.scenario;

import javax.persistence.*;

/**
 * 
 * @author Damian Frąszczak
 * @since 23 lut 2017
 */
@Entity
// Stores ADatP3 report data.
public class ADatP3Report extends ScenarioObject {
	@Basic(fetch = FetchType.EAGER)
	@Lob
	private byte[] byteContent;

	@Transient
	private String content;

	/**
	 * @return the content
	 */
	public byte[] getByteContent() {
		return byteContent;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setByteContent(byte[] byteContent) {
		this.byteContent = byteContent;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@PostLoad
	public void loadStringContent() {
		if (byteContent != null) {
			setContent(new String(byteContent));
		}
	}

	@PrePersist
	@PreUpdate
	public void loadByteContent() {
		if (content != null) {
			setByteContent(content.getBytes());
		}
	}
}
