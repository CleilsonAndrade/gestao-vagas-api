package br.com.cleilsonandrade.gestao_vagas.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.cleilsonandrade.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.cleilsonandrade.gestao_vagas.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Before
  public void setup() {
    mvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(SecurityMockMvcConfigurers.springSecurity())
        .build();
  }

  @Test
  public void shouldBeAbleTo_CreateANewJob() throws Exception {

    var createJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

    var result = mvc.perform(
        MockMvcRequestBuilders.post("/companies/jobs/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(createJobDTO))
            .header("Authorization", TestUtils.generateToken(UUID.randomUUID(),
                "eyJhbGciOiJIUzI1NiJ9.eyJJc3N1ZXIiOiJjbGVpbHNvbmFuZHJhZGUiLCJleHAiOjE2OTkyODc3NTcsImlhdCI6MTY5OTI4Nzc1N30.dib1N8tlKtMufq17pSBUL2TIZepfHd9tfs-WGwcm76E")))
        .andExpect(MockMvcResultMatchers.status().isOk());

    System.out.println(result);
  }

}
