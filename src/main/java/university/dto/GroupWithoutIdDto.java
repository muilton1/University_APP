package university.dto;

public class GroupWithoutIdDto {
    private String groupName;

    public GroupWithoutIdDto() {
    }

    public GroupWithoutIdDto(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
