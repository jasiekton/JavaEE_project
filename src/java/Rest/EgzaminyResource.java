package Rest;

import EJB.DBConnect;
import JPA.ExamQuery;
import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author student
 */
@Path("egzaminy")
public class EgzaminyResource {

    @EJB
    private DBConnect dbConnect;
    
    private final ExamQuery examQuery = new ExamQuery();
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response wyswietlWszystkie() {
        String wynik = examQuery.wyswietlWszystkie();
        return Response.ok(wynik).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response dodajRekord(MultivaluedMap<String, String> formParams) {
        String nazwa = formParams.getFirst("nazwa");
        String prowadzacy = formParams.getFirst("prowadzacy");
        String data = formParams.getFirst("data");
        String sala = formParams.getFirst("sala");
        examQuery.dodajRekord(nazwa, prowadzacy, data, sala);
    
        String wynik = examQuery.wyswietlWszystkie();
    
        return Response.status(Response.Status.CREATED).entity(wynik).build();
    }
    
    @POST
    @Path("/usun/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response usunRekord(@PathParam("id") Integer id, @FormParam("_method") String method) {
        if (method != null && method.equalsIgnoreCase("DELETE")) {
            examQuery.usunRekord(id);
            String wynik = examQuery.wyswietlWszystkie();
            return Response.ok(wynik).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Metoda żądania nie jest obsługiwana").build();
        }
    }
    
    @GET
    @Path("/pobierz/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response pobierzPlik(@PathParam("id") Integer id) {
        try {
            String sciezkaDoPliku = examQuery.zapiszDoPliku(id); 
            File plik = new File(sciezkaDoPliku);
            if (plik.exists()) {
                return Response.ok(plik)
                               .header("Content-Disposition", "attachment; filename=\"" + plik.getName() + "\"")
                               .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Nie znaleziono pliku").build();
            }
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Błąd podczas generowania pliku").build();
        }
    }
}
