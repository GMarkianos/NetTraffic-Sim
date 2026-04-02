import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WDM{
    private final Map<Integer, Packet> received;

    public WDM(){
        received = new HashMap<>();
    }

    public void receive(ArrayList<Packet> packets){
        Iterator<Packet> iterator = packets.iterator();
        while (iterator.hasNext()) {
            Packet p = iterator.next();
            Integer key = p.getWavelength();
            if(received.containsKey(key)){
                iterator.remove();
                p.getComputer().addToBuffer(p);
                received.remove(key);
            }else{
                received.put(key, p);
            }
        }
    }

    public ArrayList<Packet> combine(){
        ArrayList<Packet> toServer = new ArrayList<>();
        for(Integer key : received.keySet()){
            Packet p = received.get(key);
            toServer.add(p);
        }
        received.clear();
        return toServer;
    }
}
