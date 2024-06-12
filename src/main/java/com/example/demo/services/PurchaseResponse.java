package com.example.demo.services;

import com.example.demo.entities.StatusType;
import lombok.Data;

@Data
public class PurchaseResponse {
 private final String orderTrackingNumber;
 private StatusType status;
}
