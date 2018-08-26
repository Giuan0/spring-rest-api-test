package com.example.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bill{
	@JsonProperty("purchase_id")
	private Long purchaseId;
	private String description;
    private float paid;
	private double lent;
	@JsonProperty("n_participants")
	private Long nParticipants;
	@JsonProperty("is_owner")
	private boolean isOwner;
	@JsonProperty("owner_name")
	private String ownerName;
	@JsonProperty("owner_id")
    private Long ownerId;
	private double owes;
	
	public Bill(){}
	public Bill(Long purchaseId, float paid, String description, Long lent, Long nParticipants, boolean isOwner, String ownerName, Long ownerId, double owes) {
			super();
			this.purchaseId = purchaseId;
			this.paid = paid;
			this.description = description;
			this.lent = lent;
			this.nParticipants = nParticipants;
			this.isOwner = isOwner;
			this.ownerName = ownerName;
			this.ownerId = ownerId;
			this.owes = owes;
	}

	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	* Returns value of purchaseId
	* @return
	*/
	public Long getPurchaseId() {
		return purchaseId;
	}

	/**
	* Sets new value of purchaseId
	* @param
	*/
	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	/**
	* Returns value of paid
	* @return
	*/
	public float getPaid() {
		return paid;
	}

	/**
	* Sets new value of paid
	* @param
	*/
	public void setPaid(float paid) {
		this.paid = paid;
	}

	/**
	* Returns value of lent
	* @return
	*/
	public double getLent() {
		return lent;
	}

	/**
	* Sets new value of lent
	* @param
	*/
	public void setLent(double lent) {
		this.lent = lent;
	}

	/**
	* Returns value of nParticipants
	* @return
	*/
	
	public Long getNParticipants() {
		return nParticipants;
	}

	/**
	* Sets new value of nParticipants
	* @param
	*/
	public void setNParticipants(Long nParticipants) {
		this.nParticipants = nParticipants;
	}

	/**
	* Returns value of isOwner
	* @return
	*/
	public boolean isIsOwner() {
		return isOwner;
	}

	/**
	* Sets new value of isOwner
	* @param
	*/
	public void setIsOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	/**
	* Returns value of ownerName
	* @return
	*/
	public String getOwnerName() {
		return ownerName;
	}

	/**
	* Sets new value of ownerName
	* @param
	*/
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	* Returns value of ownerId
	* @return
	*/
	public Long getOwnerId() {
		return ownerId;
	}

	/**
	* Sets new value of ownerId
	* @param
	*/
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	/**
	* Returns value of owes
	* @return
	*/
	public double getOwes() {
		return owes;
	}

	/**
	* Sets new value of owes
	* @param
	*/
	public void setOwes(double owes) {
		this.owes = owes;
	}

}
