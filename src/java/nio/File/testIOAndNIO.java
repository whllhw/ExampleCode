package nio.File;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class testIOAndNIO {

    public static void io() {
        String filename = "";
        try (InputStream in =
                     new BufferedInputStream(
                             new FileInputStream(filename)
                     )) {
            byte[] buf = new byte[1024];
            int bytesRead = in.read();
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.println((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nio() {
        String filename = "";
        try (RandomAccessFile afile = new RandomAccessFile(filename, "rw")) {
            FileChannel fileChannel = afile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.println((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 12345));
            if (socketChannel.finishConnect()) {
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    String info = i + "times from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                    i++;
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void server() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while (true) {
                Socket clntSocket = serverSocket.accept();
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                System.out.println("hello!" + clientAddress);
                InputStream in = clntSocket.getInputStream();
                while ((recvMsgSize = in.read(recvBuf)) != -1) {
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                    System.out.println(new String(temp));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        int byteRead = sc.read(buffer);
        while (byteRead > 0) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            System.out.println();
            buffer.clear();
            byteRead = sc.read(buffer);
        }
        if (byteRead == -1)
            sc.close();
    }

    public static void selector() {
        try (Selector selector = Selector.open(); ServerSocketChannel ssc = ServerSocketChannel.open()) {
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 12345));
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                if (selector.select(3000) == 0) {
                    System.out.println("==");
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                        SocketChannel sc = ssChannel.accept();
                        sc.configureBlocking(false);
                        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
                    }
                    if (key.isReadable()) {
                        read(key);
                    }
                    if (key.isWritable() && key.isValid()) {
                        write(key);
                    }
                    if (key.isConnectable()) {
                        System.out.println("connected !");
                    }
                    iter.remove();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write(SelectionKey key) throws IOException {
        ByteBuffer buf = (ByteBuffer) key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        buf.compact();
    }

    public static void main(String[] args) {
//        new Thread(() -> {
//            selector();
//        }).start();
//
//        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            client();
//        }).start();
        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(10);
        byte[] b1 = {'0', '1'};
        byte[] b2 = {'2', '3'};
        header.put(b1);
        body.put(b2);
        ByteBuffer[] buffs = {header, body};
        try {
            FileOutputStream os = new FileOutputStream("src/scattingAndGather.txt");
            FileChannel channel = os.getChannel();
            channel.write(buffs);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
