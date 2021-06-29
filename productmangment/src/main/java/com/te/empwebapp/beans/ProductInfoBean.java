package com.te.empwebapp.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product_info")
public class ProductInfoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	private Integer pid;
	
	@Column
	private String pname;
	
	@Column
	private String mgDate;
	
	@Column
	private String exDate;
	
	@Column
	private int price;
	
	@Column
	private int quantity;

}