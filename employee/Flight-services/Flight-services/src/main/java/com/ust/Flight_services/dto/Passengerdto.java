package com.ust.Flight_services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passengerdto {
    private String pnr;
    private String name;
    private String email;
    private String phonenumber;
    private String address;
    private String flightNumber;
}
