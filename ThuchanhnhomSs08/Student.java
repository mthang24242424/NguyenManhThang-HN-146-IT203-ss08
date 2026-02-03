package ThuchanhnhomSs08;

/**
 * Lớp Student quản lý thông tin sinh viên
 */
public class Student {

    private String studentId; // Mã sinh viên
    private String fullName; // Họ tên
    private int age; // Tuổi
    private String gender; // Giới tính

    private double mathScore; // Điểm Toán
    private double physicsScore; // Điểm Lý
    private double chemistryScore; // Điểm Hóa

    private double averageScore; // Điểm trung bình
    private String classification; // Xếp loại

    public Student(String studentId, String fullName, int age, String gender,
                   double mathScore, double physicsScore, double chemistryScore) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.mathScore = mathScore;
        this.physicsScore = physicsScore;
        this.chemistryScore = chemistryScore;
        calculateAverageScore();
        classify();
    }

    // ===== Getter =====
    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public String getClassification() {
        return classification;
    }

    // ===== Setter =====
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setScores(double math, double physics, double chemistry) {
        this.mathScore = math;
        this.physicsScore = physics;
        this.chemistryScore = chemistry;
        calculateAverageScore();
        classify();
    }

    // ===== Business logic =====
    private void calculateAverageScore() {
        averageScore = (mathScore + physicsScore + chemistryScore) / 3;
    }

    private void classify() {
        if (averageScore >= 8.0 &&
                mathScore >= 6.5 &&
                physicsScore >= 6.5 &&
                chemistryScore >= 6.5) {

            classification = "Giỏi";

        } else if (averageScore >= 6.5 &&
                mathScore >= 5.0 &&
                physicsScore >= 5.0 &&
                chemistryScore >= 5.0) {

            classification = "Khá";

        } else if (averageScore >= 5.0 &&
                mathScore >= 3.5 &&
                physicsScore >= 3.5 &&
                chemistryScore >= 3.5) {

            classification = "Trung bình";

        } else {
            classification = "Yếu";
        }
    }

    public void display() {
        System.out.printf(
                "%-10s %-20s %-5d %-8s %-8.2f %-10s\n",
                studentId, fullName, age, gender, averageScore, classification);
    }
}