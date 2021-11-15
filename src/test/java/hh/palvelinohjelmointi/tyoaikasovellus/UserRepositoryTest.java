package hh.palvelinohjelmointi.tyoaikasovellus;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohjelmointi.tyoajanseuranta.domain.User;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findByUsernameShouldReturnUser() {
        User user = repository.findByUsername("user1");
        
        assertThat(user).isNotNull();
        assertThat(user.getUsername().contentEquals("user1"));
    }
    
    
    
    
    @Test
    public void createNewUser() {
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	User user = new User ("testi", encoder.encode("testi"), "testi@testi.com","USER");
    	repository.save(user);
    	assertThat(user.getId()).isNotNull();
    }    

    
    
    
    
    @Test
	public void deleteUser() {
		Long id = repository.findByUsername("user1").getId();
		
		repository.deleteById(id);	
		assertThat(repository.findById(id)).isEmpty();
	}
	
}