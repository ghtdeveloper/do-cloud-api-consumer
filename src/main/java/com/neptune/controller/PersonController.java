package com.neptune.controller;

import com.neptune.dto.request.person.CreatePersonDto;
import com.neptune.dto.request.person.UpdatePersonDto;
import com.neptune.dto.response.person.PersonCollectionResponse;
import com.neptune.dto.response.person.PersonResponseDto;
import com.neptune.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/v1.0/neptune/person",produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonService personService;

    @PostMapping(value = "/save")
    @Operation(summary = "Method to save an person",
            description = "Method to save an person")
    public ResponseEntity<PersonResponseDto> save(@NotNull @Valid @RequestBody CreatePersonDto createPersonDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.personService.save(createPersonDto));
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Method to update an person",
            description = "Method to update an person")
    public ResponseEntity<PersonResponseDto> update(@NotNull @Valid @RequestBody UpdatePersonDto updatePersonDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.personService.update(updatePersonDto));
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Search by person  id",
            description = "Search by person id")
    public ResponseEntity<PersonResponseDto> findById(@NotNull @PathVariable(name = "id")Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.personService.findById(id));
    }

    @GetMapping(value = "/all")
    @Operation(summary = "Return a list of person",
            description = "Return a list of person")
    public ResponseEntity<PersonCollectionResponse> findAll(@Min(value = 0) @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                            @Min(value = 1) @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll(page, pageSize));
    }

}
