package hh.palvelinohjelmointi.tyoaikasovellus;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohjelmointi.tyoajanseuranta.domain.Task;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.TaskRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository repository;

    @Test
    public void findByNameShouldReturnTask() {
        List<Task> tasks = repository.findByName("Tammikuu");
        
        assertThat(tasks).hasSize(1);
        assertThat(tasks.get(0).getName()).isEqualTo("Tammikuu");
    }
    
    @Test
    public void createNewTask() {
    	Task task = new Task("Testi", "user1");
    	repository.save(task);
    	assertThat(task.getTaskid()).isNotNull();
    	assertThat(task.getName()).isEqualTo("Testi");
    }    
    
    @Test
	public void deleteCategory() {
    		List<Task> tasks = repository.findByName("Helmikuu");
		Long id = tasks.get(0).getTaskid();
		
		repository.deleteById(id);	
		assertThat(repository.findByName("Helmikuu")).isEmpty();
	}
}