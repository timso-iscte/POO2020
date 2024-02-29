package pt.iscte.dcti.poo.sokoban.starter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Score implements Comparator<Integer> {
	private int score;
	private int level;
	private List<List<Integer>> scoreBoard = new ArrayList<List<Integer>>();
	private List<Integer> scoreBoard0 = new ArrayList<>(5);
	private List<Integer> scoreBoard1 = new ArrayList<>(5);
	private List<Integer> scoreBoard2 = new ArrayList<>(5);
	private List<Integer> scoreBoard3 = new ArrayList<>(5);
	private List<Integer> scoreBoard4 = new ArrayList<>(5);
	private List<Integer> scoreBoard5 = new ArrayList<>(5);
	private List<Integer> scoreBoard6 = new ArrayList<>(5);
	private List<Integer> scoreBoard7 = new ArrayList<>(5);
	private List<Integer> scoreBoard8 = new ArrayList<>(5);
	private List<Integer> scoreBoard9 = new ArrayList<>(5);

	public Score(int level, int score) {
		this.level = level;
		this.score = score;
		scoreBoard.add(scoreBoard0);
		scoreBoard.add(scoreBoard1);
		scoreBoard.add(scoreBoard2);
		scoreBoard.add(scoreBoard3);
		scoreBoard.add(scoreBoard4);
		scoreBoard.add(scoreBoard5);
		scoreBoard.add(scoreBoard6);
		scoreBoard.add(scoreBoard7);
		scoreBoard.add(scoreBoard8);
		scoreBoard.add(scoreBoard9);
		readFile();
	}

	public void readFile() {

		try {
			File file = new File("scoreboard/scoreboard" + level);
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				if (scanner.hasNextInt()) {
					int prevScore = scanner.nextInt();
					scoreBoard.get(level).add(prevScore);

				} else
					scanner.nextLine();
			}
			scanner.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}

	@Override
	public int compare(Integer newScore, Integer oldScore) {
		if (newScore > oldScore)
			return -1;
		if (newScore < oldScore)
			return 1;
		else
			return 0;
	}

	public void addScore() {
		File file = new File("scoreboard/scoreboard" + this.level);
		if (scoreBoard.get(this.level).isEmpty() || (scoreBoard.get(this.level).size() < 5)) {
			scoreBoard.get(this.level).add(score);
			Collections.sort(scoreBoard.get(this.level));
			writeInFile(file, scoreBoard.get(this.level));
		} else
			for (int i : scoreBoard.get(this.level)) {
				if (compare(score, i) == 1) {
					scoreBoard.get(this.level).set(scoreBoard.get(this.level).size() - 1, score);
					Collections.sort(scoreBoard.get(this.level));
					writeInFile(file, scoreBoard.get(this.level));
					break;
				}
			}

	}

	public void writeInFile(File file, List<Integer> scoreboard) {
		PrintWriter printWriter = null;
		try {
			FileWriter fileWriter = new FileWriter(file);
			printWriter = new PrintWriter(fileWriter);
			String a = "ScoreBoard";
			printWriter.println(a);
			for (int i : scoreboard) {
				String b = i + " ";
				printWriter.println(b);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		printWriter.close();
		printWriter.flush();

	}

}
