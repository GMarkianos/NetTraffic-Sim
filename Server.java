public class Server {
    private Integer totalPacketsReceived;
    private Integer totalDelay;

    //Initialization
    public Server(){
        totalPacketsReceived = 0;
        totalDelay = 0;
    }
    //Receiving a packet and adding the delay in Slots
    public void packetReceived(Packet packet, Integer cur_slot){
        totalDelay = totalDelay+ 1 + cur_slot - packet.getTimestamp();
        totalPacketsReceived++;
    }
    //Returning the number of packets received
    public Integer getTotalPacketsReceived() {
        return totalPacketsReceived;
    }
    //Returning the total delay of all packets combined
    public Integer getDelay() {
        return totalDelay;
    }
}
