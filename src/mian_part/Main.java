package mian_part;

import LogicRepository.Beauty;
import all_interface.Invoke;
import connector.Sequence_connector;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Beauty beauty = new Beauty();
        Display disp = new Display();
        ArrayList<Invoke> components = new ArrayList<>();
        components.add(beauty);
        components.add(disp);
        Sequence_connector sequence_connector = new Sequence_connector();
        sequence_connector.invoke_function(null,components);
    }
}
