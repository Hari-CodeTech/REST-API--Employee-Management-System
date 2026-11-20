package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentRequestDto;
import com.example.demo.dto.DepartmentResponseDto;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.repo.DepartmentRepo;

@Service
public class DepartmentService {

	public DepartmentService(DepartmentRepo dr) {
		super();
		this.dr = dr;
	}

	private DepartmentRepo dr;
	
	
	public DepartmentResponseDto convertDto(Department dept) {
		DepartmentResponseDto deptr=new DepartmentResponseDto();
		
		deptr.setDid(dept.getDid());
		deptr.setDname(dept.getDname());
		
		return deptr;
	}
	
	public DepartmentResponseDto addDept(DepartmentRequestDto deptr) {
		Department dept=new Department();
		
		dept.setDname(deptr.getDname());
		
		Department saveddept=dr.save(dept);
		
		return convertDto(saveddept);
		
	}
	
	public Department findById(int did) {
		return dr.findById(did).orElseThrow(()->new DepartmentNotFoundException("department id" +did+"not exists"));
		
				
	}

	public Department deleteDept(int did) {
		Department dept=findById(did);
		dr.delete(dept);
		return dept;
	}
	
	//Get all
	public List<DepartmentResponseDto>  getAllDept(){
		List<Department> depts=dr.findAll();
		List<DepartmentResponseDto> depts1=depts.stream()
			.map(d->convertDto(d))
				.toList();
			
			return depts1;
	}
	
	
	//Update
	public DepartmentResponseDto updateDept(int did,DepartmentRequestDto dept) {
		Department dept1=findById(did);
		dept1.setDname(dept.getDname());
		Department savedept=dr.save(dept1);
		
		return convertDto(savedept);
	}
	
	//Get by Id
	
	public DepartmentResponseDto getById(int did) {
		Department dept1=findById(did);
		return convertDto(dept1);
	}
	
	
}
