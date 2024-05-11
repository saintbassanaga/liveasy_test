package tech.saintbassanaga.liveasy_test.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.saintbassanaga.liveasy_test.entity.Load;
import tech.saintbassanaga.liveasy_test.repository.LoadRepository;
import tech.saintbassanaga.liveasy_test.services.LoadService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PayLoadController {
    private final LoadService loadService;
    private final LoadRepository loadRepository;

    public PayLoadController(LoadService loadService, LoadRepository loadRepository) {
        this.loadService = loadService;
        this.loadRepository = loadRepository;
    }

    @GetMapping(value = "load/{loadId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Load> findLoad(@PathVariable UUID loadId) {
        Optional<Load> load = loadService.findById(loadId);
        return load.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "load", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<String> createLoad(@RequestBody Load load) {
        return ResponseEntity.
                ok().body(loadService.createLoad(load));
    }


    @GetMapping(value = "load")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Load>> payLoadList(@RequestParam UUID shipperId) {
        List<Load> load = loadService.payLoadList(shipperId);
        if (load.isEmpty())
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(load);
    }

    @PutMapping(value = "load/{loadId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> update(@PathVariable UUID loadId, @RequestBody Load load) {
        loadService.updatePayLoad(loadId, load);
        return ResponseEntity.ok("Load Details Updated SuccessFully");
    }

    @DeleteMapping(value = "load/{loadId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable(value = "loadId") UUID uuid) {
        Optional <Load> load = loadRepository.findById(uuid);
        if (load.isPresent()){
            loadService.deletePayLoad(uuid);
            return "Load Deleted Successfully !";
        }
        return "Load Not found !";
    }
}
