package com.projectsvadim.project3restapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name = "Sensor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sensor implements Serializable { // в случае если мы работаем не числовыми ключами

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2, max = 30, message = "Название должно быть от 2 до 30 символов")
    @Column(name = "name")
    String name;
//    @OneToMany(mappedBy = "sensor")
//    List<Measurements> measurements;
}
