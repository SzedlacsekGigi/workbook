package com.workbook.controller;

import com.workbook.config.TemplateEngineUtil;
import com.workbook.dao.QuestionsDao;
import com.workbook.dao.implementation.QuestionsDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/advanced"})

public class AdvancedController extends HttpServlet {

    private QuestionsDao questionsDaoData = QuestionsDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        questionsDaoData.setup("src/main/resources/questions_advanced.txt", 1, 68);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("title", "Advanced questions");
        context.setVariable("questions", questionsDaoData.getAll());
        engine.process("questions.html", context, resp.getWriter());
    }

}
