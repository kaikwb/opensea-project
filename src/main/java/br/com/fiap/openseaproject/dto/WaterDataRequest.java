package br.com.fiap.openseaproject.dto;

import br.com.fiap.openseaproject.models.WaterData;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record WaterDataRequest(
    @NotNull
    @Min(-180)
    @Max(180)
    Double latitude,

    @NotNull
    @Min(-180)
    @Max(180)
    Double longitude,

    @NotNull
    @Min(0)
    Integer depth,

    @NotNull
    Integer year,

    Double temperature,
    Double salinity,
    Double oxygen,
    Double phosphate,
    Double silicate,
    Double ph
) {

    public WaterData toEntity() {
        return new WaterData(
            null,
            latitude(),
            longitude(),
            depth(),
            year(),
            temperature(),
            salinity(),
            oxygen(),
            phosphate(),
            silicate(),
            ph()
        );
    }
}
