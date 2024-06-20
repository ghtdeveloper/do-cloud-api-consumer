package com.neptune.domain;

import com.neptune.dto.request.phone.CreatePhoneDto;
import com.neptune.dto.response.phone.PhoneResponseDto;
import com.neptune.utils.ToDTO;
import com.neptune.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Collections;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_phone",schema = "core")
public class PhoneEntity implements TransformFrom<CreatePhoneDto,PhoneEntity>, ToDTO<PhoneResponseDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_phone_id_generator")
    @SequenceGenerator(name = "seq_phone_id_generator", allocationSize = 1,
            sequenceName = "seq_phone_id",schema = "core")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "phone_number",updatable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "person_id",updatable = false)
    private PersonEntity personEntity;

    @ManyToOne
    @JoinColumn(name = "phone_type_id",updatable = false)
    private PhoneTypeEntity phoneTypeEntity;

    @Column(name = "created_on",updatable = false)
    private LocalDate createdOn;

    @Override
    public PhoneResponseDto toDto() {
        return PhoneResponseDto.builder()
                .phoneNumber(this.phoneNumber)
                .createdOn(this.createdOn)
                .phoneTypeResponseDtos(this.phoneTypeEntity.toDto())
                .build();
    }

    @Override
    public PhoneEntity from(CreatePhoneDto createPhoneDto) {
        return PhoneEntity.builder()
                .phoneTypeEntity(PhoneTypeEntity.builder().id(createPhoneDto.getPhoneTypeId()).build())
                .phoneNumber(createPhoneDto.getPhoneNumber())
                .createdOn(LocalDate.now())
                .build();
    }
}
