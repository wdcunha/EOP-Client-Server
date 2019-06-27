import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate<date> {

    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date date = new Date(System.currentTimeMillis());
//    System.out.println(formatter.format(date));
}
