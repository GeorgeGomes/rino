package br.com.rino.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rino.dao.PhotoDAO;
import br.com.rino.entity.Photo;

public class DynamicImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String PATH_FOLDER = "C://rino//gallery//offline//";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		try {

			// PEGA IMAGEM EM UM PATH.
			/*
			String file = request.getParameter("file");
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(PATH_FOLDER + file));
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			in.close();
			response.getOutputStream().write(bytes);
			*/
			
			// PEGA IMAGEM EM BLOB.
			
			String file = request.getParameter("file");
			PhotoDAO photoDAO = new PhotoDAO();
			Photo photo = photoDAO.edit(file);
			response.getOutputStream().write(photo.getFile());
			
		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
