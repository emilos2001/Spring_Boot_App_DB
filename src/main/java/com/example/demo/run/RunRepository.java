package com.example.demo.run;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Positive;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class RunRepository {

    private final JdbcClient jdbcClient;
    private static final Logger logger = Logger.getLogger(RunRepository.class.getName());

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run").query(Run.class).list();
    }

    Optional<Run> findById(Integer id) {
        return jdbcClient.sql("select * from Run where id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    List<Run> findByTitle(String title) {
        return jdbcClient.sql("select * from Run where title = :title").
                param("title", title).
                query(Run.class).list();
    }

    List<Run> findByDistance(Integer distance) {
        return jdbcClient.sql("select * from Run where distance = :distance").
                param("distance", distance).
                query(Run.class).list();
    }

    List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from Run where location = :location").
                param("location", location).
                query(Run.class).list();
    }

    public int count(){
        return jdbcClient.sql("select * from Run").query().listOfRows().size();
    }

    public void update(Run run, Integer id) {
        var update = jdbcClient.sql("update Run set title = ?, started_on = ?, completed_on = ?, distance = ?, location = ? where id =  ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.distance(), run.location().toString(), id))
                .update();
        Assert.state(update == 1, "Update failed for : " + run.title());
    }

    public void create(Run run) {
        var create = jdbcClient.sql("insert into Run(id, title, started_on, completed_on, distance, location) values (?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.distance(), run.location().toString()))
                .update();
        Assert.state(create == 1, "Create failed for : " + run.id());
    }

    public void delete(Integer id) {
        var delete = jdbcClient.sql("delete from Run where id = :id")
                .param("id", id)
                .update();
        Assert.state(delete == 1, "Delete failed for : " + id);
    }

}