package com.raktsetu.backend.enums;

public enum BloodGroup{
	A_POS("A+"), A_NEG("A-"), AB_POS("AB+"), AB_NEG("AB-"),
	B_POS("B+"), B_NEG("B-"), O_POS("O+"), O_NEG("O-");
	
	private final String label;
	
	BloodGroup(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
