package hh.palvelinohjelmointi.tyoajanseuranta.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.palvelinohjelmointi.tyoajanseuranta.domain.Task;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.TaskRepository;

@Controller
public class TaskController {

	@Autowired
	private TaskRepository pRepository;
		
		// palauttaa tehtävälistan
		@RequestMapping(value="/tasklist", method = RequestMethod.GET)
		public String findUsersTasks(Model model, Principal principal) {
			String name = principal.getName();
			model.addAttribute("tasks", pRepository.findByOwner(name));
			if (name.contains("admin")){
		    	  model.addAttribute("tasks", pRepository.findAll());
		      }
		      else {
		    	  model.addAttribute("tasks", pRepository.findByOwner(name));
		      }
			
			return "tasklist";
		}
		
		// RESTful servicet Tehtäviä / Taskeja varten
	    @RequestMapping(value="/tasks", method = RequestMethod.GET)
	    public @ResponseBody List<Task> getTasksRest() {	
	        return (List<Task>) pRepository.findAll();
	    }    

	    @RequestMapping(value="/task/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Task> getTaskRest(@PathVariable("id") Long taskId) {	
	    	return pRepository.findById(taskId);
	    }

	    @RequestMapping(value="/tasks", method = RequestMethod.POST)
	    public @ResponseBody Task saveTaskRest(@RequestBody Task task, Model model) {	
	    	return pRepository.save(task);
	    }
	    
		@RequestMapping(value = "/delete/task/{id}", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('ADMIN')")
		public String deleteTask(@PathVariable("id") Long taskId) {
			pRepository.deleteById(taskId);
			return "redirect:/tasklist";
		}

		@RequestMapping(value = "/savetask", method = RequestMethod.POST)
		public String saveTask(@ModelAttribute Task task, BindingResult errors, Model model, Principal principal) {
			String owner = principal.getName();
			task.setOwner(owner);
			pRepository.save(task);
			return "redirect:tasklist";
		}

		@RequestMapping(value = "/addtask", method = RequestMethod.GET)
		public String addTask(Model model) {
			model.addAttribute("task", new Task());
	        return "addtask";
		}
}
