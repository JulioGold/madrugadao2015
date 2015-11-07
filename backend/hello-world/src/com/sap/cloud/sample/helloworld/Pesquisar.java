package com.sap.cloud.sample.helloworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
 
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Pesquisar extends HttpServlet {
	/**
    *
     */
    private static final long serialVersionUID = 1L;
   
    private DataSource dataSource;
   
   
	 @Override
	 public void init() throws ServletException {
		 try {
	                 InitialContext initialContext = new InitialContext();
	                 dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/DefaultDB");
	          }
	          catch (NamingException e) {
	                 throw new ServletException(e.getMessage());
	          }
	 }
   
   
    /**
    * Handles HTTP GET request from a client.
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	JsonObject jsonResponse = new JsonObject();
         try {
        	 Connection connection = dataSource.getConnection();
        	 try {
        		 PreparedStatement preparedStatement = connection.prepareStatement("select top 10 * from MADRUGADAO.AIH");
        		 ResultSet resultSet = preparedStatement.executeQuery();
        		 ResultSetMetaData meta = resultSet.getMetaData();
        		 JsonArray rows = new JsonArray ();
        		 //resultSet.next();
        		 //response.getWriter().write(resultSet.getString(1));
                 
        		 while (resultSet.next()) {
        			 JsonObject obj = new JsonObject();
        			 for (int i = 1; i <= meta.getColumnCount(); i++) {
						String column = meta.getColumnName(i);
						String value = resultSet.getString(i);
						//row.add(new JsonPrimitive(resultSet.getString(column)));
						obj.addProperty(column, value);
        			 }
        			 rows.add(obj);
        		 }
        		 jsonResponse.add("values", rows);
        	 }
        	 catch (SQLException e) {
        		 throw new SQLException("Failed to select the data for the table:" + e.getMessage(), e);
        	 }
        	 finally {
        		 connection.close();
        	 }
         } catch (SQLException e) {
         }
         
         
         response.setContentType("application/json");
         response.setStatus(HttpServletResponse.SC_OK);
         response.setCharacterEncoding("UTF-8");
         PrintWriter writer = response.getWriter();
         writer.print(jsonResponse.toString());
         writer.flush();
         writer.close();
    }
}
