package com.example.models.response;

import java.util.List;

public class BillingDetails{
    private List<String> participants;
    private List<Bill> bills;

    public BillingDetails(List<String> participants, List<Bill> bills){
        this.participants = participants;
        this.bills = bills;
    }

    public List<String> getParticipants() {
        return this.participants;
    }
    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public List<Bill> getBills() {
        return this.bills;
    }
    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}