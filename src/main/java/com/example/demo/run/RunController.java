package com.example.demo.run;
import com.example.demo.exceptions.RunDistanceNotFoundException;
import com.example.demo.exceptions.RunIdNotFoundException;
import com.example.demo.exceptions.RunLocationNotFoundException;
import com.example.demo.exceptions.RunTitleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/Home")
    public String home(){
        return "Hello Runner";
    }

    @GetMapping("")
    List<Run> showAll(){
        return runRepository.findAll();
    }

    @GetMapping("/id/{id}")
    Optional<Run> findById(@PathVariable Integer id){
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()){
            throw new RunIdNotFoundException();
        }
        return run;
    }

    @GetMapping("/title/{tile}")
    List<Run> findByTitle(@PathVariable String tile){
        List<Run> run = runRepository.findByTitle(tile);
        if(run.isEmpty()){
            throw new RunTitleNotFoundException();
        }
        return run;
    }
    @GetMapping("/distance/{distance}")
    List<Run> findByDistance(@PathVariable int distance){
        List<Run> run = runRepository.findByDistance(distance);
        if(run.isEmpty()){
            throw new RunDistanceNotFoundException();
        }
        return run;
    }

    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location){
        List<Run> run = runRepository.findByLocation(location);
        if(run.isEmpty()){
            throw new RunLocationNotFoundException();
        }
        return run;
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping ("")
    void createRun(@RequestBody Run run){
        runRepository.create(run);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void updateRun(@RequestBody Run run, @PathVariable Integer id){
        runRepository.update(run, id);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteRun(@PathVariable Integer id){
        runRepository.delete(id);
    }
}
