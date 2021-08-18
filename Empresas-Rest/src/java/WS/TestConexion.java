package WS;

import Genericos.ConexionBD;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("test")
public class TestConexion {

    
    
    public TestConexion() {
        
    }
    
    
    @GET
    public String Test(){
        ConexionBD.getConexionBD();
       
        return     ConexionBD.getMsg();
        
        
    
    }
    
    
}
