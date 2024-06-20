package com.neptune;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neptune.controller.PersonController;
import com.neptune.dto.request.address.CreateAddressDto;
import com.neptune.dto.request.person.CreatePersonDto;
import com.neptune.dto.request.person.UpdatePersonDto;
import com.neptune.dto.request.phone.CreatePhoneDto;
import com.neptune.dto.response.person.PersonCollectionResponse;
import com.neptune.dto.response.person.PersonResponseDto;
import com.neptune.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;

@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc
class PersonControllerTest {

    @MockBean
    private PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void savePerson_ValidDtoProvided_ReturnResult() throws Exception {
        PersonResponseDto personResponseDto = new PersonResponseDto();
        personResponseDto.setId(1L);
        personResponseDto.setFirstName("Joe");
        personResponseDto.setMaternalName("Doe");
        personResponseDto.setMaternalName("Fritz");

        Mockito.when(personService.save(ArgumentMatchers.any(CreatePersonDto.class))).thenReturn(personResponseDto);

        CreatePersonDto createPersonDto = new CreatePersonDto();
        createPersonDto.setFirstName("Joe");
        createPersonDto.setMaternalName("Doe");
        createPersonDto.setMaternalName("Fritz");
        createPersonDto.setFullName("Joe Doe Fritz ");
        createPersonDto.setAge(25);
        createPersonDto.setSex('M');
        createPersonDto.setActiveStatus('A');
        createPersonDto.setCreatePhoneDto(new CreatePhoneDto());
        createPersonDto.setCreateAddressDto(new CreateAddressDto());

        String content = objectMapper.writeValueAsString(createPersonDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1.0/neptune/person/save").contentType(MediaType.APPLICATION_JSON_VALUE).content(content);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void updatePerson_ValidDtoProvided_ReturnResult() throws Exception {
        PersonResponseDto personResponseDto = new PersonResponseDto();
        personResponseDto.setId(13L);
        personResponseDto.setFirstName("Reynaldo");
        personResponseDto.setMaternalName("Godines");
        personResponseDto.setMaternalName("Lopez");

        Mockito.when(personService.update(ArgumentMatchers.any(UpdatePersonDto.class))).thenReturn(personResponseDto);

        UpdatePersonDto updatePersonDto = new UpdatePersonDto(13L,'I');

        String content = objectMapper.writeValueAsString(updatePersonDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1.0/neptune/person/update").contentType(MediaType.APPLICATION_JSON_VALUE).content(content);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void findPerson_ValidIdProvided_ReturnResult()throws Exception {
        PersonResponseDto personResponseDto = new PersonResponseDto();
        personResponseDto.setId(13L);
        personResponseDto.setFirstName("Reynaldo");
        personResponseDto.setMaternalName("Godines");
        personResponseDto.setMaternalName("Lopez");

        Mockito.when(personService.findById(ArgumentMatchers.any(Long.class))).thenReturn(personResponseDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/neptune/person/13").contentType(MediaType.APPLICATION_JSON_VALUE);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findAllPerson_ValidParamsProvided_ReturnResult()throws Exception{
        PersonCollectionResponse personCollectionResponse = new PersonCollectionResponse();
        personCollectionResponse.setPersonResponseDtoList(List.of(
                new PersonResponseDto(13L,"Reynaldo","Godines","Lopez",null,null,null,null,null,null,null,null),
                new PersonResponseDto(1L,"Joe","Doe","Fritz",null,null,null,null,null,null,null,null)
        ));
        Mockito.when(personService.findAll(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt())).thenReturn(personCollectionResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1.0/neptune/person/all").contentType(MediaType.APPLICATION_JSON_VALUE).param("page","0","pageSize","10");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

    }
}
