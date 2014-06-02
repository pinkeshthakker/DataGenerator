package com.test.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FILE_DATA")
public class DataObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="dataObject_Sequence")
	@SequenceGenerator(name="dataObject_Sequence", sequenceName="dataObject_Sequence")
	@Column(name = "ID", nullable = false)
	private Long id;
	@Column(name="FIRSTVALUE")
	private String firstValue;
	@Column(name="SECONDVALUE")
	private String secondVAlue;
	@Column(name="THIRDVALUE")
	private String thirdValue;
	@Column(name="FORTHVALUE")
	private String fourthValue;
	@Column(name="FIFTHVALUE")
	private String fifthValue;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstValue() {
		return firstValue;
	}
	public void setFirstValue(String firstValue) {
		this.firstValue = firstValue;
	}
	public String getSecondVAlue() {
		return secondVAlue;
	}
	public void setSecondVAlue(String secondVAlue) {
		this.secondVAlue = secondVAlue;
	}
	public String getThirdValue() {
		return thirdValue;
	}
	public void setThirdValue(String thirdValue) {
		this.thirdValue = thirdValue;
	}
	public String getFourthValue() {
		return fourthValue;
	}
	public void setFourthValue(String fourthValue) {
		this.fourthValue = fourthValue;
	}
	public String getFifthValue() {
		return fifthValue;
	}
	public void setFifthValue(String fifthValue) {
		this.fifthValue = fifthValue;
	}
	
	
}
