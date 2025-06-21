package com.java.business.HeadOffice.entity;

import java.io.Serializable;

public class TimePK implements Serializable {
    protected int invoice_no;
   
    

    public TimePK() {}

    public TimePK(Integer levelStation, Integer confPathID) {
        this.invoice_no = levelStation;
    }
 
}
