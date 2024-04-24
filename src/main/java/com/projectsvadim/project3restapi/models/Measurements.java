package com.projectsvadim.project3restapi.models;

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
@Entity
@Table(name = "Measurements")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Measurements {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotEmpty(message = "Значение температуры не может быть пустым")
    @Min(value = -100, message = "Значение должно быть больше чем -100")
    @Max(value = 100,  message = "Значение должно быть меньше чем +100")
    @Column(name = "value")
    int value;

    @NotEmpty(message = "Значение raining не может быть пустым")
    @Column(name = "raining")
    boolean raining;

    @NotEmpty(message = "Сенсор не может быть пустым")
    @Valid
    @ManyToOne
    @JoinColumn(name = "sensor_id",
            referencedColumnName = "id")
    Sensor sensor;

    @Column(name = "date_created_at")
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime dateCreatedAt;
}
