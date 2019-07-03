package com.workbook.controller;


import com.workbook.config.TemplateEngineUtil;
import com.workbook.dao.implementation.OopDaoSQL;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/oop"})

public class OopController extends HttpServlet {

    private com.workbook.dao.OopDao oopDaoData = OopDaoSQL.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("OopQuestions", oopDaoData.getAll());

        engine.process("index.html", context, resp.getWriter());
    }

}
