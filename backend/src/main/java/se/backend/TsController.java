package se.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TsController {

    @GetMapping("/random")
    public ResponseEntity<String> getRandomRecipe(){

        return null;
    }

}
