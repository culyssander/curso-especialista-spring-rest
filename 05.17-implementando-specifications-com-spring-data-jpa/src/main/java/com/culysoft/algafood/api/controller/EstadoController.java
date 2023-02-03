package com.culysoft.algafood.api.controller;

import com.culysoft.algafood.domain.model.Estado;
import com.culysoft.algafood.domain.repository.EstadoRepository;
import com.culysoft.algafood.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscaPeloId(@PathVariable Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        if (estado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.ok(estado.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado salva(@RequestBody Estado estado) {
        return estadoService.salva(estado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> actualiza(@PathVariable Long id, @RequestBody Estado estado) {
        Optional<Estado> estadoAtualOptional = estadoRepository.findById(id);

        if (estadoAtualOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Estado estadoAtual = estadoAtualOptional.get();
        BeanUtils.copyProperties(estado, estadoAtual, "id");

        estadoService.salva(estadoAtual);
        return ResponseEntity.ok(estadoAtual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estado> remove(@PathVariable Long id) {
        try {
            Optional<Estado> estadoAtualOptional = estadoRepository.findById(id);

            if (estadoAtualOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            estadoService.remove(id);

            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
