package WS;

import Dao.EmpresasDao;
import Dto.EmpresasDto;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("empresas")
public class EmpresasWS {
    private EmpresasDao dao;
    private EmpresasDto dto;
    private Gson gsonSerializador;
    private BufferedReader br;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("id") String id) { 
        dto = new EmpresasDto();
        dto.setId(Integer.parseInt(id)); 
        dao = new EmpresasDao();
        return new Gson().toJson(dao.consultarSegunId(dto));
    }   
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
   // @Produces(MediaType.APPLICATION_JSON) 
    public void crear(InputStream i) {
        try {
            String json = "";
            gsonSerializador=new Gson();
            br = new BufferedReader(new InputStreamReader(i));
            if (br.ready()) {
                json = br.readLine();
            }
            dto = gsonSerializador.fromJson(json, EmpresasDto.class);
            dao = new EmpresasDao();
            dao.insertar(dto);
            br.close();
        } catch (IOException ex) {
            //  Logger.getLogger(cursos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
   // @Produces(MediaType.APPLICATION_JSON)
    public void update(InputStream i) {
        String json = "";
        gsonSerializador=new Gson();
        try {
            br = new BufferedReader(new InputStreamReader(i));
            if (br.ready()) {
                json = br.readLine();
            }
            System.out.println("Cadena Leida Update " + json );
            dto = gsonSerializador.fromJson(json, EmpresasDto.class);
            dao = new EmpresasDao();
            dao.modificar(dto);
        } catch (IOException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
   // @Produces(MediaType.APPLICATION_JSON)
    public void eliminar(InputStream i) {
        String json = "";
        gsonSerializador=new Gson();
        try {
            br = new BufferedReader(new InputStreamReader(i));
            if (br.ready()) {
                json = br.readLine();
            }
            System.out.println("Cadena Leida delete " + json );
            dto = gsonSerializador.fromJson(json, EmpresasDto.class);
            dao = new EmpresasDao();
            dao.eliminar(dto);
        } catch (IOException ex) {
            //Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
