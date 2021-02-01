package be.pxl.ja.exercise1;

import java.time.LocalDate;
import java.util.Objects;

public class Student {
	private String name;
	private int graduationYear;
	private int score;
	private LocalDate dateOfBirth;

	public Student(String name, int graduationYear, int score, LocalDate dateOfBirth) {
		this.name = name;
		this.graduationYear = graduationYear;
		this.score = score;
		this.dateOfBirth = dateOfBirth;
	}

	public String getName() {
		return name;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public int getScore() {
		return score;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return graduationYear == student.graduationYear &&
				score == student.score &&
				Objects.equals(name, student.name) &&
				Objects.equals(dateOfBirth, student.dateOfBirth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, graduationYear, score, dateOfBirth);
	}
}
