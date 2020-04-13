package fixtures.controllers;

import java.lang.Exception;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class MethodMappingControllerTest {
  private final MockMvc mockMvc;

  private final MethodMappingController methodMappingController;

  public MethodMappingControllerTest() {
    this.methodMappingController = new MethodMappingController();
    this.mockMvc = MockMvcBuilders.standaloneSetup(new MethodMappingController()).build();
  }

  @Test
  public void getMapping() throws Exception {
    mockMvc.perform( MockMvcRequestBuilders.get("/controller").accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
  }
}
