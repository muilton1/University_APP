package university.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import university.dto.Group;
import university.dto.Student;
import university.dto.StudentsByGroupDto;
import university.service.StudentsByGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "StudentsByGroupServlet", urlPatterns = "/university")

public class StudentsByGroupServlet extends HttpServlet {
    private final StudentsByGroupService insertService;
    private ObjectMapper mapper;


    public StudentsByGroupServlet() {
        this.insertService = StudentsByGroupService.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        Group group = mapper.readValue(req.getInputStream(), Group.class);
        int id = group.getId();
        List<StudentsByGroupDto> students = insertService.getStudentsByGroup(id);

        writer.write(mapper.writeValueAsString(students));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        List<Student> students = Arrays.asList(mapper.readValue(req.getInputStream(), Student[].class));

        String id = (req.getParameter("id"));
        insertService.insert(students, Integer.parseInt(id));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        List<Student> students = Arrays.asList(mapper.readValue(req.getInputStream(), Student[].class));

        String id = (req.getParameter("id"));
        insertService.deleteStudents(students, Integer.parseInt(id));
    }
}
