package data_access_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

//import data_access.DbUtils;
import model.Student;

public class StudentDAO {

	public StudentDAO() {
		// In Tomcat 8 environment, the JDBC driver must be explicitly loaded as below
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}

	/**
	 * Open a new database connection
	 * 
	 * @throws SQLException
	 */
	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
				ConnectionParameters.password);
	}

	public List<Student> getAllStudents() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> allStudents = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student ORDER BY lastname";

			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");
				allStudents.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));

			}
		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");
			allStudents = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return allStudents;
	}

	public List<Student> getStudentByID(int studentID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentID);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");
				studentList.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));

			}

		} catch (SQLException sqle) {
			System.out.println("[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return studentList;
	}

	public String getAllStudentsJSON() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> allStudents = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student ORDER BY lastname";

			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");
				allStudents.add(new Student(id, firstname, lastname, streetaddress, postcode, postoffice));

			}
		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getAllStudents() failed. " + sqle.getMessage() + "\n");
			allStudents = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		Gson gson = new Gson();
		String jsonString = gson.toJson(allStudents);

		return jsonString;
	}

	// * @return 0 = Ok | 1 = Duplicate id | -1 = Other error

	public int insertStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode=-1;

		try {
			connection = openConnection();

			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstname());
			preparedStatement.setString(3, student.getLastname());
			preparedStatement.setString(4, student.getStreetaddress());
			preparedStatement.setString(5, student.getPostcode());
			preparedStatement.setString(6, student.getPostoffice());
			
			preparedStatement.executeUpdate();
			errorCode = 0;
			
		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				errorCode = 1;
			} else {
				System.out.println("\n[ERROR] StudentDAO: insertStudent() failed. " + sqle.getMessage() + "\n");
				errorCode = -1;
			}

		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}
		
		return errorCode;
		
	}

	// * @return 1 = Ok | 0 = id is not found| -1 = Other error
	public int deleteStudent(int studentId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "DELETE FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentId);

			errorCode = preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: deleteStudent() failed. " + sqle.getMessage() + "\n");
			errorCode = -1;
		}

		finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}

//* @return 0 = Ok | 1 = id is not  found | -1 = Other error

	public int updateStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "UPDATE Student SET firstname=?, lastname=?, streetaddress=?, postcode=?, postoffice=? WHERE id=?";

			preparedStatement = connection.prepareStatement(sqlText);

			preparedStatement.setString(1, student.getFirstname());
			preparedStatement.setString(2, student.getLastname());
			preparedStatement.setString(3, student.getStreetaddress());
			preparedStatement.setString(4, student.getPostcode());
			preparedStatement.setString(5, student.getPostoffice());
			preparedStatement.setInt(6, student.getId());

			System.out.println();

			errorCode = preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: insertStudent() failed. " + sqle.getMessage() + "\n");
			errorCode = -1;
		}

		finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}
}
