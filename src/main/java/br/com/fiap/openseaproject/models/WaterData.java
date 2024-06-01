package br.com.fiap.openseaproject.models;

import jakarta.persistence.*;

@Entity
@Table(
    name = "water_data",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_water_data",
            columnNames = {"latitude", "longitude", "depth", "year"}
        )
    }
)
public class WaterData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_water_data")
    @SequenceGenerator(name = "sq_water_data", sequenceName = "sq_water_data", allocationSize = 1)
    private Long id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "depth", nullable = false)
    private Integer depth;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "salinity")
    private Double salinity;

    @Column(name = "oxygen")
    private Double oxygen;

    @Column(name = "phosphate")
    private Double phosphate;

    @Column(name = "silicate")
    private Double silicate;

    @Column(name = "ph")
    private Double ph;

    public WaterData() {
    }

    public WaterData(Long id, Double latitude, Double longitude, Integer depth, Integer year, Double temperature, Double salinity, Double oxygen, Double phosphate, Double silicate, Double ph) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.year = year;
        this.temperature = temperature;
        this.salinity = salinity;
        this.oxygen = oxygen;
        this.phosphate = phosphate;
        this.silicate = silicate;
        this.ph = ph;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer time) {
        this.year = time;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getSalinity() {
        return salinity;
    }

    public void setSalinity(Double salinity) {
        this.salinity = salinity;
    }

    public Double getOxygen() {
        return oxygen;
    }

    public void setOxygen(Double oxygen) {
        this.oxygen = oxygen;
    }

    public Double getPhosphate() {
        return phosphate;
    }

    public void setPhosphate(Double phosphate) {
        this.phosphate = phosphate;
    }

    public Double getSilicate() {
        return silicate;
    }

    public void setSilicate(Double silicate) {
        this.silicate = silicate;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }
}
