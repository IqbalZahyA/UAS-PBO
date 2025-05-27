public class Field {

    private final String fieldId;
    private final String name;
    private final String type;
    private String status;

    public Field(String fieldId, String name, String type) {
        this.fieldId = fieldId;
        this.name = name;
        this.type = type;
        this.status = "Available";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getInfo() {
        return fieldId + " - " + name + " (" + type + ") [" + status + "] ";
    }

    public String getName() {
        return name;
    }

    public String getFieldId() {
        return fieldId;
    }
}
