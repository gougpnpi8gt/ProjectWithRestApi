package com.projectsvadim.project3restapi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SensorDTO {

    @NotEmpty(message = "Название не может быть пустым")
    @Size(min = 2, max = 30, message = "Название должно быть от 2 до 30 символов")
    String name;

}
