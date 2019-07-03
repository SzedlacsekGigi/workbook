package com.workbook.dao.implementation;

import com.workbook.dao.QuestionsDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;


public class QuestionsDaoMem implements QuestionsDao {


    private String filePath;

    public HashMap<String, String> getSearchFormat(String[] list){
        HashMap<String, String> questsionsWithSearch = new HashMap<>();
        for (String line: list
             ) {
            questsionsWithSearch.put(line, getQuestionsURL(line));
        }
        return questsionsWithSearch;
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

    public String getQuestionsURL(String toFormat){
        String formatted = toFormat.replaceAll(" ", "+");
        return "https://google.com/search?q=" + formatted;
    }

    @Override
    public HashMap<String, String> getAll() throws IOException {
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





