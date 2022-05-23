package university.dto;

public class StudentWithoutIdDto {
    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;

    public StudentWithoutIdDto(String name, int age, double score, boolean olympicGamer) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.olympicGamer = olympicGamer;
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
}
