package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareModificaRegistaServlet
 */
@WebServlet("/PrepareModificaRegistaServlet")
public class PrepareModificaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public PrepareModificaRegistaServlet() {
        super();
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String idParameter = request.getParameter("idRegista");

		try {

			request.setAttribute("edit_regista_attribute",
					MyServiceFactory.getRegistaServiceInstance().caricaSingoloElemento(Long.parseLong(idParameter)));
		 
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("regista/search.jsp").forward(request, response);
			return;
		}
		
		 request.getRequestDispatcher("regista/edit.jsp").forward(request, response);

	}
 
}
