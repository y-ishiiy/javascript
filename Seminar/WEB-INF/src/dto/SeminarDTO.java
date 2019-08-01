package dto;

public class SeminarDTO {
    private int id;
    private String name;
    private String crowded;

    public SeminarDTO(int id, String name, String crowded) {
        this.id = id;
        this.name = name;
        this.crowded = crowded;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCrowded() {
        return crowded;
    }

}