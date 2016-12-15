/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modeloBD.Vinpartidas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modeloBD.Vinusuarios;
import services.exceptions.IllegalOrphanException;
import services.exceptions.NonexistentEntityException;

/**
 *
 * @author OPEN
 */
public class VinusuariosJpaController implements Serializable {

    public VinusuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vinusuarios vinusuarios) {
        if (vinusuarios.getVinpartidasList() == null) {
            vinusuarios.setVinpartidasList(new ArrayList<Vinpartidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vinpartidas> attachedVinpartidasList = new ArrayList<Vinpartidas>();
            for (Vinpartidas vinpartidasListVinpartidasToAttach : vinusuarios.getVinpartidasList()) {
                vinpartidasListVinpartidasToAttach = em.getReference(vinpartidasListVinpartidasToAttach.getClass(), vinpartidasListVinpartidasToAttach.getIdPartida());
                attachedVinpartidasList.add(vinpartidasListVinpartidasToAttach);
            }
            vinusuarios.setVinpartidasList(attachedVinpartidasList);
            em.persist(vinusuarios);
            for (Vinpartidas vinpartidasListVinpartidas : vinusuarios.getVinpartidasList()) {
                Vinusuarios oldIdUsuarioOfVinpartidasListVinpartidas = vinpartidasListVinpartidas.getIdUsuario();
                vinpartidasListVinpartidas.setIdUsuario(vinusuarios);
                vinpartidasListVinpartidas = em.merge(vinpartidasListVinpartidas);
                if (oldIdUsuarioOfVinpartidasListVinpartidas != null) {
                    oldIdUsuarioOfVinpartidasListVinpartidas.getVinpartidasList().remove(vinpartidasListVinpartidas);
                    oldIdUsuarioOfVinpartidasListVinpartidas = em.merge(oldIdUsuarioOfVinpartidasListVinpartidas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vinusuarios vinusuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vinusuarios persistentVinusuarios = em.find(Vinusuarios.class, vinusuarios.getIdUsuario());
            List<Vinpartidas> vinpartidasListOld = persistentVinusuarios.getVinpartidasList();
            List<Vinpartidas> vinpartidasListNew = vinusuarios.getVinpartidasList();
            List<String> illegalOrphanMessages = null;
            for (Vinpartidas vinpartidasListOldVinpartidas : vinpartidasListOld) {
                if (!vinpartidasListNew.contains(vinpartidasListOldVinpartidas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vinpartidas " + vinpartidasListOldVinpartidas + " since its idUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Vinpartidas> attachedVinpartidasListNew = new ArrayList<Vinpartidas>();
            for (Vinpartidas vinpartidasListNewVinpartidasToAttach : vinpartidasListNew) {
                vinpartidasListNewVinpartidasToAttach = em.getReference(vinpartidasListNewVinpartidasToAttach.getClass(), vinpartidasListNewVinpartidasToAttach.getIdPartida());
                attachedVinpartidasListNew.add(vinpartidasListNewVinpartidasToAttach);
            }
            vinpartidasListNew = attachedVinpartidasListNew;
            vinusuarios.setVinpartidasList(vinpartidasListNew);
            vinusuarios = em.merge(vinusuarios);
            for (Vinpartidas vinpartidasListNewVinpartidas : vinpartidasListNew) {
                if (!vinpartidasListOld.contains(vinpartidasListNewVinpartidas)) {
                    Vinusuarios oldIdUsuarioOfVinpartidasListNewVinpartidas = vinpartidasListNewVinpartidas.getIdUsuario();
                    vinpartidasListNewVinpartidas.setIdUsuario(vinusuarios);
                    vinpartidasListNewVinpartidas = em.merge(vinpartidasListNewVinpartidas);
                    if (oldIdUsuarioOfVinpartidasListNewVinpartidas != null && !oldIdUsuarioOfVinpartidasListNewVinpartidas.equals(vinusuarios)) {
                        oldIdUsuarioOfVinpartidasListNewVinpartidas.getVinpartidasList().remove(vinpartidasListNewVinpartidas);
                        oldIdUsuarioOfVinpartidasListNewVinpartidas = em.merge(oldIdUsuarioOfVinpartidasListNewVinpartidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vinusuarios.getIdUsuario();
                if (findVinusuarios(id) == null) {
                    throw new NonexistentEntityException("The vinusuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vinusuarios vinusuarios;
            try {
                vinusuarios = em.getReference(Vinusuarios.class, id);
                vinusuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vinusuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Vinpartidas> vinpartidasListOrphanCheck = vinusuarios.getVinpartidasList();
            for (Vinpartidas vinpartidasListOrphanCheckVinpartidas : vinpartidasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vinusuarios (" + vinusuarios + ") cannot be destroyed since the Vinpartidas " + vinpartidasListOrphanCheckVinpartidas + " in its vinpartidasList field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vinusuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vinusuarios> findVinusuariosEntities() {
        return findVinusuariosEntities(true, -1, -1);
    }

    public List<Vinusuarios> findVinusuariosEntities(int maxResults, int firstResult) {
        return findVinusuariosEntities(false, maxResults, firstResult);
    }

    private List<Vinusuarios> findVinusuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vinusuarios.class));
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

    public Vinusuarios findVinusuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vinusuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getVinusuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vinusuarios> rt = cq.from(Vinusuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
