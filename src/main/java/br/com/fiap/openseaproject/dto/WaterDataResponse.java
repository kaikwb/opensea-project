package br.com.fiap.openseaproject.dto;

import br.com.fiap.openseaproject.models.WaterData;

public record WaterDataResponse(
    Long id,
    Double latitude,
    Double longitude,
    Integer depth,
    Integer year,
    Double temperature,
    Double salinity,
    Double oxygen,
    Double phosphate,
    Double silicate,
    Double ph
) {
    public static WaterDataResponse fromEntity(WaterData waterData) {
        return new WaterDataResponse(
            waterData.getId(),
            waterData.getLatitude(),
            waterData.getLongitude(),
            waterData.getDepth(),
            waterData.getYear(),
            waterData.getTemperature(),
            waterData.getSalinity(),
            waterData.getOxygen(),
            waterData.getPhosphate(),
            waterData.getSilicate(),
            waterData.getPh()
        );
    }
}
