package com.cruds.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cruds.entity.Student;

public class StudentDAO {
	
	public boolean delete(int rollNo)
	{
		String sql = "delete from student where rollno=?";
		int rows = 0;
		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rollNo);
			
			rows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}	
	
	public List<Student> getAllStudent()
	{
		String sql = "select rollno,name from student";
		Student s = null;
		ArrayList<Student> list = new ArrayList<>();
		
		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs != null && rs.next())
			{
				s = new Student(rs.getString(2),rs.getInt(1));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();}
		return list;
	}	
	
	public boolean create(Student student)
	{
		String sql = "insert into student(rollno,name) values(?,?)";
		int rows = 0;
		
		try(Connection conn = DBConnectionManager.getConnection())
		{
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, student.getRollNo());
			ps.setString(2, student.getName());
			
			rows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}	

}
