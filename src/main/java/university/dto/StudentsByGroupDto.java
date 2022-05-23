package university.dto;

public class StudentsByGroupDto {
    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;
    private String groupName;

    public StudentsByGroupDto(String name, int age, double score, boolean olympicGamer, String groupName) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getScore() {
        return score;
    }

    public boolean isOlympicGamer() {
        return olympicGamer;
    }

    public String getGroupName() {
        return groupName;
    }
}
