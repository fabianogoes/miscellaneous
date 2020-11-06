package com.example.apidemo.api;

import com.example.apidemo.model.Person;
import com.example.apidemo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/people")
public class PersonApi {

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity get() {
        log.info("get()...");
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        log.info("getOne({})...", id);
        return ResponseEntity.ok(service.read(id));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Person person) {
        log.info("post({})...", person);
        Person created = service.create(person);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@RequestBody Person person, @PathVariable Long id) {
        log.info("put({})...", person);
        service.update(id, person);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity patch(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
        log.info("patch({})...", updates);
        service.updatePartial(id, updates);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        log.info("delete({})...", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
