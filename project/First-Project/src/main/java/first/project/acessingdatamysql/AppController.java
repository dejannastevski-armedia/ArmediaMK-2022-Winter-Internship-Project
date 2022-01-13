package first.project.acessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/register")
    public String Register(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @GetMapping("/login")
    public String Login(Model model) {
        model.addAttribute("user", new User());

        return "login";
    }

    @PostMapping("/register sucessfull")
    public String processRegister(User user)
    {
        List<User> list = userRepo.findAll();
        int i;
        boolean add = true;
        for (i = 0; i < list.size(); i++)
        {
            if (user.getEmail().equals(list.get(i).getEmail()))
            {
                add = false;
            }
        }
        if (add == true)
        {

            userRepo.save(user);
            return "login";
        } else
        {
            return "failed";
        }
    }
}