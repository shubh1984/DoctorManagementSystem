package com.amdocs.doc.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.amdocs.doc.pojo.Doctor;
import com.amdocs.doc.dao.*;

public class DocMain {
	public static void main(String[] args)
	{
		
		DoctorDao doct=new DoctorDao();
		while(true)
		{
			System.out.println("Doctor Management System");
			System.out.println("Enter your choice:");
			System.out.println("1. Add new doctor");
			System.out.println("2. Update doctor fees");
			System.out.println("3. Delete doctor");
			System.out.println("4. View all doctors");
			System.out.println("5. Find doctor by specialization");
			System.out.println("6. Find doctors who�s fees is less than all doctors of given shift");
			System.out.println("7. Count number of doctors by shift");
			System.out.println("8. Exit");
			Scanner sc=new Scanner(System.in);
			int choice=sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
				case 1:
					System.out.println("Enter doctor details");
					System.out.print("Enter doctorid");
					
					int id=sc.nextInt();
					sc.nextLine();
					System.out.print("Enter doctorname");
					String name=sc.nextLine();
					System.out.print("Enter mobilenumber");
					String mobileNo=sc.nextLine();
					System.out.print("Enter specialization");
					String specialization=sc.nextLine();
					System.out.print("Enter avilable shift");
					String shift=sc.nextLine();
					System.out.print("fees");
					double fees=sc.nextDouble();
					Doctor doc=new Doctor(id,name,mobileNo,specialization,shift,fees);
					int c = doct.addDoctor(doc);
					System.out.println(c+ "Record inserted");
					break;
				case 2:
					System.out.println("Enter doctor id to be updated");
					int docid=sc.nextInt();
					System.out.println("Enter the new doctor fees");
					double docFees=sc.nextDouble();
					boolean res=doct.updateDoctorFees(docid, docFees);
					if(res==true)
						System.out.println("Fees updated successfully");
					else
						System.out.println("Fees update failed");
					break;
				case 3:
					System.out.println("Enter doctor id to be deleted");
					int dno=sc.nextInt();
					int c1=doct.deleteDoctor(dno);
					System.out.println(c1+ "Record deleted");
					break;
				case 4:
					List<Doctor> list1=new ArrayList<>();
					list1=doct.showAllDoctors();
					for (Doctor dctr1 : list1) {
					    System.out.println("Doctor ID: " + dctr1.getDoctorId());
					    System.out.println("Doctor Name: " + dctr1.getDoctorName());
					    System.out.println("Mobile Number: " + dctr1.getMobileNumber());
					    System.out.println("Specialization: " + dctr1.getSpecialization());
					    System.out.println("Available Shift: " + dctr1.getAvailableShift());
					    System.out.println("Fees: " + dctr1.getFees());
					    System.out.println("-------------------------");
					}
					break;
				case 5:
					System.out.println(
);
					String spec=sc.nextLine();
					List<Doctor> list=new ArrayList<>();
					list=doct.searchBySpecialization(spec);
					for (Doctor dctr : list) {
					    System.out.println("Doctor ID: " + dctr.getDoctorId());
					    System.out.println("Doctor Name: " + dctr.getDoctorName());
					    System.out.println("Mobile Number: " + dctr.getMobileNumber());
					    System.out.println("Specialization: " + dctr.getSpecialization());
					    System.out.println("Available Shift: " + dctr.getAvailableShift());
					    System.out.println("Fees: " + dctr.getFees());
					    System.out.println("-------------------------");
					}
					  break;
				case 6:
					System.out.println("Enter the shift");
					String shift1=sc.nextLine();
					List<Doctor> list2=new ArrayList<>();
					list2=doct.searchByFeesAndShift(shift1);
					for (Doctor dctr2 : list2) {
					    System.out.println("Doctor ID: " + dctr2.getDoctorId());
					    System.out.println("Doctor Name: " + dctr2.getDoctorName());
					    System.out.println("Mobile Number: " + dctr2.getMobileNumber());
					    System.out.println("Specialization: " + dctr2.getSpecialization());
					    System.out.println("Available Shift: " + dctr2.getAvailableShift());
					    System.out.println("Fees: " + dctr2.getFees());
					    System.out.println("-------------------------");
					}
					    break;
				case 7:
					System.out.println("Enter The shift");
					String shift2=sc.nextLine();
					int c3=doct.countDoctorsByShift(shift2);
					System.out.println(c3+" Number of doctors in "+shift2);
					break;
					
				case 8:
					 	System.out.println("Exiting the program.");
	                    System.exit(0);
					
			}
			
			
			
			
		}
	}

}
