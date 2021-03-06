package all_interface;

import java.util.ArrayList;

public interface Invoke {
    Object invoke_function(Object object, ArrayList<Invoke> components);
}
