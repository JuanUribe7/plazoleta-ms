package com.example.plazoleta.ms_plazoleta.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RestaurantRequestDto {

        @NotBlank
        private String name;
        private String nit;
        private String address;
        private String phone;
        private String urlLogo;
        private Long ownerId;

        public RestaurantRequestDto() {
        }


        public RestaurantRequestDto(String name, String nit, String address, String phone, String urlLogo, Long ownerId) {
        this.name = name;
        this.nit = nit;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.ownerId = ownerId;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

    public void setUrlLogo(String logoUrl) {
        this.urlLogo = logoUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
