import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.SynchronousQueue;


public class Main {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
//        inputStream = new FileInputStream(new File("palindrome.in"));
        outputStream = new FileOutputStream(new File("output.txt"));
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int t = 1;
        t = in.nextInt();
        for(int i = 0; i < t; i++){
            solve(in, out);
        }
        out.close();
    }
    static void solve(InputReader in, PrintWriter out) {
        Random rnd = new Random();
        String nickname = "";
        String name = "";
        String surname = "";
        for(int i = 0; i < 25; i++){
            nickname += String.valueOf((char)('a' + rnd.nextInt(25)));
            if(i <= 5){
                name += nickname.charAt(i);
            }else{
                surname += nickname.charAt(i);
            }
        }

        String email = nickname + "@gmail.com";

        String number = "";
        for(int i = 0; i < 11; i++){
            number += String.valueOf(rnd.nextInt(10));
        }

        out.println("INSERT INTO USER (nickname, photo_url, phone_number, email, name, surname, password)\n" +
                "VALUES ('" + nickname + "', null, '" + number + "', '" + email + "', '" + name + "', '" + surname + "', '111111');");
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
