package hh.palvelinohjelmointi.tyoajanseuranta.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long>{
	
	List<Task>findByName(String name);
	List<Task>findByOwner(String owner);

}
