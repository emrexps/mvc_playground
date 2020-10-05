package com.pmanager;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pmanager.model.ApplicationUser;
import com.pmanager.model.CustomUserDetails;
import com.pmanager.model.Role;
import com.pmanager.model.Users;
import com.pmanager.repository.UsersRepository;

@Controller
public class ApplicationController {
	@Autowired
	UsersRepository ur;
	
	@RequestMapping("/list")
	public String listPage(Model model,Principal principal,Authentication authentication) {
		
		model.addAttribute("loggedInUser", principal.getName());
		
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		System.out.println("User has authorities: " + userDetails.getAuthorities());
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getEmail());
		

		
		
		return "list";
	}
	@RequestMapping("/edit")
public String editPage() {
		
		
		return "edit";
	}

	@RequestMapping("/login")
public String loginPage() {
	
	
	return "login";
}

	@RequestMapping("/adduser")
	public String addUser(Model model,Principal principal) {
		
		if(principal!=null) {
			
		}
		
		ApplicationUser au=new ApplicationUser();
		
		model.addAttribute("person", au);
		
		return "adduser";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String savePerson(@ModelAttribute("person") ApplicationUser person) {
		
		 BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
			Set<Role> roles=new HashSet();
			
		Users u=new Users();
		u.setActive(1);
		u.setEmail(person.getEmail());
		u.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
		u.setName(person.getName());
		
		Role role=new Role();
		role.setRole(person.getRole());
		
		
		roles.add(role);
		
		u.setRoles(roles);
		
		ur.save(u);
		
		return "redirect:/list";
	}
	
	@RequestMapping("/addProduct")
	public String addProduct() {
		
		
		return "create";
	}

}
