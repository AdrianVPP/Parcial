package com.parcial.paciente.controller;

import com.parcial.paciente.dto.PacienteRequestDTO;
import com.parcial.paciente.dto.PacienteResponseDTO;
import com.parcial.paciente.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Pacientes", description = "API para gestionar pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo paciente")
    public ResponseEntity<PacienteResponseDTO> create(@Valid @RequestBody PacienteRequestDTO request) {
        return new ResponseEntity<>(pacienteService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los pacientes")
    public ResponseEntity<List<PacienteResponseDTO>> findAll() {
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un paciente por ID")
    public ResponseEntity<PacienteResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un paciente existente")
    public ResponseEntity<PacienteResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PacienteRequestDTO request) {
        return ResponseEntity.ok(pacienteService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un paciente")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}