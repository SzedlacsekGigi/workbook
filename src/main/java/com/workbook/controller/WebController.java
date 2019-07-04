package com.workbook.controller;

import com.workbook.config.TemplateEngineUtil;
import com.workbook.dao.QuestionsDao;
import com.workbook.dao.implementation.QuestionsDaoMemOop;
import com.workbook.dao.implementation.QuestionsDaoMemWeb;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/web"})

public class WebController extends HttpServlet {

    private QuestionsDao questionsDaoDataWeb = QuestionsDaoMemWeb.getInstance();

    public WebController() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("title", "Web questions");
        context.setVariable("questions", questionsDaoDataWeb.getQuestionsWithSearch());
        context.setVariable("page", "/web");
        engine.process("questions.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String answer = request.getParameter("answer");
        String questionKey = request.getParameter("questionKey");
        questionsDaoDataWeb.addAnswer(questionKey, answer);
        response.sendRedirect("/web");

    }
}
