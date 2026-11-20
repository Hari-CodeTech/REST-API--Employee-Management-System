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

import com.example.demo.dto.DepartmentRequestDto;
import com.example.demo.dto.DepartmentResponseDto;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/dept")
public class DepartmentController {

	public DepartmentController(DepartmentService ds) {
		super();
		this.ds = ds;
	}

	private DepartmentService ds;
	
	@PostMapping
	public DepartmentResponseDto addDept( @RequestBody DepartmentRequestDto dept) {
		return ds.addDept(dept);
	}
	
	
	@DeleteMapping
	public ResponseEntity<String> deleteById(@PathVariable int did){
		ds.deleteDept(did);
		return ResponseEntity.ok("delete successfully");
}
	
	@GetMapping
	public List<DepartmentResponseDto> getAllDept(){
		return ds.getAllDept();
	}
	
	@PutMapping("/{did}")
	public DepartmentResponseDto updateDept(@PathVariable int did,@RequestBody DepartmentRequestDto dept) {
		return ds.updateDept(did, dept);
	}
	
	@GetMapping("/{did}")
	public DepartmentResponseDto getById(@PathVariable int did) {
		return ds.getById(did);
	}
}
