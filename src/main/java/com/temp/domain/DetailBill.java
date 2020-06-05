package com.temp.domain;

import java.util.Date;
import java.util.List;

public class DetailBill {
    private List<ServiceRecord> serviceRecords;
    private int totalCost;
    private int totalServiceTime;
    private int serviceNum;
    private Date checkInDate;
    private Date checkOutDate;
    public DetailBill(List<ServiceRecord> serviceRecords) {
        this.serviceRecords = serviceRecords;
        analysis();
    }

    private void analysis(){
        totalCost = 0;
        totalServiceTime = 0;
        for(ServiceRecord sr : serviceRecords){
            totalCost += sr.getTotalCost();
            totalServiceTime += sr.getLastTime();
        }
        serviceNum = serviceRecords.size();
        return;
    }

    public List<ServiceRecord> getServiceRecords() {
        return serviceRecords;
    }

    public void setServiceRecords(List<ServiceRecord> serviceRecords) {
        this.serviceRecords = serviceRecords;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalServiceTime() {
        return totalServiceTime;
    }

    public void setTotalServiceTime(int totalServiceTime) {
        this.totalServiceTime = totalServiceTime;
    }

    public int getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(int serviceNum) {
        this.serviceNum = serviceNum;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
