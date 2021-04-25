package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.service.MyServiceFactory;


@WebServlet("/PrepareModificaFilmServlet")
public class PrepareModificaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PrepareModificaFilmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String idParameter = request.getParameter("idFilm");

		try {

			request.setAttribute("edit_film_attribute",
					MyServiceFactory.getFilmServiceInstance().caricaSingoloElemento(Long.parseLong(idParameter)));
			request.setAttribute("registi_list_attribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
			request.getRequestDispatcher("film/search.jsp").forward(request, response);
			return;
		}
		
	    request.getRequestDispatcher("film/edit.jsp").forward(request, response);

	}

}
