package fr.bediss.employes.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bediss.employes.entities.Employee;
import fr.bediss.employes.exceptions.RessourceNotFoundException;
import fr.bediss.employes.repositories.EmployeeRepository;

@RestController
@RequestMapping("/api/v2")
public class EmployeeController {

	@Autowired
	private EmployeeRepository er;

	@GetMapping("/employes")
	List<Employee> getAllEmployee() {
		return er.findAll();
	}

	@PostMapping("/create")
	public Employee createEmployee(@RequestBody Employee employee) {
		return er.save(employee);
	}

	@GetMapping("/employes/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = er.findById(id).orElseThrow(() -> new RessourceNotFoundException("Pas d'employé retrouvé avec l'id : " + id) );
		
		return ResponseEntity.ok(employee);
	}
	
	
	@PutMapping("/update/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		
		Employee employee = er.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("L'employé avec l'id :" + id + " n'exitse pas"));
		
		employeeDetails.setId(id);
		
		return er.save(employeeDetails);
		
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = er.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("L'employé avec l'id : " + id + " n'exitse pas"));
		
		er.delete(employee);
		
		Map<String, Boolean> response = new HashMap<>();
		
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	
}
