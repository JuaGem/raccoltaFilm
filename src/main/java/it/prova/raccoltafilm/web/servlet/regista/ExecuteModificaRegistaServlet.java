package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteModificaRegistaServlet
 */
@WebServlet("/ExecuteModificaRegistaServlet")
public class ExecuteModificaRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ExecuteModificaRegistaServlet() {
        super();
    }

	 

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("userInfo") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		String idInputParam = request.getParameter("idEditInput");
 		String nicknameParam = request.getParameter("nickName");
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String dataDiNascitaParam = request.getParameter("dataDiNascita");
		String sessoParam = request.getParameter("sesso");


		Regista registaInstance = UtilityForm.createRegistaFromParams(nomeParam, cognomeParam, nicknameParam,
				dataDiNascitaParam, sessoParam);

		registaInstance.setId(Long.parseLong(idInputParam));

		try {

			if (!UtilityForm.validateRegistaBean(registaInstance)) {
			
				request.setAttribute("edit_regista_attribute", registaInstance);
			
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/regista/edit.jsp").forward(request, response);
				return; 
			} 
			
			MyServiceFactory.getRegistaServiceInstance().aggiorna(registaInstance);
			request.setAttribute("successMessage", "Operazione eseguita con successo!");
 
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("regista/edit.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("ExecuteListRegistaServlet?operationResult=SUCCESS");

	}
	 
}
