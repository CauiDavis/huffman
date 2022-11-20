import java.util.*;

public class HuffmanCode {
	public static void main(String[] args) {
        String word;
        Scanner input = new Scanner(System.in);

        System.out.println("--------------------------------------------");
        System.out.println("Algoritmo de Huffman");
        System.out.println("--------------------------------------------");
		System.out.println("digite o texto para ser codificado");
        word = input.nextLine(); 
        
        int[] charFreqs = new int[256];
        for (char c : word.toCharArray())
            charFreqs[c]++;
        
        HuffmanTree tree = buildTree(charFreqs);
        
        System.out.println("TABELA DE CODIGOS");
        System.out.println("SIMBOLO\tQUANTIDADE\tHUFFMAN CODIGO");
        printCodes(tree, new StringBuffer());
        
        String encode = encode(tree,word);
        
        System.out.println("\nTEXTO COMPACTADO");
        System.out.println(encode); 
        
        int normalSize = word.length() * 8;
        int compressedSize = encode.length();
        double rate = 100.0 - (compressedSize * 100.0 / normalSize);
        System.out.println("tamnanho normal: " + normalSize);
        System.out.println("tamanho compactado: " + compressedSize);
        System.out.printf("foi compactado e esta %.2f%% menor que o original. %n", rate);
        
        System.out.println("\n\nTEXTO DECODIFICADO");
        System.out.println(decode(tree,encode));

    }

    public static HuffmanTree buildTree(int[] charFreqs) {
    	
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
         
        for (int i = 0; i < charFreqs.length; i++){
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i)); // Inser os elementos, n� folha, na fila de prioridade
        }
        
        while (trees.size() > 1) {
            HuffmanTree a = trees.poll(); 
            HuffmanTree b = trees.poll(); 
 
            trees.offer(new HuffmanNode(a, b)); 
        }
        return trees.poll();
    }
 
    public static String encode(HuffmanTree tree, String encode){
    	assert tree != null;
    	
    	String encodeText = "";
        for (char c : encode.toCharArray()){
        	encodeText+=(getCodes(tree, new StringBuffer(),c));
        }
    	return encodeText;
    }
    
    public static String decode(HuffmanTree tree, String encode) {
    	assert tree != null;
    	
    	String decodeText="";
    	HuffmanNode node = (HuffmanNode)tree;
    	for (char code : encode.toCharArray()){
    		if (code == '0'){ 
    		    if (node.left instanceof HuffmanLeaf) { 
    		    	decodeText += ((HuffmanLeaf)node.left).value;   
	                node = (HuffmanNode)tree; 
	    		}else{
	    			node = (HuffmanNode) node.left; 
	    		}
    		}else if (code == '1'){ 
    		    if (node.right instanceof HuffmanLeaf) {
    		    	decodeText += ((HuffmanLeaf)node.right).value;
	                node = (HuffmanNode)tree;
	    		}else{
	    			node = (HuffmanNode) node.right;
	    		}
    		}
    	} 
    	return decodeText; 
    }    
    
    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t\t" + prefix);
 
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    
    public static String getCodes(HuffmanTree tree, StringBuffer prefix, char w) {
        assert tree != null;
        
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            
            if (leaf.value == w ){
            	return prefix.toString();
            }
            
        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;
 
            prefix.append('0');
            String left = getCodes(node.left, prefix, w);
            prefix.deleteCharAt(prefix.length()-1);
 
            prefix.append('1');
            String right = getCodes(node.right, prefix,w);
            prefix.deleteCharAt(prefix.length()-1);
            
            if (left==null) return right; else return left;
        }
		return null;
    }

}
