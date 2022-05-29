package university.dto;


public class StudentsByGroupDto {
    private StudentWithoutIdDto student;
    private GroupWithoutIdDto groupName;

    public StudentsByGroupDto(StudentWithoutIdDto student, GroupWithoutIdDto groupName) {
        this.student = student;
        this.groupName = groupName;
    }

    public StudentWithoutIdDto getStudent() {
        return student;
    }

    public void setStudent(StudentWithoutIdDto student) {
        this.student = student;
    }

    public GroupWithoutIdDto getGroupName() {
        return groupName;
    }

    public void setGroupName(GroupWithoutIdDto groupName) {
        this.groupName = groupName;
    }
}
