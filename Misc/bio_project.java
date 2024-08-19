import java.util.*;

public class bio_project {
    static String residues = "GPPPARTPCQQELDQVLERISTMRLPDERGPLEHLYSLHIPNCDKHGLYNLKQCKMSLNGQRGECWCVNPNTGKLIQGAPTIRGDPECHLFYNEQQEARGVHTQRMQ";
    
    public static void main(String[] args) {
        HashMap<Character, Integer> freqs = new HashMap<Character, Integer>();
        for(int i = 0; i < residues.length(); i++) {
            char c = residues.charAt(i);
            Integer count = freqs.get(c);
            if(count == null) {
                count = 0;
            }
            freqs.put(c, count + 1);
            if(c == 'C') {
                System.out.print((i + 183) + " ");
            }
        }
        System.out.println();
        System.out.println(freqs);
        System.out.println(freqs.size());
    }
}
