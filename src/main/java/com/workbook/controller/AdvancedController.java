package com.workbook.controller;

import com.workbook.config.TemplateEngineUtil;
import com.workbook.dao.QuestionsDao;
import com.workbook.dao.implementation.QuestionsDaoMemAdvanced;
import com.workbook.dao.implementation.QuestionsDaoMemOop;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/advanced"})

public class AdvancedController extends HttpServlet {

    private QuestionsDao questionsDaoDataAdvanced = QuestionsDaoMemAdvanced.getInstance();

    public AdvancedController() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("title", "Advanced questions");
        context.setVariable("questions", questionsDaoDataAdvanced.getQuestionsWithSearch());
        context.setVariable("page", "/advanced");
        engine.process("questions.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String answer = request.getParameter("answer");
        String questionKey = request.getParameter("questionKey");
        questionsDaoDataAdvanced.addAnswer(questionKey, answer);
        response.sendRedirect("/advanced");

    }

}
