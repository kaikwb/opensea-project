package br.com.fiap.openseaproject.repositories;

import br.com.fiap.openseaproject.models.WaterData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WaterDataRepository extends JpaRepository<WaterData, Long> {
    public Page<WaterData> findAllByYearAndDepth(Integer year, Integer depth, Pageable pageable);
}
