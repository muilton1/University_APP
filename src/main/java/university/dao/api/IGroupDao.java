package university.dao.api;


import university.dto.Group;
import university.dto.GroupWithoutIdDto;

import java.util.List;

public interface IGroupDao extends ICRUDDao<Group, GroupWithoutIdDto, Integer> {
    GroupWithoutIdDto create(GroupWithoutIdDto group);

    List<Group> read();

    Group readOne(Integer id);

    GroupWithoutIdDto update(Integer id, GroupWithoutIdDto group);

    void delete(Integer id);
}
