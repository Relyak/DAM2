package Repaso.calculadoraUdp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SocketUDP {
    private DatagramSocket socket;
    private String ip;
    private int port;
    private int MAX_LENGTH = 63535;

    public SocketUDP(int port, String ip) throws SocketException {
        try {
            this.socket = new DatagramSocket(port);
            this.ip = ip;
            this.port = port;
        } catch (SocketException e) {
            System.out.println("PUERTO USADO, ASIGNANDO OTRO");
            this.socket = new DatagramSocket();
            this.ip = ip;
            this.port = port;
        }
    }

    public void enviarMensaje(String message) throws IOException {
        byte[] buffer = message.getBytes();
        DatagramPacket p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ip), port);
        this.socket.send(p);
    }

    public String recibirMensaje() throws IOException {
        byte[] buffer = new byte[MAX_LENGTH];
        DatagramPacket p = new DatagramPacket(buffer, MAX_LENGTH);
        this.socket.receive(p);
        return new String(p.getData(), 0, p.getLength());
    }

    public void cerrar() {
        this.socket.close();
    }
}