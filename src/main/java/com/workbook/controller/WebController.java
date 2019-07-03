package com.workbook.controller;

import com.workbook.config.TemplateEngineUtil;
import com.workbook.dao.QuestionsDao;
import com.workbook.dao.implementation.QuestionsDaoSQL;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/web"})

public class WebController extends HttpServlet {

    private QuestionsDao questionsDaoData = QuestionsDaoSQL.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        questionsDaoData.setup("src/main/resources/questions_web.txt", 1, 59); //// TODO: 2019.07.03.  
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("title", "Web questions");
        context.setVariable("questions", questionsDaoData.getAll());
        engine.process("questions.html", context, resp.getWriter());
    }

}