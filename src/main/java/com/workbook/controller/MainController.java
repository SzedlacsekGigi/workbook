package com.workbook.controller;

import com.workbook.config.TemplateEngineUtil;
import com.workbook.dao.QuestionsDao;
import com.workbook.dao.implementation.QuestionsDaoMemOop;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})


public class MainController extends HttpServlet {

    private QuestionsDao questionsDaoData = QuestionsDaoMemOop.getInstance();

    public MainController() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        questionsDaoData.setup("src/main/resources/category.txt", 1, 4);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("title", "Codecool Tech-Interview Workbook");
        context.setVariable("categories", questionsDaoData.getAll());
        engine.process("main.html", context, resp.getWriter());
    }

}
