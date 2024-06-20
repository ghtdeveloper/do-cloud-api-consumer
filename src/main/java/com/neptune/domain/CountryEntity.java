package com.neptune.domain;

import com.neptune.dto.response.country.CountryResponseDto;
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
@Table(name = "tb_country",schema = "core")
public class CountryEntity implements ToDTO<CountryResponseDto> {
    @Id
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "name",updatable = false)
    private String name;

    @Override
    public CountryResponseDto toDto() {
        return CountryResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
