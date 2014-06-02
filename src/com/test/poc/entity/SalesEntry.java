package com.test.poc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SALES_ENTRY")
public class SalesEntry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5963670447555005952L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="mediaOcean_sales_example")
	@SequenceGenerator(name="mediaOcean_sales_example", sequenceName="mediaOcean_sales_example")
	@Column(name = "SALES_ID", nullable = false)
	private long salesID;
	
	@Column(name = "SALEAMOUNT")
	private double saleAmount;
	
	@Column(name = "DATOFSALE")
	private Date dateOfSale;
	
	//@ManyToOne(targetEntity = com.nihilent.training.ItemBid.class)
	//@JoinColumn(name = "ID", nullable = false )	
	//@ManyToOne(cascade={CascadeType.ALL} )
	@ManyToOne
    @JoinColumn(name="PRODUCT_ID",insertable=false, updatable=false,
                nullable=false)
	private Product product;

	public long getSalesID() {
		return salesID;
	}

	public void setSalesID(long salesID) {
		this.salesID = salesID;
	}

	public double getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(double saleAmount) {
		this.saleAmount = saleAmount;
	}

	public Date getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(Date dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
