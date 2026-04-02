import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.DecimalFormat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int p;
        for (p = 10; p < 100; p = p + 10) {
            int packets_lost = 0; // Packets that never reached the server
            int packets_sent = 0; // Packets created and sent to the server, including retransmissions.
            int total_timeslots = 50000; //Total duration of the simulation
            Random rand = new Random();
            Integer PacketID = 1;

            // Creating the stations
            Computer c1 = new Computer("c1", "01", 1);
            Computer c2 = new Computer("c2", "02", 1);
            Computer c3 = new Computer("c3", "03", 2);
            Computer c4 = new Computer("c4", "04", 2);
            Computer c5 = new Computer("c5", "05", 3);
            Computer c6 = new Computer("c6", "06", 3);
            Computer c7 = new Computer("c7", "07", 4);
            Computer c8 = new Computer("c8", "08", 4);

            List<Computer> computers = new ArrayList<>();
            computers.add(c1);
            computers.add(c2);
            computers.add(c3);
            computers.add(c4);
            computers.add(c5);
            computers.add(c6);
            computers.add(c7);
            computers.add(c8);

            // Creating the server.
            Server s = new Server();
            WDM multiplexer = new WDM();
            for (int i = 0; i < total_timeslots; i++) {
                ArrayList<Packet> toBeSent = new ArrayList<>();

                // Sending a packet to the Server s.
                for (Computer c : computers) {
                    if (c.getPacketsInBuffer() != null && rand.nextInt(1, 101) > 50) {
                        toBeSent.add(c.getPacket());
                        c.removeFromBuffer();
                        packets_sent++;
                    }
                }

                //Creating a packet for Computer c.
                for (Computer c : computers) {
                    if (rand.nextInt(1, 101) > p) {
                        Packet packet = new Packet(PacketID, i, c.getWavelength(), c);
                        if (!c.addToBuffer(packet)) {
                            //System.out.println("Packet lost");
                            packets_lost++;
                        }
                        PacketID++;
                    }
                }
                multiplexer.receive(toBeSent);
                for (Packet packet : multiplexer.combine()) {
                    s.packetReceived(packet, i);
                }
            }
            int TotalDelay = s.getDelay();
            double average_delay_per_packet = s.getDelay() * 1.0 / s.getTotalPacketsReceived();
            double packetloss_rate = packets_lost * 1.0 / packets_sent;
            double average_slot_thoughput = s.getTotalPacketsReceived() * 1.0 / total_timeslots;
            DecimalFormat numberFormat = new DecimalFormat("0.0");
            System.out.println("For p = " + p);
            System.out.println("Sent: " + packets_sent + " || Lost: " + packets_lost + " || Received: " + s.getTotalPacketsReceived());
            System.out.println("Total delay: " + TotalDelay + " Slots || Average delay: " + numberFormat.format(average_delay_per_packet)
                    + " Slots\nPacket loss rate: " + numberFormat.format(packetloss_rate * 100)
                    + "% || Average slot throughput: " + numberFormat.format(average_slot_thoughput) + " Packets per slot");
        }
    }
}