package br.com.yes.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Excursion implements Serializable {

    private UUID id;
    private String destination;
    private LocalDate departure;
    private Short armchairs;
    private String description;
    private UUID ownerId;
}
