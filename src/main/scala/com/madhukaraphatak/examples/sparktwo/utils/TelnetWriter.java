package com.madhukaraphatak.examples.sparktwo.utils;

import java.io.*;
import java.net.Socket;

class TelnetWriter extends Thread {

    public static void main(String[] args) throws IOException {
        connect("127.0.0.1", 50060);
        //即可在控制台输入命令，回车后会发送命令到远程主机，并在控制台打印响应数据
    }


    //远程连接
    public static void connect(String host, int port) {
        try {
            Socket sock = new Socket(host, port);
            // 创建一个写线程
            new TelnetWriter(sock.getOutputStream()).start();
            // 创建一个读线程
//            new TelnetReader(sock.getInputStream()).start();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    private PrintStream out;

    public TelnetWriter(OutputStream out) {
        this.out = new PrintStream(out);
    }

    @Override
    public void run() {
        try {
            // 包装控制台输入流
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new FileReader("data.txt"));
            // 反复将控制台输入写到Telnet服务程序
            while (true)
                out.println(in.readLine());
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
//
//class TelnetReader extends Thread {
//    private InputStreamReader in;
//
//    public TelnetReader(InputStream in) {
//        this.in = new InputStreamReader(in);
//    }
//
//    public void run() {
//        try {
//            // 反复将Telnet服务程序的反馈信息显示在控制台屏幕上
//            while (true) {
//                // 从Telnet服务程序读取数据
//                int b = in.read();
//                if (b == -1)
//                    System.exit(0);
//                // 将数据显示在控制台屏幕上
//                System.out.print((char) b);
//            }
//        } catch (IOException exc) {
//            exc.printStackTrace();
//        }
//    }
//}