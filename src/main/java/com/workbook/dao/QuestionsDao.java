package com.workbook.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface QuestionsDao {

    HashMap<String, ArrayList<String>> getAll() throws IOException;
    void setup(String filePath, Integer fromLine, Integer toLine);
    String read() throws IOException;
    void addAnswer(String questionKey, String answer);
    HashMap<String, ArrayList<String>> getQuestionsWithSearch();


}
