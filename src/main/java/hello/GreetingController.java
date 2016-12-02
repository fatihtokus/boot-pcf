package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
	@Autowired
	UserRepository repository;
	
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="id", required=false, defaultValue="1") Long id, Model model) {
    	User user = repository.findOne(id);
        model.addAttribute("user", user);
        return "greeting";
    }

}
