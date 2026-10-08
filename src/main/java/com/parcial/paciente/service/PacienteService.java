package com.parcial.paciente.service;

import com.parcial.paciente.dto.PacienteRequestDTO;
import com.parcial.paciente.dto.PacienteResponseDTO;

import java.util.List;

public interface PacienteService {
    PacienteResponseDTO create(PacienteRequestDTO request);
    List<PacienteResponseDTO> findAll();
    PacienteResponseDTO findById(Long id);
    PacienteResponseDTO update(Long id, PacienteRequestDTO request);
    void delete(Long id);
}