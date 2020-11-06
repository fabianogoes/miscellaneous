package com.example.apidemo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "people")
public class Person {

    @Id @GeneratedValue
    private Long id;

    @NotNull(message = "name should not be null")
    @Size(min = 3, message = "name should has min 3 character")
    private String name;
    private String address;
    private String phone;

}
