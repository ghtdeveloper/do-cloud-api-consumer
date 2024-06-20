package com.neptune.domain;

import com.neptune.dto.request.address.CreateAddressDto;
import com.neptune.dto.response.address.AddressResponseDto;
import com.neptune.utils.ToDTO;
import com.neptune.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_address",schema = "core")
public class AddressEntity  implements TransformFrom<CreateAddressDto,AddressEntity>, ToDTO<AddressResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address_id_generator")
    @SequenceGenerator(name = "seq_address_id_generator", allocationSize = 1,
            sequenceName = "seq_address_id",schema = "core")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "postal_code",updatable = false)
    private String postalCode;

    @Column(name = "state",updatable = false)
    private String state;

    @Column(name = "town",updatable = false)
    private String town;

    @Column(name = "created_on",updatable = false)
    private LocalDate createdOn;

    @ManyToOne
    @JoinColumn(name = "person_id",updatable = false)
    private PersonEntity personEntity;

    @ManyToOne
    @JoinColumn(name = "country_id",updatable = false)
    private CountryEntity countryEntity;

    @Override
    public AddressResponseDto toDto() {
        return AddressResponseDto.builder()
                .id(this.id)
                .postalCode(this.postalCode)
                .createdOn(this.createdOn)
                .countryResponseDto(this.countryEntity.toDto())
                .town(this.town)
                .state(this.state)
                .build();
    }


    @Override
    public AddressEntity from(CreateAddressDto createAddressDto) {
        return AddressEntity.builder()
                .createdOn(LocalDate.now())
                .postalCode(createAddressDto.getPostalCode())
                .countryEntity(CountryEntity.builder().id(createAddressDto.getCountryId()).build())
                .build();
    }
}
