package fuzzy;

import java.io.IOException;
import java.net.*;

public class servidorFuzzy {
	
	public static void main(String[] args) {
		
		//constantes
		final int serverPort = 9900;
		final float menosMil = -1000.9f;
		
		// Create a buffer where to put a received datagram
		byte[] buf ;
		String recibido1;

		float distObj;
		float angObj;
		float angDest;
		float angDrone = 1.1f;		
		
		DatagramPacket packetDistObj;
		DatagramPacket packetAngObj; 
		DatagramPacket packetAngDest;		
		DatagramPacket packetAng;
		
		DatagramSocket udpSocket;
		
		InetAddress clientAddress;
		int clientPort;
		
		try {
			// Create a udp socket for sending and receiving datagrams in serverPort
			udpSocket = new DatagramSocket(serverPort);
			
			System.out.println("Server: waiting for new datagrams...");
			buf = new byte[300];
			packetDistObj = new DatagramPacket(buf, buf.length);
			packetAngObj = new DatagramPacket(buf, buf.length);
			packetAngDest = new DatagramPacket(buf, buf.length);			
			
			while (true) {								
				// Receive the distObj from client
				udpSocket.receive(packetDistObj);
				recibido1=new String(packetDistObj.getData());
				distObj = stringToFloat(recibido1);
								
				// Receive the angObj from client
				udpSocket.receive(packetAngObj);
				recibido1=new String(packetAngObj.getData());
				angObj = stringToFloat(recibido1);				
				
				// Receive the datagram from client
				udpSocket.receive(packetAngDest);
				recibido1=new String(packetAngDest.getData());				
				angDest = stringToFloat(recibido1);		
				
				// Get the address of client from packet
				clientAddress = packetDistObj.getAddress();
				clientPort = packetDistObj.getPort();	
				
				// Salgo del While si el mensaje que envio al servidor es null o stop.
				//SI SE HACEN GENERACIONES HACER UN WAIT PARA CUANDO SE PARE LA GENERACION
				if (distObj == menosMil ) { 
					System.out.println("Las comunicaciones han finalizado");
					break;}
				
				//Si no entra se envia el valor de la vez anterior
				if (angDest == -6789f || angObj == -6789f || distObj == -6789f ){
						angDrone = 00f; //angDrone = evaluar ( angDest, angObj, distObj);
					} 
						
				buf = (Float.toString(angDrone)).getBytes();
				
				// Send the datagram packet to client
				packetAng = new DatagramPacket(buf, buf.length, clientAddress,clientPort);
				udpSocket.send(packetAng);
				
				//puede no ser necesario
				buf = new byte[300];
				packetDistObj = new DatagramPacket(buf, buf.length);
				packetAngObj = new DatagramPacket(buf, buf.length);
				packetAngDest = new DatagramPacket(buf, buf.length);	

				System.out.println("       --------------------------        ");
			}
			udpSocket.close();
		}		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static float stringToFloat (final String convertir){
		float out = 0.0f;
		String convertido;
		int posicionDecimal;
		
		posicionDecimal = convertir.indexOf('.')+2;
		convertido= convertir.substring(0, posicionDecimal);
		
		try{
			out = Float.parseFloat(convertido);
		} catch (NumberFormatException e){
			return out=-6789f;
		}
		
		return out;
		
	}
	
}

//funci�n para que cuando se envia angDrone tenga un .1
