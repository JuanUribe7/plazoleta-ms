package com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant;

public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String urlLogo;

    public RestaurantResponseDto() {
    }
    public RestaurantResponseDto(Long id, String name, String address, String urlLogo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.urlLogo = urlLogo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
