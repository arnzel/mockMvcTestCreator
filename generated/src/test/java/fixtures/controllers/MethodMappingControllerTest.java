package fixtures.controllers;

import org.springframework.test.web.servlet.MockMvc;

class MethodMappingControllerTest {
  private MockMvc mockMvc;

  private MethodMappingController MethodMappingController;

  public MethodMappingControllerTest() {
    this.MethodMappingController = new MethodMappingController();
    this.mockMvc = null;
  }
}
