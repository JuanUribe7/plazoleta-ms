package com.example.plazoleta.ms_plazoleta.application.dto.response;

public class RestaurantSimpleResponseDto {

    private String name;
    private String urlLogo;

    public RestaurantSimpleResponseDto() {
    }
    public RestaurantSimpleResponseDto(String name, String urlLogo) {
        this.name = name;
        this.urlLogo = urlLogo;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
