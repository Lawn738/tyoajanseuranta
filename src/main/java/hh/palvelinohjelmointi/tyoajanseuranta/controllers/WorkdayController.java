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

import hh.palvelinohjelmointi.tyoajanseuranta.domain.TaskRepository;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.Workday;
import hh.palvelinohjelmointi.tyoajanseuranta.domain.WorkdayRepository;

@Controller
public class WorkdayController {

	@Autowired
	private WorkdayRepository wRepository;
	@Autowired
	private TaskRepository pRepository;

	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
		
		// to see all your own messages, not others messages
		@RequestMapping(value="/workdaylist", method = RequestMethod.GET)
		public String findUserMessages(Model model, Principal principal) {
	      String name = principal.getName();
	      if (name.contains("admin")){
	    	  model.addAttribute("workdays", wRepository.findAll());
	      }
	      else {
	    	  model.addAttribute("workdays", wRepository.findByOwner(name));
	      }
	      return "workdaylist";

	}

		// RESTful servicet työmääräyksiä varten
	    @RequestMapping(value="/workdays", method = RequestMethod.GET)
	    public @ResponseBody List<Workday> getWorkdaysRest() {	
	        return (List<Workday>) wRepository.findAll();
	    }    

	    @RequestMapping(value="/workday/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Workday> getWorkdayRest(@PathVariable("id") Long workdayId) {	
	    	return wRepository.findById(workdayId);
	    }

	    @RequestMapping(value="/workdays", method = RequestMethod.POST)
	    public @ResponseBody Workday saveWorkdayRest(@RequestBody Workday workday, Model model) {	
	    	return wRepository.save(workday);
	    }
	    
	  
	    @RequestMapping(value="/resthome", method = RequestMethod.GET)
	    public String getRestHome() {	
	    	return "resthomepage";
	    }

		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		@PreAuthorize("hasAuthority('ADMIN')")
		public String deleteWorkday(@PathVariable("id") Long workdayId) {
			wRepository.deleteById(workdayId);
			return "redirect:/workdaylist";
		}

		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String saveBook(@ModelAttribute Workday workday, BindingResult errors, Model model, Principal principal) {
			String owner = principal.getName();
			workday.setOwner(owner);
			wRepository.save(workday);
			return "redirect:workdaylist";
		}

		@RequestMapping(value = "/addworkday", method = RequestMethod.GET)
		public String addWorkday(Model model, Principal principal) {
			String name = principal.getName();
			model.addAttribute("workday", new Workday());
			model.addAttribute("tasks", pRepository.findByOwner(name));
	        return "addworkday";
		}
}
