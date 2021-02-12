package br.com.yes.handler.excursion.dto;

import br.com.yes.domain.Excursion;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcursionDTO implements Serializable {

    private UUID id;
    private String departure;
    private Short armchairs;
    private String description;
    private UUID ownerId;
    private String destination;

    public Excursion toDomain() {
        return Excursion.builder()
                .id(this.id)
                .destination(this.destination)
                .departure(LocalDate.parse(this.departure))
                .armchairs(this.armchairs)
                .description(this.description)
                .ownerId(this.ownerId)
                .build();
    }

    public static ExcursionDTO fromDomain(final Excursion excursion) {
        return ExcursionDTO.builder()
                .id(excursion.getId())
                .destination(excursion.getDestination())
                .departure(excursion.getDeparture().toString())
                .armchairs(excursion.getArmchairs())
                .description(excursion.getDescription())
                .ownerId(excursion.getOwnerId())
                .build();
    }

}
