package university.dao;


import university.dto.Student;
import university.dto.StudentsByGroupDto;

import java.sql.*;
import java.util.*;


public class StudentsByGroupDao {
    private static final StudentsByGroupDao instance = new StudentsByGroupDao();
    private static final String INSERT_STUDENTS = "INSERT INTO univer.\"cross\"(\"groupId\", \"studentId\") VALUES (?, ?)";
    private static final String DELETE_STUDENTS = "DELETE FROM univer.\"cross\" WHERE \"groupId\"=? AND \"studentId\"=?";
    private static final String READ_STUDENTS_BY_GROUP = "SELECT\n" +
            "    students.name,\n" +
            "    students.age,\n" +
            "    students.score,\n" +
            "    students.\"olympicGamer\",\n" +
            "    groups.\"groupName\"\n" +
            "FROM\n" +
            "    univer.students\n" +
            "    JOIN univer.\"cross\" ON students.id = \"studentId\"\n" +
            "    JOIN univer.groups ON \"groupId\" = groups.id\n" +
            "WHERE\n" +
            "    \"groupId\" = ?";

    private static final String ERROR = "Произошла ошибка! Повторите попытку позже!";

    private List<StudentsByGroupDto> students = new ArrayList<>();

    public void insertStudents(List<Student> students, int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_STUDENTS);
        ) {
            statement.setInt(1, id);
            for (Student studentDto : students) {
                statement.setInt(2, studentDto.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
    }

    public void deleteStudents(List<Student> students, int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS);
        ) {
            statement.setInt(1, id);
            for (Student studentDto : students) {
                statement.setInt(2, studentDto.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
    }

    public List<StudentsByGroupDto> getStudentsByGroup(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_STUDENTS_BY_GROUP);
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    students.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
        return students;
    }

    private StudentsByGroupDto map(ResultSet rs) throws SQLException {
        return new StudentsByGroupDto(
                rs.getString("name"),
                rs.getInt("age"),
                rs.getDouble("score"),
                rs.getBoolean("olympicGamer"),
                rs.getString("groupName")
        );
    }

    public static StudentsByGroupDao getInstance() {
        return instance;
    }
}
