/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author student
 */
public class ExamQuery {
    
    private EntityManagerFactory emf;

    public ExamQuery() {
        this.emf = Persistence.createEntityManagerFactory("WCY21IG1S1_Jasiek_NowickiPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Wyświetlanie wszystkich danych w postaci tabeli
    public String wyswietlWszystkie() {
        EntityManager em = getEntityManager();
        try {
            List<Wcy21ig1s1JasiekNowicki> wyniki = em.createNamedQuery("Wcy21ig1s1JasiekNowicki.findAll", Wcy21ig1s1JasiekNowicki.class).getResultList();
            StringBuilder sb = new StringBuilder("<!DOCTYPE html><html><head><title>Wyniki</title>");
            sb.append("<meta charset=\"UTF-8\">"); 
            sb.append("<link rel=\"stylesheet\" href=\"/WCY21IG1S1_Jasiek_Nowicki/css/table.css\" type=\"text/css\">"); 
            sb.append("</head><body>");
            sb.append("<table><tr><th>Nazwa</th><th>Prowadzący</th><th>Data</th><th>Sala</th><th>Akcje</th></tr>");
            for (Wcy21ig1s1JasiekNowicki w : wyniki) {
                sb.append("<tr><td>")
                    .append(w.getNazwa()).append("</td><td>")
                    .append(w.getProwadzący()).append("</td><td>")
                    .append(w.getData()).append("</td><td>")
                    .append(w.getSala()).append("</td><td>")
                    .append("<button><a href='egzaminy/pobierz/").append(w.getId()).append("'>Pobierz</a></button> ")
                    .append("<form method='POST' action='egzaminy/usun/").append(w.getId()).append("'>")
                    .append("<input type='hidden' name='_method' value='DELETE'>")
                    .append("<button type='submit'>Usuń</button>")
                    .append("</form>")
                    .append("</td></tr>");
            }
            sb.append("</table>");
            sb.append("<div class='powrot'><a href='/WCY21IG1S1_Jasiek_Nowicki' class='button'>Powrót na stronę główną</a></div>");
            sb.append("</body></html>");
            return sb.toString();
        } finally {
            em.close();
        }
    }

    // Dodawanie rekordu do bazy
    public void dodajRekord(String nazwa, String prowadzący, String data, String sala) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Wcy21ig1s1JasiekNowicki nowy = new Wcy21ig1s1JasiekNowicki();
            nowy.setNazwa(nazwa);
            nowy.setProwadzący(prowadzący);
            nowy.setData(data);
            nowy.setSala(sala);
            em.persist(nowy);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Usuwanie rekordu z bazy
    public void usunRekord(Integer id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Wcy21ig1s1JasiekNowicki doUsuniecia = em.find(Wcy21ig1s1JasiekNowicki.class, id);
            if (doUsuniecia != null) {
                em.remove(doUsuniecia);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Zapisywanie do pliku txt danych z rekordu o wybranym id
    public String zapiszDoPliku(Integer id) throws IOException {
        EntityManager em = getEntityManager();
        String pelnaSciezka = null;
        try {
            Wcy21ig1s1JasiekNowicki rekord = em.find(Wcy21ig1s1JasiekNowicki.class, id);
            if (rekord != null) {
                String sciezkaPulpitu = System.getProperty("user.home") + "/Desktop/";
                try (FileWriter writer = new FileWriter(sciezkaPulpitu + rekord.getNazwa() + ".txt")) {
                    writer.write("Nazwa: " + rekord.getNazwa() + "\n");
                    writer.write("Prowadzący: " + rekord.getProwadzący() + "\n");
                    writer.write("Data: " + rekord.getData() + "\n");
                    writer.write("Sala: " + rekord.getSala());
                }
            }
        } finally {
            em.close();
        }
        return pelnaSciezka;
    }
}
