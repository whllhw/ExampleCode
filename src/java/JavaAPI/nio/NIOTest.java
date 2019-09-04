package JavaAPI.nio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

public class NIOTest {
    public static long checksumInputStream(String filename) throws IOException {
        InputStream in = new FileInputStream(filename);
        CRC32 crc = new CRC32();
        int c;
        while ((c = in.read()) != -1) {
            crc.update(c);
        }
        return crc.getValue();
    }

    public static long checksumBufferedInputStream(String filename) throws Exception {
        InputStream in = new BufferedInputStream(new FileInputStream(filename));
        CRC32 crc = new CRC32();
        int c;
        while ((c = in.read()) != -1) {
            crc.update(c);
        }
        return crc.getValue();
    }

    public static long checksumRandomAccessFile(String filename) throws Exception {
        RandomAccessFile file = new RandomAccessFile(filename, "r");
        long length = file.length();
        CRC32 crc = new CRC32();
        for (long i = 0; i < length; i++) {
            file.seek(i);
            int c = file.readByte();
            crc.update(c);
        }
        return crc.getValue();
    }

    public static long checksumMappedFile(String filename) throws Exception {
        FileInputStream in = new FileInputStream(filename);
        FileChannel channel = in.getChannel();
        CRC32 crc = new CRC32();
        int lenght = (int) channel.size();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, lenght);
        for (int i = 0; i < lenght; i++) {
            int c = buffer.get(i);
            crc.update(c);
        }
        return crc.getValue();
    }

    public static void main(String[] args) throws Exception {
        String filename = "/home/jax/下载/XMind-ZEN-for-Linux-64bit.rpm";
        System.out.println(checksumInputStream(filename));
        System.out.println(checksumBufferedInputStream(filename));
        System.out.println(checksumRandomAccessFile(filename));
        System.out.println(checksumMappedFile(filename));
    }
}
