import java.util.ArrayList;

public class Computer {
    private final String computerName;
    private final String computerID;
    private final ArrayList<Packet> Buffer; //List of packets
    private final Integer wavelength; //Wavelength used

    //Initialization
    public Computer(String computerName, String computerID, Integer wavelength) {
        this.computerName = computerName;
        this.computerID = computerID;
        this.wavelength = wavelength;
        this.Buffer = new ArrayList<>();
    }
    //Returning the first packed of the buffer
    public Packet getPacket(){
        try{
            return  Buffer.getFirst();
        }catch(Exception e){
            return null;
        }
    }
    //Returning all packets in the buffer
    public ArrayList<Packet> getPacketsInBuffer(){
        if(Buffer.isEmpty()){
            return null;
        }else{
            return Buffer;
        }
    }
    //Checking if adding a packet to the buffer is possible and if it is, adds it.
    public boolean addToBuffer(Packet packet){
        if(Buffer.size()==5){
            //System.out.println("Buffer overflow");
            return false;
        }
        Buffer.add(packet);
        return true;
    }
    //Removes the first packet in the buffer
    public void removeFromBuffer(){
        Buffer.removeFirst();
    }
    //Returns the wavelength used
    public Integer getWavelength() {
        return wavelength;
    }
}
