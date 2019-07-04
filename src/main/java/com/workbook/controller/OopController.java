package com.workbook.controller;

import com.workbook.config.TemplateEngineUtil;
import com.workbook.dao.QuestionsDao;
import com.workbook.dao.implementation.QuestionsDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/oop"})

public class OopController extends HttpServlet {

    private QuestionsDao questionsDaoData = QuestionsDaoMem.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        questionsDaoData.setup("src/main/resources/questions_oop.txt", 1, 79);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("title", "OOP questions");
        context.setVariable("questions", questionsDaoData.getAll());
        engine.process("questions.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String answer = request.getParameter("answer");
        String questionKey = request.getParameter("questionKey");
        questionsDaoData.addAnswer(questionKey, answer);
        response.sendRedirect("/oop");

    }
}
