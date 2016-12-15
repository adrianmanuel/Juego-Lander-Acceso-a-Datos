/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modeloBD.Vinpartidas;
import modeloBD.Vinusuarios;
import services.exceptions.NonexistentEntityException;

/**
 *
 * @author OPEN
 */
public class VinpartidasJpaController implements Serializable {

    public VinpartidasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vinpartidas vinpartidas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vinusuarios idUsuario = vinpartidas.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                vinpartidas.setIdUsuario(idUsuario);
            }
            em.persist(vinpartidas);
            if (idUsuario != null) {
                idUsuario.getVinpartidasList().add(vinpartidas);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vinpartidas vinpartidas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vinpartidas persistentVinpartidas = em.find(Vinpartidas.class, vinpartidas.getIdPartida());
            Vinusuarios idUsuarioOld = persistentVinpartidas.getIdUsuario();
            Vinusuarios idUsuarioNew = vinpartidas.getIdUsuario();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                vinpartidas.setIdUsuario(idUsuarioNew);
            }
            vinpartidas = em.merge(vinpartidas);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getVinpartidasList().remove(vinpartidas);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getVinpartidasList().add(vinpartidas);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vinpartidas.getIdPartida();
                if (findVinpartidas(id) == null) {
                    throw new NonexistentEntityException("The vinpartidas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vinpartidas vinpartidas;
            try {
                vinpartidas = em.getReference(Vinpartidas.class, id);
                vinpartidas.getIdPartida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vinpartidas with id " + id + " no longer exists.", enfe);
            }
            Vinusuarios idUsuario = vinpartidas.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getVinpartidasList().remove(vinpartidas);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(vinpartidas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vinpartidas> findVinpartidasEntities() {
        return findVinpartidasEntities(true, -1, -1);
    }

    public List<Vinpartidas> findVinpartidasEntities(int maxResults, int firstResult) {
        return findVinpartidasEntities(false, maxResults, firstResult);
    }

    private List<Vinpartidas> findVinpartidasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vinpartidas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Vinpartidas findVinpartidas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vinpartidas.class, id);
        } finally {
            em.close();
        }
    }

    public int getVinpartidasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vinpartidas> rt = cq.from(Vinpartidas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
