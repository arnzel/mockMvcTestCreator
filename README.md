# mockMvcTestCreator

![Build Status](https://travis-ci.com/arnzel/mockMvcTestCreator.svg?branch=master)

Generates Tests for Spring Mvc Controller

Example Controller Class

```
package fixtures.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MethodMappingController {

  @RequestMapping(value="/controller", method=GET)
  public String getMapping() {
    return "GetMapping";
  }
  
  
}
```

Generated Test

```
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

```