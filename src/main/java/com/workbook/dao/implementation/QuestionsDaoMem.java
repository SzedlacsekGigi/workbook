package com.workbook.dao.implementation;

import com.workbook.dao.QuestionsDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;


public class QuestionsDaoMem implements QuestionsDao {


    private String filePath;

    public HashMap<String, ArrayList<String>> getSearchFormat(String[] list){
        HashMap<String, ArrayList<String>> questionsWithSearch = new HashMap<>();
        for (String line: list
             ) {
            ArrayList<String> values = new ArrayList<>();
            values.add(getQuestionsURLGoogle(line));
            values.add(getQuestionsURLStackoverflow(line));
            values.add(getQuestionsURLW3School(line));
            questionsWithSearch.put(line, values);
        }
        return questionsWithSearch;
    }


    private static QuestionsDaoMem instance = null;

    private QuestionsDaoMem() {
    }

    public static QuestionsDaoMem getInstance() {
        if (instance == null) {
            instance = new QuestionsDaoMem();
        }
        return instance;
    }

    public String getQuestionsURLGoogle(String toFormat){
        String formatted = toFormat.replaceAll(" ", "+");
        return "https://google.com/search?q=" + formatted;
    }

    public String getQuestionsURLStackoverflow(String toFormat){
        String formatted = toFormat.replaceAll(" ", "+");
        return "https://www.google.com/search?q=site%3Astackoverflow.com+" + formatted;
    }

    public String getQuestionsURLW3School(String toFormat){
        String formatted = toFormat.replaceAll(" ", "+");
        return "https://www.google.com/search?q=site%3Aw3schools.com+" + formatted;
    }

    @Override
    public HashMap<String, ArrayList<String>> getAll() throws IOException {
        String fullText = read();
        String[] lines = fullText.split("\n");
        return getSearchFormat(lines);
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {
        if (toLine < fromLine || fromLine < 1) throw new IllegalArgumentException("Not valid line numbers");
        this.filePath = filePath;
        Integer fromLine1 = fromLine - 1;
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





