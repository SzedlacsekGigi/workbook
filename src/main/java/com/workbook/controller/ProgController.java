package com.workbook.controller;

import com.workbook.config.TemplateEngineUtil;
import com.workbook.dao.QuestionsDao;
import com.workbook.dao.implementation.QuestionsDaoMemOop;
import com.workbook.dao.implementation.QuestionsDaoMemProg;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/progbasics"})

public class ProgController extends HttpServlet {

    private QuestionsDao questionsDaoDataProg = QuestionsDaoMemProg.getInstance();

    public ProgController() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("title", "Prog Basics questions");
        context.setVariable("questions", questionsDaoDataProg.getQuestionsWithSearch());
        context.setVariable("page", "/progbasics");
        engine.process("questions.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String answer = request.getParameter("answer");
        String questionKey = request.getParameter("questionKey");
        questionsDaoDataProg.addAnswer(questionKey, answer);
        response.sendRedirect("/progbasics");

    }
}
