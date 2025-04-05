package com.example.trainingzero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    @Autowired
    private TrainerRepository trainerRepository;

    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Trainer getTrainerById(@PathVariable Long id) {
        return trainerRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @PutMapping("/{id}")
    public Trainer updateTrainer(@PathVariable Long id, @RequestBody Trainer updatedTrainer) {
        if (trainerRepository.existsById(id)) {
            updatedTrainer.setId(id);
            return trainerRepository.save(updatedTrainer);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable Long id) {
        trainerRepository.deleteById(id);
    }

    @GetMapping("/courses")
    public List<Trainer> getTrainersByCourseDates(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return trainerRepository.findByCourseDates(startDate, endDate);
    }

    @GetMapping("/skills/{skill}")
    public List<Trainer> getTrainersBySkill(@PathVariable String skill) {
        return trainerRepository.findBySkill(skill);
    }
}