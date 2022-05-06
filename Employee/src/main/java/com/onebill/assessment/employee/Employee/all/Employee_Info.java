package com.onebill.assessment.employee.Employee.all;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee_Info {
	@Id
	private int Employee_ID;  
	private String Employee_Name;
	private String Employee_Type;
	private String Email;
	private String Password;
	
	

}
