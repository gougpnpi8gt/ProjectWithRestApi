package com.projectsvadim.project3restapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    Integer id;

    @NotNull(message = "Значение температуры не может быть пустым")
    @Min(value = -100, message = "Значение должно быть больше чем -100")
    @Max(value = 100,  message = "Значение должно быть меньше чем +100")
    @Column(name = "value")
    Double value;
    /*
    Double - если не перадем температуру, будет null
    double - если не передаем температуру, будет 0.0, так как примитивные типы не могут хранить null;
     */

    @NotNull(message = "Значение raining не может быть пустым")
    @Column(name = "raining")
    Boolean raining;

    @NotNull(message = "Сенсор не может быть пустым")
    @ManyToOne
    @JoinColumn(name = "sensor",
            referencedColumnName = "name")
    Sensor sensor;

    @Column(name = "date_created_at")
    @NotNull
    //@Temporal(TemporalType.TIMESTAMP)
    LocalDateTime dateCreatedAt;

    public Boolean isRaining() {
        return raining;
    }
}
