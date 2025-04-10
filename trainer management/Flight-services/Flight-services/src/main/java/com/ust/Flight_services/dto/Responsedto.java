package com.ust.Flight_services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Responsedto {
    private Flightdto flightdto;
    private List<Passengerdto> passengerdto;
}
