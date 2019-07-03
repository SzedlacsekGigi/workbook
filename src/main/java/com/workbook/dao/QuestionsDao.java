package com.workbook.dao;

import java.io.IOException;

public interface QuestionsDao {

    String[] getAll() throws IOException;
    void setup(String filePath, Integer fromLine, Integer toLine);
    String read() throws IOException;

}
