package com.onebill.assessment.employee.Employee;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transaction;

import com.onebill.assessment.employee.Employee.all.Employee_Info;
import com.onebill.assessment.employee.Employee.all.Employee_Leave;

public class App {
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("PU01");
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=entityManager.getTransaction();
        Scanner sc=new Scanner(System.in);
        do {
        System.out.println("Press 1 to register employee");
        System.out.println("Press 2 to login");
        int num=sc.nextInt();
        switch(num) {
        	case 1:
        		System.out.println("\tEmployee Registration");
        		System.out.println("------------");
        		System.out.println("Enter the Employee ID:");
        		int empId=sc.nextInt();
        		System.out.println("Enter the Employee Name:");
        		String empName=sc.next();
        		System.out.println("Employee Type:");
        		String empType=sc.next();
        		System.out.println("Enter the Email ID:");
        		String empEmail=sc.next();
        		System.out.println("Enter the Password:");
        		String empPassword=sc.next();
        		Employee_Info employee=new Employee_Info(empId,empName,empType,empEmail,empPassword);
        		transaction.begin();
        		entityManager.persist(employee);
        		transaction.commit();
        	break;
        case 2:
        	System.out.println("\tLogin");
//        	System.out.println(" ");
        	System.out.println("Enter the Employee ID:");
        	int id=sc.nextInt();
        	String check = "from Employee_Info where Employee_ID=:id";
			Query query = entityManager.createQuery(check);
			query.setParameter("id", id);
			Employee_Info employee1 = (Employee_Info) query.getSingleResult();
			String uPassword = employee1.getPassword();
			int k = 0;
			while (k != 1) {
				System.out.println("Enter the password");
				String password = sc.next();
				if (password.equals(uPassword))
					k = 1;
				else
					System.out.println("Enter the correct password");
			}
        	String type="select Employee_Type from Employee_Info where Employee_ID=:id";
        	Query q1=entityManager.createQuery(type);
			q1.setParameter("id", id);
			String type1=(String) q1.getSingleResult();
			String typeLowerCase=type1.toLowerCase();
			switch(typeLowerCase) {
				case "manager":
					System.out.println("Press 1 Show All Leave Request");
					System.out.println("Press 2 Approve/Reject leave Request.");
					System.out.println("Press 3 Exit to the Main Menu.");
					int input=sc.nextInt();
					switch(input) 
					{
						case 1:
							String show = "from Employee_Leave";
							Query listQuery = entityManager.createQuery(show);
							List list = listQuery.getResultList();
							for (Object object : list) {
								Employee_Leave employeeleave = (Employee_Leave) object;
								System.out.println("=> " + employeeleave);}
						break;
						case 2:
							System.out.println("Enter the leave id");
							int Leave_Id = sc.nextInt();
							String leave = "select Leave_Status from Employee_Leave where Leave_Id=:id";
							Query leaveQuery = entityManager.createQuery(leave);
							leaveQuery.setParameter("id", Leave_Id);
							System.out.println("to Approve Press 1");
							System.out.println("to Reject Press 2");
							int leaveStatus = sc.nextInt();
							String status = "";
							String update = "";
							if (leaveStatus == 1) {
								update = "Approved";
								status = "update Employee_Leave set Leave_Status=:n where Leave_Id=:id";
							} else if (leaveStatus == 2) {
								update = "Rejected";
								status = "update Employee_Leave set Leave_Status=:n where Leave_Id=:id";
							} else
								update = "Pending";
							Query permitQuery = entityManager.createQuery(status);
							permitQuery.setParameter("id", Leave_Id);
							permitQuery.setParameter("n", update);
							transaction.begin();
							permitQuery.executeUpdate();
							transaction.commit();

							break;
						default:
							System.out.println("invalid");
							break;
					}
				break;	
				case "employee":
					System.out.println("1.Press 1 to show status of applied leave requests");
					System.out.println("2.Press 2 to apply new leave request");
					System.out.println("3.Press 3 to exit to main menu");
					int leaveReq = sc.nextInt();
					switch (leaveReq) {
					case 1: {
						String leaveList = "from Employee_Leave where Employee_Id=:id";
						Query leaveQuery = entityManager.createQuery(leaveList);
						leaveQuery.setParameter("id", id);
						List listLeave = leaveQuery.getResultList();
						for (Object object : listLeave) {
							Employee_Leave leave = (Employee_Leave) object;
							System.out.println("=> " + leave);
						}
					}
						break;
					case 2: {
						System.out.println("Enter the date of leave");
						String date = sc.next();
						Employee_Leave leave = new Employee_Leave(id,date);
						transaction.begin();
						entityManager.persist(leave);
						transaction.commit();
					}
				
					break;
				default:
					System.out.println("invalid");
				break;
			}
        default:
        	System.out.println("Invalid Input Please Enter the Valid Input.");
        	}
        

        }
    }while(true);
}
}
