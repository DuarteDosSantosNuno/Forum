package com.nuno.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuno.beans.BeanException;
import com.nuno.beans.UserEntity;
import com.nuno.dao.*;

@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
    
	public void init() throws ServletException {
		//MongoClient mongoclient = DaoFactory.mongoInstance();
		//this.userDao = DaoFactory.getUserDao();
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setAttribute("users", userDao.lister());
        }
        catch (Exception e) {
            request.setAttribute("erreur", e.getMessage());
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserEntity user = new UserEntity();
        try {
        	user.setUsername(request.getParameter("username"));
        	user.setPassword(request.getParameter("password"));
			userDao.ajouter(user);
			request.setAttribute("users", userDao.lister());
		} catch (BeanException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
                       
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
	}

}
