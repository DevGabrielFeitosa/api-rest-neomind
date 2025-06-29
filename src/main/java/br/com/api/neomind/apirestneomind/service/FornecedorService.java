package br.com.api.neomind.apirestneomind.service;

import br.com.api.neomind.apirestneomind.dto.FornecedorDTO;
import br.com.api.neomind.apirestneomind.entity.Fornecedor;
import br.com.api.neomind.apirestneomind.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FornecedorService {

    @Inject
    private FornecedorRepository fornecedorRepository;

    public List<FornecedorDTO> findAll() {
        return fornecedorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FornecedorDTO findById(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado com ID: " + id));
        return toDTO(fornecedor);
    }

    public FornecedorDTO create(FornecedorDTO dto) {
        if (fornecedorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Já existe um fornecedor com este email: " + dto.getEmail());
        }

        if (fornecedorRepository.findByCnpj(dto.getCnpj()).isPresent()) {
            throw new BadRequestException("Já existe um fornecedor com este CNPJ: " + dto.getCnpj());
        }

        Fornecedor fornecedor = toEntity(dto);
        fornecedor = fornecedorRepository.save(fornecedor);
        return toDTO(fornecedor);
    }

    public FornecedorDTO update(Long id, FornecedorDTO dto) {
        Fornecedor existingFornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado com ID: " + id));

        if (fornecedorRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new BadRequestException("Já existe outro fornecedor com este email: " + dto.getEmail());
        }

        if (fornecedorRepository.existsByCnpjAndIdNot(dto.getCnpj(), id)) {
            throw new BadRequestException("Já existe outro fornecedor com este CNPJ: " + dto.getCnpj());
        }

        existingFornecedor.setName(dto.getName());
        existingFornecedor.setEmail(dto.getEmail());
        existingFornecedor.setComment(dto.getComment());
        existingFornecedor.setCnpj(dto.getCnpj());

        existingFornecedor = fornecedorRepository.update(existingFornecedor);
        return toDTO(existingFornecedor);
    }

    public void delete(Long id) {
        if (!fornecedorRepository.findById(id).isPresent()) {
            throw new NotFoundException("Fornecedor não encontrado com ID: " + id);
        }
        fornecedorRepository.delete(id);
    }

    private FornecedorDTO toDTO(Fornecedor entity) {
        return new FornecedorDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getComment(),
                entity.getCnpj()
        );
    }

    private Fornecedor toEntity(FornecedorDTO dto) {
        return new Fornecedor(
                dto.getName(),
                dto.getEmail(),
                dto.getComment(),
                dto.getCnpj()
        );
    }
}
