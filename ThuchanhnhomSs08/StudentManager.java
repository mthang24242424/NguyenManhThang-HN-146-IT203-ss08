package ThuchanhnhomSs08;

/**
 * Lớp quản lý danh sách sinh viên
 */
public class StudentManager {

    private Student[] students;
    private int count;

    public StudentManager(int size) {
        students = new Student[size];
        count = 0;
    }

    // 1. Thêm sinh viên
    public boolean addStudent(Student s) {
        if (count == students.length) return false;
        if (findById(s.getStudentId()) != -1) return false;
        students[count++] = s;
        return true;
    }

    // 2. Hiển thị
    public void displayAll() {
        if (count == 0) {
            System.out.println("Danh sách rỗng!");
            return;
        }
        System.out.printf(
                "%-10s %-20s %-5s %-8s %-8s %-10s\n",
                "Mã SV", "Họ tên", "Tuổi", "GT", "ĐTB", "Xếp loại"
        );
        for (int i = 0; i < count; i++) {
            students[i].display();
        }
    }

    // 3. Tìm theo mã
    public int findById(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentId().equalsIgnoreCase(id))
                return i;
        }
        return -1;
    }

    // 3.1 Tìm theo tên
    public void findByName(String name) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (students[i].getFullName().toLowerCase().contains(name.toLowerCase())) {
                students[i].display();
                found = true;
            }
        }
        if (!found) System.out.println("Không tìm thấy sinh viên!");
    }

    // 4. Cập nhật
    public boolean updateStudent(String id, String name, int age, String gender,
                                 double math, double physics, double chemistry) {
        int idx = findById(id);
        if (idx == -1) return false;

        students[idx].setFullName(name);
        students[idx].setAge(age);
        students[idx].setGender(gender);
        students[idx].setScores(math, physics, chemistry);
        return true;
    }

    // 5. Xóa
    public boolean removeStudent(String id) {
        int idx = findById(id);
        if (idx == -1) return false;

        for (int i = idx; i < count - 1; i++) {
            students[i] = students[i + 1];
        }
        students[--count] = null;
        return true;
    }

    // 7. Sắp xếp theo tên
    public void sortByName() {
        for (int i = 0; i < count - 1; i++) {
            int min = i;
            for (int j = i + 1; j < count; j++) {
                if (students[j].getFullName()
                        .compareToIgnoreCase(students[min].getFullName()) < 0)
                    min = j;
            }
            Student tmp = students[i];
            students[i] = students[min];
            students[min] = tmp;
        }
    }

    // 7.1 Sắp xếp theo điểm TB giảm dần
    public void sortByAverageScoreDesc() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (students[j].getAverageScore()
                        < students[j + 1].getAverageScore()) {
                    Student tmp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = tmp;
                }
            }
        }
    }

    // 8. Thống kê
    public void statistics() {
        if (count == 0) {
            System.out.println("Danh sách rỗng!");
            return;
        }

        int gioi = 0, kha = 0, tb = 0, yeu = 0;
        double sum = 0;

        Student max = students[0];
        Student min = students[0];

        for (int i = 0; i < count; i++) {
            Student s = students[i];
            sum += s.getAverageScore();

            switch (s.getClassification()) {
                case "Giỏi": gioi++; break;
                case "Khá": kha++; break;
                case "Trung bình": tb++; break;
                case "Yếu": yeu++; break;
            }

            if (s.getAverageScore() > max.getAverageScore()) max = s;
            if (s.getAverageScore() < min.getAverageScore()) min = s;
        }

        System.out.println("===== THỐNG KÊ =====");
        System.out.println("Tổng SV: " + count);
        System.out.println("Giỏi: " + gioi);
        System.out.println("Khá: " + kha);
        System.out.println("Trung bình: " + tb);
        System.out.println("Yếu: " + yeu);
        System.out.printf("Điểm TB chung: %.2f\n", sum / count);

        System.out.println("\nSV điểm cao nhất:");
        max.display();
        System.out.println("SV điểm thấp nhất:");
        min.display();
    }
}