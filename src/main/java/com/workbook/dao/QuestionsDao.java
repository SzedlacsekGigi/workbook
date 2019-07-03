package com.workbook.dao;

import java.io.IOException;
import java.util.HashMap;

public interface QuestionsDao {

    HashMap<String, String> getAll() throws IOException;
    void setup(String filePath, Integer fromLine, Integer toLine);
    String read() throws IOException;

}
