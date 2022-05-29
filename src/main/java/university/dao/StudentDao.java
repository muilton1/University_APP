package university.dao;


import university.dao.api.IStudentDao;
import university.dto.Student;
import university.dto.StudentWithoutIdDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDao implements IStudentDao {
    private static final StudentDao instance = new StudentDao();
    private static final String CREATE_STUDENT = "INSERT INTO univer.students(name, age, score, \"olympicGamer\") VALUES (?, ?, ?, ?)";
    private static final String READ_STUDENTS = "SELECT id, name, age, score, \"olympicGamer\" FROM univer.students ORDER BY id";
    private static final String READ_ONE_STUDENT = "SELECT id, name, age, score, \"olympicGamer\" FROM univer.students WHERE id=?";
    private static final String UPDATE_STUDENT = "UPDATE univer.students SET name=?, age=?, score=?, \"olympicGamer\"=? WHERE id=?";
    private static final String DELETE_STUDENT = "DELETE FROM univer.students WHERE id=?";
    private static final String ERROR = "Произошла ошибка! Повторите попытку позже!";

    private List<Student> studentsList = new ArrayList<>();

    public StudentWithoutIdDto create(StudentWithoutIdDto student) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_STUDENT);
        ) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getScore());
            statement.setBoolean(4, student.isOlympicGamer());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
        return student;
    }

    public List<Student> read() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_STUDENTS);
        ) {
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    studentsList.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
        return studentsList;
    }

    public Student readOne(Integer id) {
        Student studentDto = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ONE_STUDENT);
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    return map(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
        return studentDto;
    }

    public StudentWithoutIdDto update(Integer id, StudentWithoutIdDto student) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT);
        ) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setDouble(3, student.getScore());
            statement.setBoolean(4, student.isOlympicGamer());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
        return student;
    }

    public void delete(Integer id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
    }

    private Student map(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setScore(rs.getDouble("score"));
        student.setOlympicGamer(rs.getBoolean("olympicGamer"));
        return student;
    }

    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static StudentDao getInstance() {
        return instance;
    }

}
