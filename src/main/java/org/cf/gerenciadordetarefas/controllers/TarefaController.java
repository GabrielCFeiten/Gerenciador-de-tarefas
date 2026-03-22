package org.cf.gerenciadordetarefas.controllers;

import org.cf.gerenciadordetarefas.models.TarefaModel;
import org.cf.gerenciadordetarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<TarefaModel>> getAll() {
        List<TarefaModel> request = tarefaService.findAll();
        return ResponseEntity.ok(request);
    }

    @PostMapping
    public ResponseEntity<TarefaModel> create(@RequestBody TarefaModel tarefa) {
        TarefaModel request = tarefaService.save(tarefa);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefa.getId())
                .toUri();
        return  ResponseEntity.created(uri).body(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TarefaModel tarefa) {
        tarefaService.update(id, tarefa);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaModel> findById(@PathVariable Long id) {
        TarefaModel tarefa = tarefaService.findById(id);

        if (tarefa != null) {
            return ResponseEntity.ok(tarefa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
