package com.example.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Catagory {

    private int idCatagory;
    @NotEmpty
    private String nameCatagory;

    public Catagory(int idCatagory, String nameCatagory) {
        this.idCatagory = idCatagory;
        this.nameCatagory = nameCatagory;
    }
    public Catagory(){}

    public int getIdCatagory() {
        return idCatagory;
    }

    public void setIdCatagory(int idCatagory) {
        this.idCatagory = idCatagory;
    }

    public String getNameCatagory() {
        return nameCatagory;
    }

    public void setNameCatagory(String nameCatagory) {
        this.nameCatagory = nameCatagory;
    }

    @Override
    public String toString() {
        return "Catagory{" +
                "idCatagory=" + idCatagory +
                ", nameCatagory='" + nameCatagory + '\'' +
                '}';
    }
}
