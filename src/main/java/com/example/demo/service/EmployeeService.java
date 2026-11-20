package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeRequestDto;
import com.example.demo.dto.EmployeeResponseDto;
import com.example.demo.exception.DepartmentNotFoundException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repo.DepartmentRepo;
import com.example.demo.repo.EmployeeRepo;

@Service
public class EmployeeService {

	
	private EmployeeRepo er;
	private DepartmentRepo dr;
	
	DepartmentService ds;
	public EmployeeService(EmployeeRepo er, DepartmentRepo dr) {
		super();
		this.er = er;
		this.dr = dr;
	}
	
	public EmployeeResponseDto convertDto(Employee emp) {
		EmployeeResponseDto empr=new EmployeeResponseDto();
		
		empr.setEid(emp.getEid());
		empr.setEname(emp.getEname());
		empr.setDid(emp.getDepartment().getDid());
		empr.setDname(emp.getDepartment().getDname());
		
		return empr;
	}
	
	
	public EmployeeResponseDto addEmp(EmployeeRequestDto empr) {
		Department dept=dr.findById(empr.getDid()).orElseThrow(()->new DepartmentNotFoundException("department id:" +empr.getDid()+"not found"));
		
		
		Employee emp=new Employee();
		emp.setEname(empr.getEname());
		emp.setSal(empr.getSal());
		emp.setDepartment(dept);
		
		Employee savedemp=er.save(emp);
		
		return convertDto(savedemp);
	}
	
	
	//Get
	
	public List<EmployeeResponseDto> getAllEmp(){
		List<Employee> emps=er.findAll();
		
		List<EmployeeResponseDto> emp1=emps.stream()
				.map(e->convertDto(e))
				.toList();
		
		return emp1;
	}
	
	//Get By EmpId
	
	public Employee findById(int eid) {
		Employee emp=er.findById(eid).orElseThrow(()-> new 
				EmployeeNotFoundException("employee eid: "+eid+"not exist"));
		
		return emp;
	}
	public EmployeeResponseDto getEid(int eid) {
		Employee emp=findById(eid);
		return convertDto(emp);
	}
	
	
	// update
	
	public EmployeeResponseDto updateEmp(int eid,EmployeeResponseDto emp) {
		Employee emp1=findById(eid);
		
		Department dept=findById(emp.getDid());
		emp1.setEname(emp.getEname());
		emp.setSal(emp.getSal());
		emp1.setDepartment(dept);
		
		Employee emps=er.save(emp1);
		
		return convertDto(emps);
	}
	
	//delete employee based on Id
	
	public EmployeeResponseDto deleteEmp(int eid) {
		Employee emp=findById(eid);
		
		er.delete(emp);
		return emp;
	}
}
