package university.service;


import university.dao.GroupDao;
import university.dto.Group;
import university.dto.GroupWithoutIdDto;

import java.util.List;
import java.util.stream.Collectors;

public class GroupService {
    private static final GroupService instance = new GroupService();

    private final GroupDao dao;

    private GroupService() {
        this.dao = GroupDao.getInstance();
    }

    public GroupWithoutIdDto create(GroupWithoutIdDto group) {
        return this.dao.create(group);
    }

    public List<Group> getAll() {
        return this.dao.read();
    }

    public Group get(int id) {
        List<Integer> getId = getAll().stream().map(Group::getId).collect(Collectors.toList());
        if (getId.contains(id)) {
            return this.dao.readOne(id);
        } else throw new IllegalArgumentException("Группы с таким ID не существует!");
    }

    public GroupWithoutIdDto update(int id, GroupWithoutIdDto group) {
        List<Integer> getId = getAll().stream().map(Group::getId).collect(Collectors.toList());
        if (getId.contains(id)) {
            return this.dao.update(id, group);
        } else throw new IllegalArgumentException("Группы с таким ID не существует!");
    }

    public void delete(int id) {
        List<Integer> getId = getAll().stream().map(Group::getId).collect(Collectors.toList());
        if (getId.contains(id)) {
            this.dao.delete(id);
        } else throw new IllegalArgumentException("Группы с таким ID не существует!");
    }

    public static GroupService getInstance() {
        return instance;
    }
}

