package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	@Autowired
	UserRepository repository;
	
    @RequestMapping("/greeting")
    public User greeting(@RequestParam(value="id", required=false, defaultValue="1") Long id) {
    	User user = repository.findOne(id);
        return user;
    }

}
