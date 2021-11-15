package hh.palvelinohjelmointi.tyoajanseuranta.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Workday {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String date;
	private double workhours;
	private String startingtime;
	private String endingtime;
	private String owner;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "taskid")
	private Task task;
	
	public Workday() {
		
	}
	
	public Workday(String date, double workhours, String startingtime, String endingtime, String owner ,Task task) {
		super();
		this.date = date;
		this.workhours = workhours;
		this.startingtime = startingtime;
		this.endingtime = endingtime;
		this.owner = owner;
		this.task = task;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public double getWorkhours() {
		return workhours;
	}
	
	public void setWorkhours(double workhours) {
		this.workhours = workhours;
	}
	
	public String getStartingtime() {
		return startingtime;
	}
	
	public void setStartingtime(String startingtime) {
		this.startingtime = startingtime;
	}
	
	public String getEndingtime() {
		return endingtime;
	}
	
	public void setEndingtime(String endingtime) {
		this.endingtime = endingtime;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	
	@Override
	public String toString() {
		if (this.task != null)
			return "Workday [id=" + id + ", date=" + date + ", workhours=" + workhours + ", startingtime=" + startingtime + ", endingtime=" + endingtime
					+ ", username="+ owner +" task =" + this.getTask() + "]";
		else
			return "Workday [id=" + id + ", date=" + date + ", workhours=" + workhours + ", startingtime=" + startingtime + ", endingtime=" + endingtime
					+ ", username="+ owner + "]";
	}
}
