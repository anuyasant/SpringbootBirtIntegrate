package com.ags.learn.integrate.springbootbirt.dto;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "orders")
public class Order {

	@Id
    @Column(name = "orderNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderNumber;
	
	@Column(name = "orderDate")
    @Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@Column(name = "requiredDate")
    @Temporal(TemporalType.DATE)
	private Date requiredDate;
	
	@Column(name = "shippedDate")
    @Temporal(TemporalType.DATE)
	private Date shippedDate;
	
	@Column(name = "status")
	private String status;

	@Column(name = "comments")
	private String comments;

	@Column(name = "customerNumber")
	private Long customerNumber;
}
