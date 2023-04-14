package cifradoCesar;

public class cifrado {
    static String msg = "";

    public static void main(String[] args) {

        // msg = args[0];
        msg = "pedroxzxz";
        int aux;
        int numeroAD = 3;
        System.out.println(msg);
        String msgCi = "";
        for (int i = 0; i < msg.length(); i++) {
            // for (char j = 'a'; j <= 'z'; j++) {
            char c = msg.charAt(i);
            // System.out.print((char)(c+('A'-'a')));
            if (c < 'x') {
                msgCi += (char) (c + numeroAD);
            } else {
                aux=0;
                aux=(c%'a')+3;
                System.out.println(aux);
                msgCi +=(char)(((aux-'a')*-1));
            }

            // }
        }
        System.out.println(msgCi);
    }

}
