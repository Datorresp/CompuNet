package icesi.edu.co.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import icesi.edu.co.person.NUser;
import icesi.edu.co.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		NUser user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return user;
	}

}
