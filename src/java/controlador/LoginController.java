
package controlador;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import static sun.management.Agent.error;


/**
 *
 * @author SERGIO C ...
 */
@Named( value = "loginController")
@ViewScoped
public class LoginController implements Serializable{
    private String usuario;
    private String con;
    
   
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }
    
    public LoginController() {
        
    }
    
  
    public void ir() {
        if(usuario.equals("admin") && con.equals("12345")){
        try {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("Carreras.xhtml");
        } catch (Exception e) {
            error("Mierda");
        }
    }
    }
       
}
