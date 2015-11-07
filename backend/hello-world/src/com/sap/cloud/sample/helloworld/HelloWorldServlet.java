package com.sap.cloud.sample.helloworld;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementing simplest possible hello world application for SAP HANA Cloud Platform.
 */
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** {@inheritDoc} */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Gson a = new Gson();
    	ArrayList<String> teste = new ArrayList<String>();
    	teste.add("asid");
    	teste.add("Julio é legal");
    	
    	
        response.getWriter().println(a.toString());
    }
}
