package com.projectsvadim.project3restapi.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeasurementsDTO {

    @NotNull(message = "Значение температуры не может быть пустым")
    @Min(value = -100, message = "Значение должно быть больше чем -100")
    @Max(value = 100,  message = "Значение должно быть меньше чем +100")
    Double value;

    @NotNull(message = "Значение raining не может быть пустым")
    Boolean raining;

    @NotNull(message = "Сенсор не может быть пустым")
    SensorDTO sensor;

}
