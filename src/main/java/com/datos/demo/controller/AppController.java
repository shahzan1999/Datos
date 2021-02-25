package com.datos.demo.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.datos.demo.dao.UserRepository;
import com.datos.demo.dao.UserInfoRepository;
import com.datos.demo.model.UserInfo;
import com.datos.demo.model.User;

@Controller
public class AppController {

	@Autowired
	private UserInfoRepository userInfoRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public String viewHomePage() {
		return "home";
	}	
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	    return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    userRepo.save(user);
	    return "register_success";
	}
	
	@GetMapping("/user_details")
	public String showUserDeatilsForm(Model model,Principal principal) {		
		if(userInfoRepo.findByUname(principal.getName())==null) {
			model.addAttribute("userInfo", new UserInfo());
		    return "user_details";
		}
		model.addAttribute("userInfo",userInfoRepo.findByUname(principal.getName()));
		return "user_details";
	}
	
	@PostMapping("/process_user")
	public String processUserDetails(UserInfo userInfo,Principal principal) {
		
        UserInfo previous_user = userInfoRepo.findByUname(principal.getName());
        
        if(previous_user == null) {
    		userInfo.setUname(principal.getName());
            userInfoRepo.save(userInfo);
        }
        else {
        	previous_user.setFirstName(userInfo.getFirstName());
            previous_user.setLastName(userInfo.getLastName());
            previous_user.setEmail(userInfo.getEmail());
            previous_user.setAddress(userInfo.getAddress());
    	    userInfoRepo.save(previous_user);
        }
	    return "home";
	}
	
}