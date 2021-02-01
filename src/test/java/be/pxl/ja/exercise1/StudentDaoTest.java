package be.pxl.ja.exercise1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentDaoTest {
    List<Student> studentList;
    List<String> actualResult;
    List<String> expectedResult;
    @BeforeEach
    void setUp() {
         studentList = StudentDao.createStudents();
    }

    @Test
    void anniversaryToday() {
        actualResult = studentList.stream()
                .filter(student -> (student.getDateOfBirth().getDayOfMonth() == LocalDate.now().getDayOfMonth() && student.getDateOfBirth().getMonth() == LocalDate.now().getMonth()))
                .map(Student::getName)
                .collect(toList());
        expectedResult = List.of("Carol");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void carol() {
        Optional<Student> carol = studentList.stream()
                .filter(student -> student.getName().equalsIgnoreCase("carol"))
                .findFirst();
        assertTrue(carol.isPresent());
        assertEquals(carol.get(), new Student("Carol", 2018, 67, LocalDate.of(1997, 12,21)));
    }

    @Test
    void highScore() {
        OptionalInt highScore = studentList.stream()
                .mapToInt(Student::getScore)
                .max();
        assertEquals(highScore.getAsInt(), 98);
    }

    @Test
    void oldestPerson() {
        Optional<Student> oldestStudent = studentList.stream()
                .reduce((student1, student2) ->
                        AgeCalculator.calculateAge(student1.getDateOfBirth(), LocalDate.now()) > AgeCalculator.calculateAge(student2.getDateOfBirth(), LocalDate.now()) ? student1 : student2);
        assertEquals(oldestStudent.get(), (new Student("Gary", 2017,88, LocalDate.of(1996, 7,12))));
    }

    @Test
    void commaSeparatedList() {
        String actualResult = studentList.stream().map(Student::getName).collect(joining(", "));
        String expectedResult = "Alice, Bob, Carol, David, Eric, Frank, Gary, Henry, Ivan, John";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void youngestPerson() {
        Optional<Student> youngestStudent = studentList.stream()
                .reduce((student1, student2) ->
                        AgeCalculator.calculateAge(student1.getDateOfBirth(), LocalDate.now()) < AgeCalculator.calculateAge(student2.getDateOfBirth(), LocalDate.now()) ? student1 : student2);
        assertEquals(youngestStudent.get(), (new Student("Ivan", 2018, 66, LocalDate.of(1999, 3,21))));
    }

    @Test
    void mapAverageScore() {
        Map<Integer, Double> result = studentList.stream().collect(groupingBy(Student::getGraduationYear, averagingInt(Student::getScore)));
        result.forEach((key, value) -> System.out.printf("%d - %.2f%%%n", key, value));
    }

    @Test
    void mapSort() {
        studentList.stream()
                .sorted(Comparator.comparing(Student::getGraduationYear).thenComparing(Student::getScore).reversed())
                .forEach(s -> System.out.println(s.getName() + " - " + s.getGraduationYear() + " - " + s.getScore()));

    }
}

class AgeCalculator {
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
