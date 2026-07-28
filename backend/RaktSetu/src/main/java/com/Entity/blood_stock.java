package com.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "blood_requests")
public class blood_stock {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int request_id;
	
	

}
