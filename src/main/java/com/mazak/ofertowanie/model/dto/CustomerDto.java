package com.mazak.ofertowanie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String name;
    private Long nip;
    private String adress;
    private String phoneNumber;

//    public CustomerDto(String name) {
//        this.name = name;
//    }

//        public static Customer create (CustomerDto customerDto){
//        return new Customer(
//                customerDto.getName(),
//                customerDto.getNip(),
//                customerDto.getAdress(),
//                customerDto.getPhoneNumber());
//    }
}
