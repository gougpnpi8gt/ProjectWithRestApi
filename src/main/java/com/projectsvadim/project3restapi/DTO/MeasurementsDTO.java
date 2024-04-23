package com.projectsvadim.project3restapi.DTO;

import com.projectsvadim.project3restapi.models.Sensor;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeasurementsDTO {

    @NotEmpty(message = "Значение температуры не может быть пустым")
    @Min(value = -100, message = "Значение должно быть больше чем -100")
    @Max(value = 100,  message = "Значение должно быть меньше чем +100")
    int value;

    @NotEmpty(message = "Сенсор не может быть пустым")
    @Valid
    Sensor sensor;

    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime dateCreatedAt;
}
