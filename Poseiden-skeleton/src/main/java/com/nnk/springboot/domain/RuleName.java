package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "rulename")
public class RuleName {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name  cannot be null")
    private String name;
    @NotBlank(message = "Description  cannot be null")
    private String description;
    @NotBlank(message = "Json  cannot be null")
    private String json;
    @NotBlank(message = "Template  cannot be null")
    private String template;
    @NotBlank(message = "SqlStr  cannot be null")
    private String sqlStr;
    @NotBlank(message = "SqlPart  cannot be null")
    private String sqlPart;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public String getSqlPart() {
		return sqlPart;
	}
	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}
	public RuleName(Integer id, String name, String description, String json,
			String template, String sqlStr, String sqlPart) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}
	public RuleName() {
		super();
	}
	@Override
	public String toString() {
		return "RuleName [id=" + id + ", name=" + name + ", description="
				+ description + ", json=" + json + ", template=" + template
				+ ", sqlStr=" + sqlStr + ", sqlPart=" + sqlPart + "]";
	}
    


}

