package com.onebill.assessment.employee.Employee.all;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employeeleave")
public class Employee_Leave {
	
	public Employee_Leave(int employee_ID, String leave_Date) {
		super();
		Employee_ID = employee_ID;
		Leave_Date = leave_Date;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Leave_ID;
	private int Employee_ID;
	private String Leave_Date;
	private String Leave_Status="Pending";  
}
