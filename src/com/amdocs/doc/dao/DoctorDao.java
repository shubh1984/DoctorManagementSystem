package com.amdocs.doc.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.doc.dao.*;

import com.amdocs.doc.pojo.Doctor;
public class DoctorDao {
	PreparedStatement pst;
	connection docconnect=new connection();
	Connection con=docconnect.connect();
	public int addDoctor(Doctor doc)
	{
		String insertSQL = "INSERT INTO doctors (doctorId,doctorName, mobileNumber, specialization, availableShift, fees) "+"VALUES (?, ?, ?, ?, ?, ?)";
		int count=0;
		try
		{ 
			pst=con.prepareStatement(insertSQL);
			pst.setInt(1,doc.getDoctorId());
			pst.setString(2, doc.getDoctorName());
            pst.setString(3, doc.getMobileNumber());
            pst.setString(4, doc.getSpecialization());
            pst.setString(5, doc.getAvailableShift());
            pst.setDouble(6, doc.getFees());
            count = pst.executeUpdate();
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
		
		
		return count;
		
	}
	public List<Doctor> searchBySpecialization(String specialization)
	{
		 String selectSQL = "SELECT * FROM doctors WHERE specialization = ?";
	        List<Doctor> docList= new ArrayList<>();
	        try{
	        	pst=con.prepareStatement(selectSQL);
	        	pst.setString(1, specialization);
	        	ResultSet resultSet = pst.executeQuery();
	        	while (resultSet.next()) {
	                int doctorId = resultSet.getInt("doctorId");
	                String doctorName = resultSet.getString("doctorName");
	                String mobileNumber = resultSet.getString("mobileNumber");
	                String availableShift = resultSet.getString("availableShift");
	                double fees = resultSet.getDouble("fees");

	                Doctor doctor = new Doctor(doctorId, doctorName, mobileNumber, specialization, availableShift, fees);
	                docList.add(doctor);
	            }
	        }
	        	catch(Exception e)
	        	{
	        		e.printStackTrace();
	        	}
			
		
	        	return docList;
	       }
	public boolean updateDoctorFees(int doctorId, double newFees) {
        String updateSQL = "UPDATE doctors SET fees = ? WHERE doctorId = ?";
        int rowsUpdated=0;
        try{
        	pst=con.prepareStatement(updateSQL);
            pst.setDouble(1, newFees);
            pst.setInt(2, doctorId);
            rowsUpdated = pst.executeUpdate();
        }            
          catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return rowsUpdated>0;       
    }
	public List<Doctor> showAllDoctors()
	{
		 String selectSQL = "SELECT * FROM doctors";
	        List<Doctor> docList= new ArrayList<>();
	        try{
	        	pst=con.prepareStatement(selectSQL);
	        	ResultSet resultSet = pst.executeQuery();
	        	while (resultSet.next()) {
	                int doctorId = resultSet.getInt("doctorId");
	                String doctorName = resultSet.getString("doctorName");
	                String mobileNumber = resultSet.getString("mobileNumber");
	                String specialization = resultSet.getString("specialization");
	                String availableShift = resultSet.getString("availableShift");
	                double fees = resultSet.getDouble("fees");

	                Doctor doctor = new Doctor(doctorId, doctorName, mobileNumber, specialization, availableShift, fees);
	                docList.add(doctor);
	            }
	        }
	        	catch(Exception e)
	        	{
	        		e.printStackTrace();
	        	}	
	        	return docList;
	}
	public int deleteDoctor(int id)
	{
		int rowsDeleted=0;
		String deleteSQL = "DELETE FROM doctors WHERE doctorId = ?";
        try  {
        	pst=con.prepareStatement(deleteSQL);
            pst.setInt(1, id);

            rowsDeleted = pst.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }        
		return rowsDeleted;
	}
	public List<Doctor> searchByFeesAndShift(String shift) {
        List<Doctor> docList = new ArrayList<>();
        String selectSQL = "SELECT * FROM doctors WHERE fees < (SELECT MIN(fees) FROM doctors WHERE availableShift = ?)";

        try  {
        	
        	pst=con.prepareStatement(selectSQL);
            pst.setString(1, shift);

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                int doctorId = resultSet.getInt("doctorId");
                String doctorName = resultSet.getString("doctorName");
                String mobileNumber = resultSet.getString("mobileNumber");
                String specialization = resultSet.getString("specialization");
                String availableShift = resultSet.getString("availableShift");
                double fees = resultSet.getDouble("fees");

                Doctor doctor = new Doctor(doctorId, doctorName, mobileNumber, specialization, availableShift, fees);
                docList.add(doctor);
            }
            
        }
        catch(Exception e)
    	{
    		e.printStackTrace();
    	}	
    	return docList;
           
	}
	public int countDoctorsByShift(String shift) {
        int count = 0;
        String selectSQL = "SELECT COUNT(*) FROM doctors WHERE availableShift = ?";

        try  {
        	pst=con.prepareStatement(selectSQL);
            pst.setString(1, shift);

            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }
}

