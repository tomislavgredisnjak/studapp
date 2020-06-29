package hr.tvz.gredisnjak.studapp;

public class CourseDTO {

    private final String name;
    private final Integer numberOfECTS;

    public CourseDTO( String name, Integer numberOfECTS) {
        this.name = name;
        this.numberOfECTS = numberOfECTS;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfECTS() {
        return numberOfECTS;
    }

    @Override
    public String toString(){
        return "CourseDTO{" +
                "Name=' " + name + '\'' +
                ", numberOfECTS=" + numberOfECTS +
                '}';
    }
}
