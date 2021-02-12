package br.com.yes.repository.entity;

import br.com.yes.domain.Representative;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
public class RepresentativeEntity {

    @DynamoDBAttribute(attributeName = "Name")
    private String name;

    @DynamoDBAttribute(attributeName = "Cpf")
    private String cpf;

    @DynamoDBAttribute(attributeName = "Phone")
    private String phone;

    @DynamoDBAttribute(attributeName = "Email")
    private String email;

    public static RepresentativeEntity fromDomain(final Representative representative) {
        return RepresentativeEntity.builder()
                .name(representative.getName())
                .cpf(representative.getCpf())
                .phone(representative.getPhone())
                .email(representative.getEmail())
                .build();
    }

    public Representative toDomain() {
        return Representative.builder()
                .name(this.name)
                .cpf(this.cpf)
                .phone(this.phone)
                .email(this.email)
                .build();
    }
}
