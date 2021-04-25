<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="ISO-8859-1">
	
	<jsp:include page="../header.jsp" />
	<link href="./assets/css/global.css" rel="stylesheet">

	<title>Modifica Film</title>

</head>

<body>

	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			  ${errorMessage}
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
			</div>
			
			<div class='card'>
			    <div class='card-header'>
			        <h5>Inserisci elemento da modificare</h5> 
			    </div>
			    <div class='card-body'>
	
					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
	
						<form method="post" action="ExecuteModificaFilmServlet" novalidate="novalidate">
						
							<div class="form-row">
								<div class="form-group col-md-6">
								
									<label>Titolo<span class="text-danger">*</span></label>
									<input type="text" name="titolo" id="titolo" class="form-control" 
									value="${edit_film_attribute.titolo}" placeholder="Inserire il titolo da modificare" required autofocus>
								
								</div>
								
								<div class="form-group col-md-6">
								
									<label>Genere<span class="text-danger">*</span></label>
									<input type="text" name="genere" id="genere" class="form-control" 
									value="${edit_film_attribute.genere}" placeholder="Inserire il genere da modificare" required autofocus>
								
								</div>
							</div>
							
							<div class="form-row">	
								<div class="form-group col-md-6">
								
									<label>Durata in minuti<span class="text-danger">*</span></label>
									<input type="number" class="form-control" name="minutiDurata" id="minutiDurata" 
									value="${edit_film_attribute.minutiDurata}" placeholder="Inserire la durata in minuti da modificare"  required autofocus>
									
								</div>
								<div class="form-group col-md-3">
									<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${edit_film_attribute.dataPubblicazione}" var="dataPubblParsed"/>
									<label>Data Pubblicazione<span class="text-danger">*</span></label>
		                     		<input class="form-control" id="dataPubblicazione" type="date" placeholder="dd/MM/yy" 
		                         	title="formato : gg/mm/aaaa" value="${dataPubblParsed}" name="dataPubblicazione" required>
		                         	
								</div>
								
							</div>
							
						<div class="form-row">	
							<div class="form-group col-md-6">
								<label for="regista.id">Regista</label>
							    <select class="form-control" id="regista.id" name="regista.id">
							    	<option value="" selected> -- Selezionare una voce -- </option>
							      	<c:forEach items="${registi_list_attribute}" var="registaItem">
							      		<option value="${registaItem.id}" ${edit_film_attribute.regista.id == registaItem.id?'selected':''} >${registaItem.nome } ${registaItem.cognome }</option>
							      	</c:forEach>
							    </select>
							</div>
						</div>
						
			      			<input type="hidden" name="idEditInput" value="${edit_film_attribute.id}">
						
							<a href="ExecuteListFilmServlet" class='btn btn-outline-secondary' style='width:7em;'>
			           			<i class='fa fa-chevron-left'></i> Indietro
			      			</a>
							<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary" style='width:7em;'>Modifica</button>
						
						</form>
			    
				<!-- end card-body -->			   
			    </div>
			</div>	
	
	
	<!-- end container -->	
	</main>
				
	<jsp:include page="../footer.jsp" />	
	
</body>

</html>