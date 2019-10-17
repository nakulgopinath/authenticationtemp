package io.javabrains.springsecurityjpa;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import io.javabrains.springsecurityjpa.models.User;

@RestController
@CrossOrigin("*")
public class HomeResource {
	
	@Autowired
    UserRepository userRepository;
	

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/authenticate")
    public Principal user(Principal user) {
    	System.out.println("Authentication successful");
        return user;
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
    
    @PostMapping(path="/register" ,consumes = { MediaType.APPLICATION_JSON_VALUE })
//	.antMatchers("/register").permitAll()
	public User createEmployee(@RequestBody @Valid User user)
    	{
    	String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    	System.out.println(encodedPassword);
    	user.setPassword(encodedPassword);
    	System.out.println("POST");
    	
		
		userRepository.save(user);	
		return user;

//		return ("<h1>Registered</h1>");

	}
}

