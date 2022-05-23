package university.service;



import university.dao.StudentsByGroupDao;
import university.dto.Student;
import university.dto.StudentsByGroupDto;

import java.util.List;

public class StudentsByGroupService {
    private static final StudentsByGroupService instance = new StudentsByGroupService();

    private final StudentsByGroupDao dao;

    private StudentsByGroupService() {
        this.dao = StudentsByGroupDao.getInstance();
    }

    public void insert(List<Student> list, int id) {
        this.dao.insertStudents(list, id);
    }

    public void deleteStudents(List<Student> students, int id) {
        this.dao.deleteStudents(students, id);
    }

    public List<StudentsByGroupDto> getStudentsByGroup(int id) {
        return this.dao.getStudentsByGroup(id);
    }

    public static StudentsByGroupService getInstance() {
        return instance;
    }
}
