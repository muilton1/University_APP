package university.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import university.dto.Group;
import university.dto.GroupWithoutIdDto;
import university.service.GroupService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "GroupServlet", urlPatterns = "/groups")
public class GroupServlet extends HttpServlet {
    private final GroupService groupService;
    private ObjectMapper mapper;

    public GroupServlet() {
        this.groupService = GroupService.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        PrintWriter writer = resp.getWriter();

        List<Group> groups = groupService.getAll();
        writer.write(mapper.writeValueAsString(groups));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        GroupWithoutIdDto group = mapper.readValue(req.getInputStream(), GroupWithoutIdDto.class);
        groupService.create(group);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        Group group = mapper.readValue(req.getInputStream(), Group.class);
        int id = group.getId();

        GroupWithoutIdDto groupWithoutIdDto = mapper.readValue(req.getInputStream(), GroupWithoutIdDto.class);
        groupService.update(id, groupWithoutIdDto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        Group group = mapper.readValue(req.getInputStream(), Group.class);
        int id = group.getId();

        groupService.delete(id);
    }
}
