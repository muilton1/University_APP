package university.dto;

import java.io.Serializable;

public class Group implements Serializable {
    private int id;
    private String groupName;

    public Group() {
    }

    public Group(int id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
