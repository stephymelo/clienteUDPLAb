package com.example.clienteudplab;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConexion extends Thread{

    private DatagramSocket socket;

    @Override
    public void run() {
        try {

            socket = new DatagramSocket(5000);


            while(true) {
                byte[] buffer = new byte[100];
                DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
                System.out.println("Esperando datagrama");
                socket.receive(packet);
                String message = new String(packet.getData()).trim();
                System.out.println("Datagrama recibido"+message);


            }




        }catch(SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void sendMessage(String message){

        new Thread(
                ()->{
                    try {
                        InetAddress ip = InetAddress.getByName("192.168.0.200");
                        DatagramPacket packet = new DatagramPacket(message.getBytes(),message.getBytes().length,ip,5000);
                        socket.send(packet);

                    }catch (UnknownHostException e){
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



        ).start();


    }



}
