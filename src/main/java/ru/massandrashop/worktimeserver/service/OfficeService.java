package ru.massandrashop.worktimeserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.massandrashop.worktimeserver.exception.OfficeNotFoundException;
import ru.massandrashop.worktimeserver.model.Office;
import ru.massandrashop.worktimeserver.repository.OfficeJpaRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfficeService {

    private final OfficeJpaRepository officeJpaRepository;

    @Transactional
    @Cacheable("offices")
    public List<Office> findAllOffices() {
        return officeJpaRepository.readAllByIdNotNull()
                .sorted(Comparator.comparing(Office::getTitle))
                .collect(Collectors.toList());
    }

    @Transactional
    @CacheEvict("offices")
    public void addNewOffice(String title) {
        officeJpaRepository.save(new Office(title));
    }

    @Transactional
    public Office findById(Long id) {
        return officeJpaRepository.findById(id)
                .orElseThrow(() -> new OfficeNotFoundException(id));
    }


}
