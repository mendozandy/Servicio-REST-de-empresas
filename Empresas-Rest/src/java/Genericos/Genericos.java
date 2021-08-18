
package Genericos;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Genericos {
    
   public static DateFormat formatterTime=  new SimpleDateFormat("HH:mm");
   private static final SimpleDateFormat formatoddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
   private static final SimpleDateFormat formatoyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
 
    
    public static Time getDateTimeSql(String dateTime) throws java.text.ParseException {
        long ms = formatterTime.parse(dateTime).getTime();
        return new Time(ms);
    }

    public static String getFechaServidor() {
        ConexionBD.getConexionBD();
        ResultSet rs;
        PreparedStatement ps;
        try {
            ps = ConexionBD.getRutaConexion().prepareStatement("select to_char(current_date,'DD/MM/YYYY') as fecha");
            rs = ps.executeQuery();
            rs.next();
            return rs.getString("fecha");
        } catch (Throwable e) {
            return "fecha invalida";
        }
    }

   

    public static String retornarFechaddMMyyyy(Date fechaRecuperada) {
        return formatoddMMyyyy.format(fechaRecuperada);
    }

    //retornar Fecha como String
    public static String retornarFechayyyyMMdd(String fecha) {
       Date fechaLocal = null;
        try {
          
           fechaLocal =  (Date) formatoddMMyyyy.parse(fecha);
           return formatoyyyyMMdd.format(fechaLocal);
       } catch (java.text.ParseException ex) {
           Logger.getLogger(Genericos.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
    }

     //retornar Fecha como Date
    public static java.sql.Date retornarFecha(String fechaRecibida) {
       // SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        String strFecha = fechaRecibida;
        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(strFecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return fechaUtilTosql(fecha);
    }

    private static java.sql.Date fechaUtilTosql(java.util.Date utilDate) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
    
//    //retornar Fecha como Date
//    public static java.sql.Date retornarFecha(String fechaRecibida) {
//       // SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
//        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
//      //SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
//
//        //tring strFecha = fechaRecibida;
//        Date fecha = null;
//       try {
//           fecha = formatoDelTexto.parse(fechaRecibida);
//       } catch (java.text.ParseException ex) {
//           Logger.getLogger(Genericos.class.getName()).log(Level.SEVERE, null, ex);
//       }
//        return fechaUtilTosql(fecha);
//    }
//
//    private static java.sql.Date fechaUtilTosql(java.sql.Date utilDate) {
//        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//        return sqlDate;
//    }
    
}
