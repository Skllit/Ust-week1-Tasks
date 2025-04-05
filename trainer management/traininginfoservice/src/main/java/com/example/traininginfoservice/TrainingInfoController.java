package com.example.traininginfoservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/traininginfos")
public class TrainingInfoController {

    @Autowired
    private TrainingInfoRepository trainingInfoRepository;

    @GetMapping
    public List<TrainingInfo> getAllTrainingInfos() {
        return trainingInfoRepository.findAll();
    }

    @GetMapping("/{id}")
    public TrainingInfo getTrainingInfoById(@PathVariable Long id) {
        return trainingInfoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public TrainingInfo createTrainingInfo(@RequestBody TrainingInfo trainingInfo) {
        return trainingInfoRepository.save(trainingInfo);
    }

    @PutMapping("/{id}")
    public TrainingInfo updateTrainingInfo(@PathVariable Long id, @RequestBody TrainingInfo updatedTrainingInfo) {
        if (trainingInfoRepository.existsById(id)) {
            updatedTrainingInfo.setTrainerId(id);
            return trainingInfoRepository.save(updatedTrainingInfo);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTrainingInfo(@PathVariable Long id) {
        trainingInfoRepository.deleteById(id);
    }
}
