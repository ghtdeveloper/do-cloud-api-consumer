package com.neptune.service;

import com.neptune.domain.AddressEntity;
import com.neptune.domain.PersonEntity;
import com.neptune.domain.PhoneEntity;
import com.neptune.dto.request.person.CreatePersonDto;
import com.neptune.dto.request.person.UpdatePersonDto;
import com.neptune.dto.response.person.PersonCollectionResponse;
import com.neptune.dto.response.person.PersonResponseDto;
import com.neptune.repository.AddressRepository;
import com.neptune.repository.PersonRepository;
import com.neptune.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;
    private final PostalCodeService postalCodeService;
    private final NumberConversionService numberConversionService;

    @Transactional
    public PersonResponseDto save(CreatePersonDto createPersonDto) {
        PersonEntity personEntity = PersonEntity.builder().build().from(createPersonDto);
        PersonEntity savedPerson = this.personRepository.save(personEntity);
        PhoneEntity phoneEntity = PhoneEntity.builder().build().from(createPersonDto.getCreatePhoneDto());
        phoneEntity.setPersonEntity(savedPerson);
        AddressEntity addressEntity = AddressEntity.builder().build().from(createPersonDto.getCreateAddressDto());
        addressEntity.setPersonEntity(savedPerson);
        addressEntity.setState(postalCodeService.fetch(createPersonDto.getCreateAddressDto().getPostalCode()).getPostalCode().getState());
        addressEntity.setTown(postalCodeService.fetch(createPersonDto.getCreateAddressDto().getPostalCode()).getPostalCode().getTown());
        this.phoneRepository.saveAndFlush(phoneEntity);
        this.addressRepository.saveAndFlush(addressEntity);
        return savedPerson.toDto();
    }

    @Transactional
    public PersonResponseDto update(UpdatePersonDto updatePersonDto) {
        Optional<PersonEntity> personEntity = Optional.of(personRepository.findById(updatePersonDto.getId()).orElseThrow());
        PersonEntity person = personEntity.get();
        person.setId(updatePersonDto.getId());
        person.setActiveStatus(updatePersonDto.getActiveStatus());
        return this.personRepository.save(person).toDto();
    }

    public PersonResponseDto findById(Long id) {
        PersonEntity personEntity = this.personRepository.findById(id).orElseThrow();
        personEntity.setInfoWsdl(numberConversionService.generateNumberToWords(personEntity.getId()));
        return personEntity.toDto();
    }

    public PersonCollectionResponse findAll(Integer page, Integer pageSize) {
        Page<PersonEntity> entityPage = this.personRepository.findAll(PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id")));
        List<PersonResponseDto> personResponseDtoList = entityPage.stream().map(personEntity -> {
            personEntity.setInfoWsdl(numberConversionService.generateNumberToWords(personEntity.getId()));
            return personEntity.toDto();
        }).toList();
        return PersonCollectionResponse.builder()
                .page(page)
                .page(pageSize)
                .totalPages(entityPage.getTotalPages() - 1)
                .totalElements(entityPage.getTotalElements())
                .personResponseDtoList(personResponseDtoList)
                .build();
    }


}
