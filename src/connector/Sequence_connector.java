package connector;

import all_interface.Invoke;

import java.util.ArrayList;

public class Sequence_connector implements Invoke {
    public void sequence_connector(){

    }
    @Override
    public Object invoke_function(Object in_object,ArrayList<Invoke> components) {
        Object temp = in_object;
        for(Invoke component:components){
            temp = component.invoke_function(temp, null);
        }
        return temp;
    }
}
