package com.nnk.springboot.domain;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
   // private Integer curveId;
    private Timestamp asOfDate;
    @NotNull(message = "Term cannot be null")
    private Double term;
    @NotNull(message = "Value cannot be null")
    private Double value;
    @CreationTimestamp
    @Column(updatable=false)
    private Timestamp creationDate;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/*
	public Integer getCurveId() {
		return curveId;
	}
	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}
	*/
	public Timestamp getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Timestamp asOfDate) {
		this.asOfDate = asOfDate;
	}
	public Double getTerm() {
		return term;
	}
	public void setTerm(Double term) {
		this.term = term;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public CurvePoint( Integer id, Timestamp asOfDate,
			Double term, Double value, Timestamp creationDate) {
		super();
		this.id = id;
	//	this.curveId = curveId;
		this.asOfDate = asOfDate;
		this.term = term;
		this.value = value;
		this.creationDate = creationDate;
	}
	public CurvePoint() {
		super();
	}
	@Override
	public String toString() {
		return "CurvePoint ["
				+ "id=" + id 
			//	+ ", curveId=" + curveId
				+ ", asOfDate="
				+ asOfDate + ", term=" + term + ", value=" + value
				+ ", creationDate=" + creationDate + "]";
	}
    
    
  
    }

 