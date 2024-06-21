package com.neptune.domain;

import com.neptune.dto.request.person.CreatePersonDto;
import com.neptune.dto.response.person.PersonResponseDto;
import com.neptune.utils.ToDTO;
import com.neptune.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_person",schema = "core")
public class PersonEntity implements TransformFrom<CreatePersonDto,PersonEntity>, ToDTO<PersonResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_person_id_generator")
    @SequenceGenerator(name = "seq_person_id_generator", allocationSize = 1,
            sequenceName = "seq_person_id",schema = "core")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "first_name",updatable = false)
    private String firstName;

    @Column(name = "paternal_name",updatable = false)
    private String paternalName;

    @Column(name = "maternal_name",updatable = false)
    private String maternalName;

    @Column(name = "full_name",updatable = false)
    private String fullName;

    @Column(name = "age",updatable = false)
    private Integer age;

    @Column(name = "sex",updatable = false)
    private Character sex;

    @Column(name = "occupation",updatable = false)
    private String occupation;

    @Column(name = "created_on",updatable = false)
    private LocalDate createdOn;

    @Column(name = "active_status")
    private Character activeStatus;

    @OneToMany(mappedBy = "personEntity", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PhoneEntity> phones;

    @OneToMany(mappedBy = "personEntity", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<AddressEntity> address;

    @Transient
    private String infoWsdl;

    @Override
    public PersonResponseDto toDto() {
        return PersonResponseDto.builder()
                .id(this.id)
                .firstName(this.firstName)
                .paternalName(this.paternalName)
                .maternalName(this.maternalName)
                .fullName(this.fullName)
                .age(this.age)
                .sex(this.sex)
                .occupation(this.occupation)
                .createdOn(this.createdOn)
                .activeStatus(this.activeStatus)
                .phoneResponseDto(this.phones != null ? this.phones.stream().map(PhoneEntity::toDto).toList() :Collections.emptyList())
                .addressResponseDto(this.address != null ? this.address.stream().map(AddressEntity::toDto).toList():Collections.emptyList())
                .numberToWords(this.infoWsdl)
                .build();
    }

    @Override
    public PersonEntity from(CreatePersonDto createPersonDto) {
        return PersonEntity.builder()
                .firstName(createPersonDto.getFirstName())
                .paternalName(createPersonDto.getPaternalName())
                .maternalName(createPersonDto.getMaternalName())
                .fullName(createPersonDto.getFullName())
                .age(createPersonDto.getAge())
                .sex(createPersonDto.getSex())
                .occupation(createPersonDto.getOccupation())
                .createdOn(LocalDate.now())
                .activeStatus(createPersonDto.getActiveStatus())
                .build();
    }
}
