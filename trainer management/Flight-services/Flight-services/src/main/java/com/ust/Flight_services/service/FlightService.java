package com.ust.Flight_services.service;

import com.ust.Flight_services.dto.Flightdto;
import com.ust.Flight_services.dto.Passengerdto;
import com.ust.Flight_services.dto.Responsedto;
import com.ust.Flight_services.model.Flight;
import com.ust.Flight_services.repo.Flightrepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    private Flightrepo repo;

    @Autowired
    private WebClient webClient;

    public Flight addFlight(Flight flight) {
        return repo.save(flight);
    }

    public List<Flight> getFlightsByAirline(String airlinecode) {
        return repo.findByAirlinecode(airlinecode);
    }

    @CircuitBreaker(name = "flightService", fallbackMethod = "fallbackGetAirline")
    public Responsedto getAirline(String flightNumber) {
        Responsedto responseDto = new Responsedto();
        Flight flight = repo.findByFlightNumber(flightNumber).orElseThrow(() -> new RuntimeException("Flight not found"));
        Flightdto flightDto = mapToFlightdto(flight);

        List<Passengerdto> passengerdtolist = webClient.get()
                .uri("http://localhost:9099/passangerinfo/" + flight.getFlightNumber())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Passengerdto>>() {})
                .block();

        responseDto.setFlightdto(flightDto);
        responseDto.setPassengerdto(passengerdtolist);

        return responseDto;
    }

    public Responsedto fallbackGetAirline(String flightNumber, Throwable throwable) {
        // Handle the fallback logic, e.g., return a default response or log the error
        return new Responsedto(); // Return an empty response or a default response
    }

    private Flightdto mapToFlightdto(Flight flight) {
        Flightdto flightdto = new Flightdto();
        flightdto.setId(flight.getId());
        flightdto.setFlightNumber(flight.getFlightNumber());
        flightdto.setAirlinecode(flight.getAirlinecode());
        flightdto.setDepartureLocation(flight.getDepartureLocation());
        flightdto.setArrivalLocation(flight.getArrivalLocation());
        flightdto.setDepartureTime(flight.getDepartureTime());
        flightdto.setArrivalTime(flight.getArrivalTime());
        flightdto.setDuration(flight.getDuration());
        flightdto.setPrice(flight.getPrice());
        flightdto.setAirline(flight.getAirline());
        return flightdto;
    }
}