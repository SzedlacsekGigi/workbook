package com.workbook.dao.implementation;

import com.workbook.dao.QuestionsDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class QuestionsDaoSQL implements QuestionsDao {


    private String filePath;
    private Integer fromLine;
    private Integer toLine;


    private static QuestionsDaoSQL instance = null;

    private QuestionsDaoSQL() {
    }

    public static QuestionsDaoSQL getInstance() {
        if (instance == null) {
            instance = new QuestionsDaoSQL();
        }
        return instance;
    }

    public String getQuestionsURL(String toFormat){
        String formatted = toFormat.replaceAll(" ", "+");
        return "https://google.com/search?=q" + formatted;
    }

    @Override
    public String[] getAll() throws IOException {
        String fullText = read();
        String[] lines = fullText.split("\n");
        return lines;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {
        if (toLine < fromLine || fromLine < 1) throw new IllegalArgumentException("Not valid line numbers");
        this.filePath = filePath;
        this.fromLine = fromLine - 1;
        this.toLine = toLine;
    }

    public String read() throws IOException {
        String fullText = "";
        for (int i = 0; i < Files.readAllLines(Paths.get(filePath)).size(); i++) {
            String lineToReturn = Files.readAllLines(Paths.get(filePath)).get(i);
            if (i == Files.readAllLines(Paths.get(filePath)).size()-1){
                return fullText += lineToReturn;
            }
            fullText += lineToReturn + "\n";
        }
        return fullText;
    }
}





