package university.dao;

import university.dao.api.IGroupDao;
import university.dto.Group;
import university.dto.GroupWithoutIdDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements IGroupDao {
    private static final GroupDao instance = new GroupDao();

    private static final String CREATE_GROUP = "INSERT INTO univer.groups(\"groupName\") VALUES (?)";
    private static final String READ_GROUPS = "SELECT id, \"groupName\" FROM univer.groups ORDER BY id";
    private static final String READ_ONE_GROUP = "SELECT \"groupName\" FROM univer.groups WHERE id=?";
    private static final String UPDATE_GROUP = "UPDATE univer.groups SET \"groupName\"=? WHERE id=?";
    private static final String DELETE_GROUP = "DELETE FROM univer.groups WHERE id=?";
    private static final String ERROR = "Произошла ошибка! Повторите попытку позже!";

    private List<Group> groupsList = new ArrayList<>();

    public GroupWithoutIdDto create(GroupWithoutIdDto group) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_GROUP);
        ) {
            statement.setString(1, group.getGroupName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
        return group;
    }

    public List<Group> read() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_GROUPS);
        ) {
            try (ResultSet resultSet = statement.executeQuery();) {
                while (resultSet.next()) {
                    groupsList.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
        return groupsList;
    }

    public Group readOne(Integer id) {
        Group group = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ONE_GROUP);
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
        return group;
    }

    public GroupWithoutIdDto update(Integer id, GroupWithoutIdDto group) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_GROUP);
        ) {
            statement.setString(1, group.getGroupName());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
        return group;
    }

    public void delete(Integer id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_GROUP);
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(ERROR);
        }
    }

    private Group map(ResultSet rs) throws SQLException {
        return new Group(
                rs.getInt("id"),
                rs.getString("groupName")
        );
    }

    public void close() throws Exception {
        ConnectionFactory.close();
    }

    public static GroupDao getInstance() {
        return instance;
    }
}
