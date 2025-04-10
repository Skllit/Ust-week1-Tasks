package com.ust.Flight_services.repo;


import com.ust.Flight_services.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Flightrepo extends JpaRepository<Flight,Long> {
    List<Flight> findByAirlinecode(String airlinecode);

    Optional<Flight> findByFlightNumber(String flightNumber);
}
