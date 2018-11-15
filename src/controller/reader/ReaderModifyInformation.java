package controller.reader;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReaderDAO;
import entity.Reader;
import util.*;

/**
 * Servlet implementation class ReaderModifyInformation
 */
public class ReaderModifyInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReaderModifyInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Initialize
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession(); 
		
		//get reader id
		Reader reader=(Reader)session.getAttribute("ReaderEntity");
		int id=reader.getId();
		
		//get new information
		String name=(String) request.getParameter("newName");
		String password=(String) request.getParameter("newPassword");
		String email=(String) request.getParameter("newEmail");		
		ReaderDAO readerDAO=new ReaderDAO();
		
		// Ilegal character filter
		CharacterFilter cf=new CharacterFilter();
		String newName=cf.isName(name);
		String newPassword=cf.isPassword(password);
        String newEmail=cf.isEmail(email);
       
        //output to console
        System.out.println(name);
        System.out.println(password);
        System.out.println(email);
       
        //judge if null
        if(newName == null || newName== ""||newName.isEmpty()) {
			out.print("<script>alert('Please enter the full keyword!');window.history.back();</script>");
		}
        if(newPassword ==null||newPassword== ""||newPassword.isEmpty()) {
			out.print("<script>alert('Please enter the full keyword!');window.history.back();</script>");
		}
        if(newEmail==null||newEmail == ""||newEmail.isEmpty()) {
			out.print("<script>alert('Please enter the full keyword!');window.history.back();</script>");
		}
        
        //execute the update 
        readerDAO.updateReaderInformation(id, newName, newPassword, newEmail);  
        out.print("<script>alert('Update information successfully! Please login again!');window.location='ReaderLogin.jsp';</script>");
	}
}