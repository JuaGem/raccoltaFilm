package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;


@WebServlet("/ExecuteModificaFilmServlet")
public class ExecuteModificaFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ExecuteModificaFilmServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		String idInputParam = request.getParameter("idEditInput");
		String titoloParam = request.getParameter("titolo");
		String genereParam = request.getParameter("genere");
		String dataPubblicazioneParam = request.getParameter("dataPubblicazione");
		String minutiDurataParam = request.getParameter("minutiDurata");
		String registaIdParam = request.getParameter("regista.id");

		Film filmInstance = UtilityForm.createFilmFromParams(titoloParam, genereParam, minutiDurataParam,
				dataPubblicazioneParam, registaIdParam);

		filmInstance.setId(Long.parseLong(idInputParam));

		try {

			if (!UtilityForm.validateFilmBean(filmInstance)) {
			
				request.setAttribute("edit_film_attribute", filmInstance);
			
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/film/edit.jsp").forward(request, response);
				return; 
			} 
			
			MyServiceFactory.getFilmServiceInstance().aggiorna(filmInstance);
 
			request.setAttribute("registi_list_attribute",
					MyServiceFactory.getRegistaServiceInstance().listAllElements());
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("film/edit.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");
	}

}
