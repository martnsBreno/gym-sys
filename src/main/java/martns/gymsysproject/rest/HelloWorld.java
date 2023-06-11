package martns.gymsysproject.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    
    
    @GetMapping(path = "/hello")
    public String helloWorldRest() {

        return "Olá Mundo!";
    }
}
