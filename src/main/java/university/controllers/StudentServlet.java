package university.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import university.dto.Student;
import university.dto.StudentWithoutIdDto;
import university.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {
    private final StudentService studentService;
    private ObjectMapper mapper;


    public StudentServlet() {
        this.studentService = StudentService.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        List<Student> all = studentService.getAll();
        writer.write(mapper.writeValueAsString(all));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        StudentWithoutIdDto student = mapper.readValue(req.getInputStream(), StudentWithoutIdDto.class);
        studentService.create(student);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        Student student = mapper.readValue(req.getInputStream(), Student.class);
        int id = student.getId();

        StudentWithoutIdDto studentWithoutIdDto = mapper.readValue(req.getInputStream(), StudentWithoutIdDto.class);
        studentService.update(id, studentWithoutIdDto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        Student student = mapper.readValue(req.getInputStream(), Student.class);
        int id = student.getId();
        studentService.delete(id);
    }
}
