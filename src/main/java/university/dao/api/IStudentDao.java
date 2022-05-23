package university.dao.api;


import university.dto.Student;
import university.dto.StudentWithoutIdDto;

import java.util.List;

public interface IStudentDao extends ICRUDDao<Student, StudentWithoutIdDto,Integer>{
    StudentWithoutIdDto create(StudentWithoutIdDto student);

    List<Student> read();

    Student readOne(Integer id);

    StudentWithoutIdDto update(Integer id, StudentWithoutIdDto student);

    public void delete(Integer id);
}
