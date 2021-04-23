package guru.springframework.services;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.dtos.UnitOfMeasureDTO;
import guru.springframework.mappers.CycleAvoidingMappingContext;
import guru.springframework.mappers.UnitOfMeasureMapper;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UOMService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureMapper mapper;

    public UOMService(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureMapper mapper) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.mapper = mapper;
    }

    public Set<UnitOfMeasureDTO> getAllUoms() {
        Iterable<UnitOfMeasure> allUOMs = this.unitOfMeasureRepository.findAll();
        Set<UnitOfMeasureDTO> UOMDTOs = new HashSet<>();
        allUOMs.forEach(unitOfMeasure -> UOMDTOs.add(mapper.unitOfMeasureToDto(unitOfMeasure, new CycleAvoidingMappingContext())));
        
        return UOMDTOs;
    }
}
