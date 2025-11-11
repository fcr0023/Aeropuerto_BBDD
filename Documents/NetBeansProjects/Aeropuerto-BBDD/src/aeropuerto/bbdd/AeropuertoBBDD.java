/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aeropuerto.bbdd;

    import java.sql.DriverManager;
    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.logging.Level;
    import java.util.logging.Logger;

/**
 *
 * @author MEDAC
 */
public class AeropuertoBBDD {

    /**
     * @param args the command line arguments
     */
            /*
    Definimos e inicializamos constantes:
    DRIVER. Nombre del driver que vamos a utilizar. En este caso de MYSQL
    URL_CONEXION. equipo:puerto/basededatos
    
        */
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String URL_CONEXION = "jdbc:mysql://localhost:3306/mydb";
        public static void main(String args[]) throws SQLException {

            final String usuario = "root";
            final String password = "1234";
            Connection dbConnection = null;
            Statement statement = null;
            Statement statement2 = null;
            try {
                //Registramos el driver de mysql
                Class.forName(DRIVER);
                //Clase DriverManager gestiona todos los drivers que tenemos en nuestra aplicación
                //Devuelve un objeto conexión que utilizaremos para conectarnos a la base de datos
                Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
                //Guardamos en una variable la consulta
                String joinTableSQL ="SELECT id_aerolinea, nombre, pais_origen FROM aerolinea";  
                // ---- CONSULTA CON WHERE 
                //String where = " WHERE NOMBRE = 'Luis'";
                //String selectTableSQL = "SELECT ID,USERNAME,PASSWORD,NOMBRE FROM usuarios" + where;
                
                //Ejecutamos el método createStatement y creamos un objeto statement
                //que nos va a permitir ejecutar consultas
                statement = conn.createStatement();
                //executeQuery(String). Ejecutamos la consulta y nos devuelve un Resultset (cursor)
                ResultSet rs = statement.executeQuery(joinTableSQL);
                
                //Recorremos el resultset y mostramos la información de las columnas
                while (rs.next()){
                int id = rs.getInt("id_aerolinea");
                String nombre = rs.getString("nombre");
                String pais = rs.getString("pais_origen");

                System.out.println("ID: " + id + 
                                   " | Nombre: " + nombre + 
                                   " | País de origen: " + pais);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            }
    }   
}

