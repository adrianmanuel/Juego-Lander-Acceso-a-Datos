package modeloBD;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modeloBD.Vinusuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-15T14:14:45")
@StaticMetamodel(Vinpartidas.class)
public class Vinpartidas_ { 

    public static volatile SingularAttribute<Vinpartidas, Integer> idPartida;
    public static volatile SingularAttribute<Vinpartidas, Vinusuarios> idUsuario;
    public static volatile SingularAttribute<Vinpartidas, Date> inicio;
    public static volatile SingularAttribute<Vinpartidas, Date> fin;
    public static volatile SingularAttribute<Vinpartidas, Float> velocidad;

}