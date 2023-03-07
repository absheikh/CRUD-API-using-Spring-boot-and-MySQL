package absheikh.co.rest.Controllers;

import absheikh.co.rest.Models.User;
import absheikh.co.rest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String getHello() {
        return "Hello World";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user/add")
    public Object addUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already exists";
        }
        return userRepository.save(user);
    }

    @PutMapping("/user/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User updateUser = userRepository.findById(id).orElse(null);
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        return userRepository.save(updateUser);
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }


}
