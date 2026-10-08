package com.parcial.paciente.service.impl;

import com.parcial.paciente.dto.PacienteRequestDTO;
import com.parcial.paciente.dto.PacienteResponseDTO;
import com.parcial.paciente.model.Paciente;
import com.parcial.paciente.repository.PacienteRepository;
import com.parcial.paciente.service.PacienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteResponseDTO create(PacienteRequestDTO request) {
        Paciente paciente = new Paciente();
        paciente.setNombre(request.getNombre());
        paciente.setApellido(request.getApellido());
        paciente.setEdad(request.getEdad());
        paciente.setTelefono(request.getTelefono());
        return toResponse(pacienteRepository.save(paciente));
    }

    @Override
    public List<PacienteResponseDTO> findAll() {
        return pacienteRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public PacienteResponseDTO findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
        return toResponse(paciente);
    }

    @Override
    public PacienteResponseDTO update(Long id, PacienteRequestDTO request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
        paciente.setNombre(request.getNombre());
        paciente.setApellido(request.getApellido());
        paciente.setEdad(request.getEdad());
        paciente.setTelefono(request.getTelefono());
        return toResponse(pacienteRepository.save(paciente));
    }

    @Override
    public void delete(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Paciente no encontrado con id: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    private PacienteResponseDTO toResponse(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getEdad(),
                paciente.getTelefono()
        );
    }
}