package fixtures.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class MethodMappingControllerTest {
  private MockMvc mockMvc;

  private MethodMappingController MethodMappingController;

  public MethodMappingControllerTest() {
    this.MethodMappingController = new MethodMappingController();
    this.mockMvc = MockMvcBuilders.standaloneSetup(new MethodMappingController()).build();
  }

  @Test
  public void getMapping() {
  }
}
