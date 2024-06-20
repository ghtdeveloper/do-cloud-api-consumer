package com.neptune.domain;

import com.neptune.dto.response.phone.PhoneTypeResponseDto;
import com.neptune.utils.ToDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_phone_type",schema = "core")
public class PhoneTypeEntity implements ToDTO<PhoneTypeResponseDto> {

    @Id
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "type_name",updatable = false)
    private String typeName;

    @Override
    public PhoneTypeResponseDto toDto() {
        return PhoneTypeResponseDto.builder()
                .id(this.id)
                .typeName(this.typeName)
                .build();
    }
}
