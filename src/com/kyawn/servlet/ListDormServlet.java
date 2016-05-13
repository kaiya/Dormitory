package com.kyawn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.kyawn.service.ListDorms;

/**
 * Servlet implementation class ListDormServlet
 */
@WebServlet("/ListDormServlet")
public class ListDormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListDormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject jo = new JSONObject();
		ListDorms lb = new ListDorms();
		jo = lb.listDorms();
		response.setCharacterEncoding("utf-8");//注意，十分重要，设置响应字符编码格式，否则可能乱码
		PrintWriter out = response.getWriter();
		out.print(jo);
	}

}
