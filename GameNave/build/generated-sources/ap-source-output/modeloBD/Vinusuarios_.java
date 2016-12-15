package modeloBD;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modeloBD.Vinpartidas;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-15T14:14:46")
@StaticMetamodel(Vinusuarios.class)
public class Vinusuarios_ { 

    public static volatile SingularAttribute<Vinusuarios, String> mail;
    public static volatile SingularAttribute<Vinusuarios, Integer> idUsuario;
    public static volatile SingularAttribute<Vinusuarios, Integer> telefono;
    public static volatile SingularAttribute<Vinusuarios, String> nombre;
    public static volatile ListAttribute<Vinusuarios, Vinpartidas> vinpartidasList;
    public static volatile SingularAttribute<Vinusuarios, String> contraseña;

}