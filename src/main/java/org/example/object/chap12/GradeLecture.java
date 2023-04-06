package org.example.object.chap12;

import static java.util.stream.Collectors.*;

import java.util.List;

public class GradeLecture extends Lecture {
	private List<Grade> grades;

	public GradeLecture(String title, int pass, List<Grade> grades, List<Integer> scores) {
		super(pass, title, scores);
		this.grades = grades;
	}

	@Override
	public String evaluate() {
		return super.evaluate() + gradeStatistics();
	}

	private String gradeStatistics() {
		return grades.stream()
			.map(this::format)
			.collect(joining(" "));
	}

	private String format(Grade grade) {
		return String.format("%s:%d", grade.getName(), gradeCount(grade));
	}

	private long gradeCount(Grade grade) {
		return getScores().stream()
			.filter(grade::include)
			.count();
	}

	public double average(String gradeName) {
		return grades.stream()
			.filter(grade -> grade.isName(gradeName))
			.findFirst()
			.map(this::gradeAverage)
			.orElse(0.0);
	}

	private double gradeAverage(Grade grade) {
		return getScores().stream()
			.filter(grade::include)
			.mapToInt(Integer::intValue)
			.average()
			.orElse(0.0);
	}
}
