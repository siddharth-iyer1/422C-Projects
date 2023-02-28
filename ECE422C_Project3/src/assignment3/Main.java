/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Siddharth Iyer
 * ssi325
 * Sidharth Shyamkumar
 * sns3446
 * Slip days used: 0
 * Git URL: https://github.com/ECE422C-Shi/sp-23-assignment-3-sp23-pair-11
 * Spring 2023
 */

 package assignment3;

 import javax.swing.text.html.HTMLDocument;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintStream;
 import java.lang.reflect.Array;
 import java.util.*;
 
 public class Main {
 
     // static variables and constants only here.
     private static Set<String> dictionary;
     private static Map<String, ArrayList<String>> adjacencyList = new HashMap<String,ArrayList<String>>();
     private static ArrayList<String> path = new ArrayList<String>();    // Return ArrayList
     private static Set<String> visited = new HashSet<String>(); // Use for BFS DFS
     private static String startWord;
     private static String endWord;
 
     public static void main(String[] args) throws Exception {
         Scanner kb;     // input Scanner for commands
         PrintStream ps; // output file, for student testing and grading only
         // If arguments are specified, read/write from/to files instead of Std IO.
         if (args.length != 0) {
             kb = new Scanner(new File(args[0]));
             ps = new PrintStream(new File(args[1]));
             System.setOut(ps);              // redirect output to ps
         } else {
             kb = new Scanner(System.in);    // default input from Stdin
             ps = System.out;                // default output to Stdout
         }
         initialize();
 
         if(parse(kb) == null){
             return;
         }
         printLadder(getWordLadderDFS(startWord.toUpperCase(), endWord.toUpperCase()));
         printLadder(getWordLadderBFS(startWord.toUpperCase(), endWord.toUpperCase()));
     }


     /**
      * @brief This method initializes the dictionary and creates the adjacency list structure to represent the graph.
      * @return void
      */
     public static void initialize() {
         // initialize your static variables or constants here.
         // We will call this method before running our JUNIT tests.  So call it
         // only once at the start of main.
         dictionary = makeDictionary();
         Iterator<String> iter = dictionary.iterator();
         while(iter.hasNext()){
             String curr = iter.next();
             fillAdjacencyList(curr);
         }
     }

     /**
      * @brief Iterates through every letter in the alphabet, checks dictionary validity, then adds to list if applicable.
      * @param curr, represents the initial word which graph edges are being drawn for in the Adjacency list representation
      * @return void
      */
     public static void fillAdjacencyList(String curr){
         for(int i = 0; i < curr.length(); i++){
             char[] wordArr = curr.toCharArray();
             char curLetter = wordArr[i]; //Grabs actual letter at index we are going to manipulate
             for(int j = 0; j<26; j++){
                 if(curLetter==(char)(j+97) || curLetter==(char)(j+65))continue; //Continues if same letter as actual letter is about to be tried
                 wordArr[i] = (char)(j+65);
                 if(dictionary.contains(String.valueOf(wordArr))){
                     if(!adjacencyList.containsKey(curr)){
                         ArrayList<String> adjacents = new ArrayList<>();
                         adjacents.add(String.valueOf(wordArr));
                         adjacencyList.put(curr, adjacents);
                     }
                     else{
                         adjacencyList.get(curr).add(String.valueOf(wordArr));
                     }
                 }
 
             }
         }
     }
 
     /**
      * @brief Interprets user entry to prepare for searching algorithms. If command is /quit, This returns an empty ArrayList.
      * @param keyboard Scanner connected to System.in
      * @return ArrayList of Strings containing start word and end word.
      */

     public static ArrayList<String> parse(Scanner keyboard) {
         ArrayList<String> entries = new ArrayList<>();
         String command = keyboard.next().toLowerCase();
         if(command.equals("/quit")){
             entries.clear();
             return entries;
         }
         entries.add(command);
         command = keyboard.next().toLowerCase();
         if(command.equals("/quit")){
             entries.clear();
             return entries;
         }
         entries.add(command);
         startWord = entries.get(0);
         endWord = entries.get(1);
         return entries;
     }

     /**
      * @brief Uses DFS recursive helper function to generate a word ladder between the entries. If the path size is too small,
      *        this returns a list comprised of the start and end word
      * @param start, represents beginning of the path
      * @param end, represents the end of the path
      * @return ArrayList of Strings, this will be the returned Word Ladder
      */
     public static ArrayList<String> getWordLadderDFS(String start, String end) {
         path.clear();
         DFS(start, end, adjacencyList, visited, path);
         visited.clear();
         if(path.size()<2){
             path.clear();
             path.add(start);
             path.add(end);
         }
         return path;
     }

      /**
      * @brief DFS recursive helper function used to generate a path between the entries using depth first search
      * @param current, current behaves like start from getWordLadderDFS, but is updated to the next word in the path every recursive call
      * @param end, the target word
      * @param map, checks array list of current string to look for possible additions to the path
      * @param visited, marks off explored nodes
      * @param path, adds to path each time a valid edge is found
      * @return boolean values to facilitate recursive calls. Each time a call is popped off the stack, an item is added/removed from the path
      */
     public static boolean DFS(String current, String end, Map<String, ArrayList<String>> map, Set<String> visited, ArrayList<String> path){
         current = current.toUpperCase();
         end = end.toUpperCase();
         visited.add(current);
         path.add(current);
         if(current.equalsIgnoreCase(end)) return true;
         if(!dictionary.contains(current) || map.get(current) == null)return false;
         for(int i = 0; i<map.get(current).size(); i++){
             if(!visited.contains(map.get(current).get(i))){
                 if(DFS(map.get(current).get(i), end, adjacencyList, visited, path))return true;
             }
         }
         path.remove(current);
         return false;
     }
 
     /**
      * @brief Generates a word ladder between the entries using a breadth first search.
      * @param start, represents the beginning the path
      * @param end, represents the end of the path
      * @return ArrayList of Strings, this will be the returned Word Ladder
      * Visited nodes are tracked using an array list, and valid nodes on the path are added accordingly when connections are found
      */
     public static ArrayList<String> getWordLadderBFS(String start, String end) {
         path.clear();
         ArrayList<String> queue = new ArrayList<String>();
         Map<String, String> parents = new HashMap<String, String>();
 
         queue.add(start);
         visited.add(start);
         while(!queue.isEmpty()){
             String curr = queue.remove(0).toUpperCase();
             if(curr.equalsIgnoreCase(end)){
                 while(!curr.equalsIgnoreCase(start)){
                     path.add(curr);
                     curr = parents.get(curr);
                 }
                 path.add(start.toUpperCase());
                 Collections.reverse(path);
                 visited.clear();
                 return path;
             }
             if(!dictionary.contains(curr) || adjacencyList.get(curr) == null)continue;
             for(int i = 0; i< adjacencyList.get(curr).size(); i++){
                     if(!visited.contains(adjacencyList.get(curr).get(i))){
                         queue.add(adjacencyList.get(curr).get(i));
                         visited.add(adjacencyList.get(curr).get(i));
                         parents.put(adjacencyList.get(curr).get(i), curr);
                     }
             }
         }
         path.add(start);
         path.add(end);
         return path;
     }
 
     /**
      * @brief This prints the word ladder requested in the input. If the length of the ladder is too short, this prints "no ladder exists" message
      * @param ladder, this is the output ArrayList after running BFS/DFS methods
      * @return void
      */
     
     public static void printLadder(ArrayList<String> ladder) {
         if(ladder.size()<= 2){
             startWord = ladder.get(0);
             endWord = ladder.get(1);
             System.out.println("no word ladder can be found between " + startWord.toLowerCase() + " and " + endWord.toLowerCase() + ".");
             return;
         }
         System.out.println("a " + (ladder.size()-2) + "-rung word ladder exists between " + ladder.get(0).toLowerCase() + " and " + ladder.get(ladder.size()-1).toLowerCase() + ".");
         for(String s: ladder){
             System.out.println(s.toLowerCase());
         }
     }
 
     /* Do not modify makeDictionary */
     public static Set<String> makeDictionary() {
         Set<String> words = new HashSet<String>();
         Scanner infile = null;
         try {
             infile = new Scanner(new File("five_letter_words.txt"));
         } catch (FileNotFoundException e) {
             System.out.println("Dictionary File not Found!");
             e.printStackTrace();
             System.exit(1);
         }
         while (infile.hasNext()) {
             words.add(infile.next().toUpperCase());
         }
         return words;
     }
 }
