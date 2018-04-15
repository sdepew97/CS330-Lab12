import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Lab12 {
    public static void main(String[] args) {
//        String msg = "It was the best of times, it was the worst of times.\n";
//        String hashValue = hashString(msg, "SHA-1");

        String hashValue = hashAFile(new File("TwoCities.txt"), "MD5");
        System.out.println(hashValue + "  " + "TwoCities.txt");
        hashValue = hashAFile(new File("Anassa.txt"), "MD5");
        System.out.println(hashValue + "  " + "Anassa.txt");
        hashValue = hashAFile(new File("Moby10.txt"), "MD5");
        System.out.println(hashValue + "  " + "Moby10.txt");
        hashValue = hashAFile(new File("spongeBob.jpg"), "MD5");
        System.out.println(hashValue + "  " + "spongeBob.jpg");

        hashValue = hashAFile(new File("TwoCities.txt"), "SHA-1");
        System.out.println(hashValue + "  " + "TwoCities.txt");
        hashValue = hashAFile(new File("Anassa.txt"), "SHA-1");
        System.out.println(hashValue + "  " + "Anassa.txt");
        hashValue = hashAFile(new File("Moby10.txt"), "SHA-1");
        System.out.println(hashValue + "  " + "Moby10.txt");
        hashValue = hashAFile(new File("spongeBob.jpg"), "SHA-1");
        System.out.println(hashValue + "  " + "spongeBob.jpg");
    } //main()

    private static String hashString(String message, String algorithm) {
        //Compute the hash value of message using algorithm and return a string representation of it
        byte[] hashedBytes = null; //will store the hash value of message
        try {
            //instantiate the specified algorithm
            //It may not exist, thus the try-catch
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            //Compute the hash value of message
            hashedBytes = digest.digest(message.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) { //TODO: ask why this is not supported?
            e.printStackTrace();
        }
        //Convert hash value (in byte[]) to a hex String and return result
        return bToH(hashedBytes);
    } //hashString()

    private static String hashAFile(File filename, String algorithm) {
        //Hash the contents of the file, fileName, and return its hash value as a hex string
        byte[] hashedBytes = null; //the result
        try {
            //Open the file
            FileInputStream inStream = new FileInputStream(filename);

            //Instantiate a digest with the algorithm
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            //Define input file chunk buffer (of 1024 bytes)
            byte[] buffer = new byte[1024];
            int bytesRead = -1; //counts how many bytes were read

            while((bytesRead = inStream.read(buffer)) != -1) {
                //There are bytes in input file to process supply the chunk to digest
                digest.update(buffer, 0, bytesRead);
            }

            //Finalize computation
            hashedBytes = digest.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            //Catches both: algorithm and file I/O exceptions
            e.printStackTrace();
        }
        return bToH(hashedBytes); //Convert bytes to hex string
    } //hashAFile()

    private static String bToH(byte[] value) {
        //Converts value to a string of hex digits
        StringBuilder sb = new StringBuilder(value.length*2);
        //Why length*2? See Question 3.
        for(byte b: value) {
            sb.append(String.format("%02x", b)); //Each byte has 2 hex digits
        }
        return sb.toString();
    } //bToH()
}
