package br.com.yes.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Representative implements Serializable {

    private String name;
    private String cpf;
    private String phone;
    private String email;
}
