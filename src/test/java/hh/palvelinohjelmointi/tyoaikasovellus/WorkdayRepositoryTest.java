package hh.palvelinohjelmointi.tyoaikasovellus;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohjelmointi.tyoajanseuranta.domain.Task;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.Workday;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.WorkdayRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class WorkdayRepositoryTest {

    @Autowired
    private WorkdayRepository repository;

    @Test
    public void findByDateShouldReturnWorkday() {
        List<Workday> workdays = repository.findByDate("1.1.2019");
        
        assertThat(workdays).hasSize(1);
        assertThat(workdays.get(0).getStartingtime()).isEqualTo("8.00");
    }
    
    @Test
    public void createNewWorkday() {
    	Workday workday = new Workday("3.1.2019", 5.5, "7.00", "17.00", "user1", new Task("Testi", "user1"));
    	repository.save(workday);
    	assertThat(workday.getId()).isNotNull();
    }    

    @Test
	public void deleteWorkday() {
		List<Workday> workdays = repository.findByDate("2.1.2019");
		Long id = workdays.get(0).getId();
		
		repository.deleteById(id);	
		assertThat(repository.findById(id)).isEmpty();
	}
	
}