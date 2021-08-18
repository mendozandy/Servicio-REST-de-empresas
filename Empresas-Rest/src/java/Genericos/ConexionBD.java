package Genericos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static ConexionBD INSTANCE = null;
    private static Connection dbcon;
   
    private static String msg;
    
    //Conexion remota
    private static final String DEFAULT_HOST = "localhost:3306";
    private static final String DEFAULT_DB = "examen-final";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "oracle";
  
// Conexion Local
//    private final String DEFAULT_HOST = "localhost:3306";
//    private final String DEFAULT_DB = "calificaciones_uninorte";
//    private final String DEFAULT_USER = "root";
//    private final String DEFAULT_PASS = "1";
    

    public static enum TR {INICIAR, CONFIRMAR, CANCELAR};

    private ConexionBD() {
        String unicode="?useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
       // String sCon = "jdbc:mysql://"+ DEFAULT_HOST +"/"+ DEFAULT_DB ;
        String sCon = "jdbc:mysql://"+ DEFAULT_HOST +"/"+ DEFAULT_DB + unicode;
        //String sCon = "jdbc:mysql://"+ DEFAULT_HOST +"/"+ DEFAULT_DB +"?useSSL=false&autoReconnect=true";
        try{
            System.out.println("url con " +sCon );
           Class.forName("com.mysql.cj.jdbc.Driver");           
           //Class.forName("com.mysql.jdbc.Driver");           
           dbcon = DriverManager.getConnection(sCon, DEFAULT_USER, DEFAULT_PASS);
           if (dbcon != null) {
               msg = "Se conectó correctamente a la base de datos.";
               System.out.println(msg);
           }else{
               msg = "Error al conectar a la base de datos.";
               System.out.println(msg);
           }
        } catch(ClassNotFoundException | SQLException e){
            msg = "Error driver! "+ e.getMessage();    
        }
    }
    
    
    public static Connection getRutaConexion() {
        String unicode="?useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
        String sCon = "jdbc:mysql://"+ DEFAULT_HOST +"/"+ DEFAULT_DB + unicode;
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");           
        //Class.forName("com.mysql.jdbc.Driver");           
         return DriverManager.getConnection(sCon, DEFAULT_USER, DEFAULT_PASS);
        } catch(ClassNotFoundException | SQLException e){
            msg = "Error driver! "+ e.getMessage();    
        }
        return null;
    }
    
    public static ConexionBD getConexionBD() {
        if (INSTANCE == null) {
            INSTANCE = new ConexionBD();
        }
        return INSTANCE;
    }

    public static String getMsg() {
        return msg;
    }
    
    public static void cerrar(){
        try{
            dbcon.close();
        }catch(SQLException e){
            msg = "Error al cerrar! "+ e.getMessage();
        }
    }
    
    public static void Transaccion(TR accion){
        try {
            switch(accion){
                case INICIAR:
                    dbcon.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                    dbcon.setAutoCommit(false); 
                    break;
                case CONFIRMAR:     
                    dbcon.commit();
                    dbcon.setAutoCommit(true); 
                    break;
                case CANCELAR:
                    dbcon.rollback();
                    dbcon.setAutoCommit(true); 
                    break;
            }
        } catch (SQLException ex) {
            msg = "Error al establecer estado de transacciones.";
        }
    }
    
    
}
