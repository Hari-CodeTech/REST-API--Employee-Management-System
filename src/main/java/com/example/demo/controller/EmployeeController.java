package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {
	private EmployeeService es;

	public EmployeeController(EmployeeService es) {
		super();
		this.es = es;
	}
	
	
	@PostMapping
	public EmployeeResponseDto addEmp(@RequestBody EmployeeRequestDto emp) {
		return es.addEmp(emp);
	}
	
	@GetMapping
	public List< EmployeeResponseDto >getAllEmp() {
		return es.getAllEmp();
	}
	
	@GetMapping("/{eid}")
	public EmployeeResponseDto getEid(@PathVariable int eid) {
		return es.getEid(eid);
	}
	
	@PutMapping("/{eid}")
	public EmployeeResponseDto updateEmp(@PathVariable int eid,@RequestBody EmployeeRequestDto emp) {
		return es.updateEmp(eid, emp);
	}
	
	@DeleteMapping("/{eid}")
	public ResponseEntity<String> deleteEid(@PathVariable int eid){
		String msg=es.deleteEmp(eid);
		return ResponseEntity.ok(msg);
	}
	

}
