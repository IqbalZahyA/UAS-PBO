public class Field {
    private final String fieldId;
    private String fieldName;
    private String fieldType;
    private String status; // Tambahkan atribut status

    // Constructor
    public Field(String fieldId, String fieldName, String fieldType) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.status = "Available"; // Default status
    }

    // Getter dan Setter
    public String getFieldId() {
        return fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Metode tambahan (opsional)
    public String getInfo() {
        return "ID: " + fieldId + ", Nama: " + fieldName + ", Tipe: " + fieldType + ", Status: " + status;
    }
}