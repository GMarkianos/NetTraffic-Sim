public class Packet{
    private final Integer timestamp;
    private final Integer id;
    private final Computer computer;
    private final Integer wavelength;

    //Initialization
    public Packet(Integer id, Integer timestamp, Integer wavelength,Computer computer){
        this.id = id;
        this.computer = computer;
        this.wavelength = wavelength;
        this.timestamp = timestamp;
    }
    //Returns the time that this packet was created
    public Integer getTimestamp(){
        return timestamp;
    }
    //Returns the wavelength that the computer of this packed uses
    public Integer getWavelength(){
        return wavelength;
    }
    //Returns the computer that sent this packet
    public Computer getComputer() {
        return computer;
    }
}
