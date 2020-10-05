package com.pmanager;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pmanager.model.Role;
import com.pmanager.model.Users;
import com.pmanager.repository.UsersRepository;

@SpringBootApplication
public class ProductManagerApplication implements CommandLineRunner{
	
	@Autowired
	UsersRepository ur;

	public static void main(String[] args) {
		SpringApplication.run(ProductManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		 BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
			Set<Role> roles=new HashSet();
			
			Users u=new Users();
			u.setActive(1);
			u.setEmail("emre.eker@mail.com");
			u.setPassword(bCryptPasswordEncoder.encode("123"));
			u.setName("emre");
			
			Role role=new Role();
			role.setRole("USER");
			
			Role role1=new Role();
			role1.setRole("ADMIN");
			
		
			roles.add(role);
			roles.add(role1);
			
			u.setRoles(roles);
			
			ur.save(u);
		
	}

}
