package com.test.poc.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5963670447555005952L;

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="mediaOcean_example")
	@SequenceGenerator(name="mediaOcean_example", sequenceName="mediaOcean_example")
	@Column(name = "PRODUCT_ID", nullable = false)
	private long productId;

	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	/* @OneToMany annotation defines a many-valued association with one-to-many multiplicity.
	If the collection is defined using generics to specify the element type, the associated target entity type need not be specified; 
	otherwise the target entity class must be specified.The association may be bidirectional. In a bidirectional relationship, 
	one of the sides (and only one) has to be the owner: the owner is responsible for the association column(s) update. 
	To declare a side as not responsible for the relationship, the attribute mappedBy is used. mappedBy refers to the property name of 
	the association on the owner side. In our case, 	this is passport. As you can see, you don’t have to (must not) declare the join column 
	since it has already been declared on the owners side.*/
	//@OneToMany(mappedBy = "item")
	@OneToMany(cascade={CascadeType.ALL})
	//@OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE})
    @JoinColumn(name="PRODUCT_ID")
	private Set<SalesEntry> salesEntry = new HashSet<SalesEntry>();
	
	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Set<SalesEntry> getSalesEntry() {
		return salesEntry;
	}


	public void setSalesEntry(Set<SalesEntry> salesEntry) {
		this.salesEntry = salesEntry;
	}


	public long getProductId() {
		return productId;
	}


	public void setProductId(long productId) {
		this.productId = productId;
	}
	
}
