package hh.palvelinohjelmointi.tyoajanseuranta.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WorkdayRepository extends CrudRepository<Workday, Long>{

	List<Workday> findByDate(String date);
	List<Workday> findByOwner(String owner);
}
