package br.com.fiap.openseaproject.controllers;

import br.com.fiap.openseaproject.dto.WaterDataRequest;
import br.com.fiap.openseaproject.dto.WaterDataResponse;
import br.com.fiap.openseaproject.models.WaterData;
import br.com.fiap.openseaproject.repositories.WaterDataRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/water-data")
public class WaterDataController {
    private final WaterDataRepository waterDataRepository;

    @Autowired
    public WaterDataController(WaterDataRepository waterDataRepository) {
        this.waterDataRepository = waterDataRepository;
    }

    @GetMapping
    public ResponseEntity<Page<WaterDataResponse>> getData(
        @RequestParam Integer year,
        @RequestParam Integer depth,
        @PageableDefault(size = 100) final Pageable pageable
    ) {
        Page<WaterData> waterData = waterDataRepository.findAllByYearAndDepth(year, depth, pageable);

        return ResponseEntity.ok(waterData.map(WaterDataResponse::fromEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaterDataResponse> getDataById(@PathVariable Long id) {
        return waterDataRepository.findById(id)
            .map(waterData -> ResponseEntity.ok(WaterDataResponse.fromEntity(waterData)))
            .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<WaterDataResponse> createData(@RequestBody @Valid WaterDataRequest waterData) {
        WaterData savedWaterData = waterDataRepository.save(waterData.toEntity());

        return ResponseEntity.created(URI.create("/water-data/" + savedWaterData.getId())).body(WaterDataResponse.fromEntity(savedWaterData));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<WaterDataResponse> updateData(@PathVariable Long id, @RequestBody @Valid WaterDataRequest waterData) {
        return waterDataRepository.findById(id)
            .map(waterDataEntity -> {
                waterDataEntity.setLatitude(waterData.latitude());
                waterDataEntity.setLongitude(waterData.longitude());
                waterDataEntity.setDepth(waterData.depth());
                waterDataEntity.setYear(waterData.year());
                waterDataEntity.setTemperature(waterData.temperature());
                waterDataEntity.setSalinity(waterData.salinity());
                waterDataEntity.setOxygen(waterData.oxygen());
                waterDataEntity.setPhosphate(waterData.phosphate());
                waterDataEntity.setSilicate(waterData.silicate());
                waterDataEntity.setPh(waterData.ph());

                WaterData updatedWaterData = waterDataRepository.save(waterDataEntity);

                return ResponseEntity.ok(WaterDataResponse.fromEntity(updatedWaterData));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        return waterDataRepository.findById(id)
            .map(waterData -> {
                waterDataRepository.delete(waterData);

                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
