package it.prova.raccoltafilm.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.raccoltafilm.dao.RuoloDAO;
import it.prova.raccoltafilm.dao.UtenteDAO;
import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.web.listener.LocalEntityManagerFactoryListener;

public class RuoloServiceImpl implements RuoloService {

	private RuoloDAO ruoloDAO;
	private UtenteDAO utenteDAO;

	@Override
	public List<Ruolo> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ruolo caricaSingoloElemento(Long id) throws Exception {
	
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
	
			ruoloDAO.setEntityManager(entityManager);

			return ruoloDAO.findOne(id).get();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			// uso l'injection per il dao
			ruoloDAO.setEntityManager(entityManager);
			ruoloDAO.setEntityManager(entityManager);
			ruoloDAO.update(ruoloInstance);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			entityManager.close();
		}

	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
		
			entityManager.getTransaction().begin();


			ruoloDAO.setEntityManager(entityManager);


			ruoloDAO.insert(ruoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Ruolo ruoloInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			
			ruoloDAO.setEntityManager(entityManager);
			
			ruoloInstance = entityManager.merge(ruoloInstance);
			
			if (!utenteDAO.findAllByRuolo(ruoloInstance).isEmpty()) 
				throw new Exception ("Ruolo non eliminabile. Utenti ancora associati");
 
			ruoloDAO.delete(ruoloInstance);	
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void setRuoloDAO(RuoloDAO ruoloDAO) {
		this.ruoloDAO = ruoloDAO;
	}

	@Override
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			ruoloDAO.setEntityManager(entityManager);

			return ruoloDAO.findByDescrizioneAndCodice(descrizione, codice);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
