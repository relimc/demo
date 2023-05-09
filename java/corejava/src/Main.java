import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        File file = new File("a.txt");
        try(
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                FileChannel randomChannel = raf.getChannel()
        ) {
            ByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            randomChannel.position(file.length());
            randomChannel.write(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}






