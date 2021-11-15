package hh.palvelinohjelmointi.tyoajanseuranta.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long taskid;
	private String name;
	private String owner;
	


	@OneToMany(cascade =CascadeType.ALL, mappedBy = "task")
	private List<Workday> workdays;
	
	public Task() {
		
	}
	
	public Task (String name, String owner) {
		super();
		this.name = name;
		this.owner = owner;
	}
	
	public Long getTaskid() {
		return taskid;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Workday> getWorkdays(){
		return workdays;
	}
	
	public void setWorkdays(List<Workday> workdays) {
		this.workdays = workdays;
	}
	
	public String getUsername() {
		return owner;
	}

	public void setUsername(String username) {
		this.owner = username;
	}
	
	public String toString() {
		return "Task [taskid=" + taskid + ", name=" + name + ", username=" + owner+ "]";
	}
}
