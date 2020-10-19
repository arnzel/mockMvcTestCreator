package fixtures.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMappingController {

  @RequestMapping(value="/controller", method=GET)
  public String getMapping() {
    return "GetMapping";
  }
  
  
}
