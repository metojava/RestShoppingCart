package com.mycompany.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.mycompany.cart.ShoppingCart;
import com.mycompany.entities.Product;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet({ "/ControllerServlet", "/addtocart", "/login", "/viewcart",
		"/products" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url = "";
		String userPath = request.getServletPath();
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (userPath.equals("/addtocart")) {
			if (cart == null) {
				cart = new ShoppingCart();
				session.setAttribute("cart", cart);
			}
			String productId = request.getAttribute("productId").toString();
			String count = request.getAttribute("count").toString();
			// Client client = Client

			cart.addtocart(productId, Integer.parseInt(count));
			url = "viewcart";
		}
		else if(userPath.equals("/viewcart"))
		{
			
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
