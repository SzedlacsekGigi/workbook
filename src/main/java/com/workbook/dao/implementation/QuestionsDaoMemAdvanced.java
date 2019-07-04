package com.workbook.dao.implementation;

import com.workbook.dao.QuestionsDao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class QuestionsDaoMemAdvanced implements QuestionsDao {

    public HashMap<String, ArrayList<String>> getQuestionsWithSearch() {
        return questionsWithSearch;
    }

    public HashMap<String, ArrayList<String>> questionsWithSearch = new HashMap<>();

    private String filePath;

    public HashMap<String, ArrayList<String>> getSearchFormat(String[] list) {
        for (String line : list
        ) {
            ArrayList<String> values = new ArrayList<>();
            values.add(getQuestionsURLGoogle(line));
            values.add(getQuestionsURLStackoverflow(line));
            values.add(getQuestionsURLW3School(line));
            values.add(3, "Unanswered");
            questionsWithSearch.put(line, values);
        }
        return questionsWithSearch;
    }


    private static QuestionsDaoMemAdvanced instance = null;

    private QuestionsDaoMemAdvanced() throws IOException {
        setup("src/main/resources/questions_advanced.txt", 1, 68);
        this.getAll();
    }

    public static QuestionsDaoMemAdvanced getInstance() throws IOException {
        if (instance == null) {
            instance = new QuestionsDaoMemAdvanced();
        }
        return instance;
    }

    public String getQuestionsURLGoogle(String toFormat) {
        String formatted = toFormat.replaceAll(" ", "+");
        String reformatted = formatted.replaceAll("'", "");
        return "https://google.com/search?q=" + reformatted;
    }

    public String getQuestionsURLStackoverflow(String toFormat) {
        String formatted = toFormat.replaceAll(" ", "+");
        String reformatted = formatted.replaceAll("'", "");
        return "https://www.google.com/search?q=site%3Astackoverflow.com+" + reformatted;
    }

    public String getQuestionsURLW3School(String toFormat) {
        String formatted = toFormat.replaceAll(" ", "+");
        String reformatted = formatted.replaceAll("'", "");
        return "https://www.google.com/search?q=site%3Aw3schools.com+" + reformatted;
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
            if (i == Files.readAllLines(Paths.get(filePath)).size() - 1) {
                return fullText += lineToReturn;
            }
            fullText += lineToReturn + "\n";
        }
        return fullText;
    }

    @Override
    public void addAnswer(String questionKey, String answer) {
        for (Map.Entry<String, ArrayList<String>> entry : questionsWithSearch.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            if (key.equals(questionKey)) {
                value.set(3, answer);
            }
        }
    }
}





