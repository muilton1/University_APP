package university.service;


import university.dao.StudentDao;
import university.dto.Student;
import university.dto.StudentWithoutIdDto;

import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private static final StudentService instance = new StudentService();

    private final StudentDao dao;

    private StudentService() {
        this.dao = StudentDao.getInstance();
    }

    public StudentWithoutIdDto create(StudentWithoutIdDto studentDto) {
        return this.dao.create(studentDto);
    }

    public List<Student> getAll() {
        return this.dao.read();
    }

    public Student get(int id) {
        List<Integer> getId = getAll().stream().map(Student::getId).collect(Collectors.toList());
        if (getId.contains(id)) {
            return this.dao.readOne(id);
        } else throw new IllegalArgumentException("Студента с таким ID не существует!");
    }

    public StudentWithoutIdDto update(int id, StudentWithoutIdDto studentDto) {
        List<Integer> getId = getAll().stream().map(Student::getId).collect(Collectors.toList());
        if (getId.contains(id)) {
            return this.dao.update(id, studentDto);
        } else throw new IllegalArgumentException("Студента с таким ID не существует!");
    }

    public void delete(int id) {
        List<Integer> getId = getAll().stream().map(Student::getId).collect(Collectors.toList());
        if (getId.contains(id)) {
            this.dao.delete(id);
        } else throw new IllegalArgumentException("Студента с таким ID не существует!");
    }

    public static StudentService getInstance() {
        return instance;
    }
}
