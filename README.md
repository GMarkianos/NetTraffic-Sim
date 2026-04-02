# WDM Network Simulation

A Java simulation of an optical network with 8 computers transmitting packets to a central server using 4 shared wavelengths.

## Components:
- Computer: Network stations with 5-packet buffers
- Packet: Data packets with timestamps and wavelength info
- WDM: Multiplexer handling collision detection
- Server: Receives packets and calculates delays
- Main: Runs 50,000-slot simulations with varying load

## How to Run:
javac *.java
java Main

## Network Setup:
- 8 computers (c1-c8) sharing 4 wavelengths (1-4)
- Pairs: (c1,c2)→λ1, (c3,c4)→λ2, (c5,c6)→λ3, (c7,c8)→λ4
- Collision = both packets requeued when same wavelength used

## Output:
Packet loss rate, average delay, and throughput for different traffic loads (p=10-90%).
