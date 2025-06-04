import java.util.*;

public class GraphPart5{
 static  class dsu {
        static  int n = 4 ;
        static  int par[] = new int[n] ;
        static  int rank[] = new int[n] ;
    
    
    
        public void init () {
            for(int i =0; i<n ;i++){
                par[i] = i ;
            }
        } 
        public int find(int x ) {
            if(x == par[x]){
            return  x ;
            }
            return  par[x]=find(par[x]) ;
        }
        public void union (int a ,int b) {
            int parA = find(a) ;
            int parB = find(b) ;
    
            if(rank[parA] == rank[parB]) {
                par[parB] = parA ;
                rank[parA]++ ;
            }
            else if (rank[parA] <rank[parB]) {
                par[parA] = parB ;
            }
            else {
                par[parB] = parA ;
            }
        }
    }
    static  int n = 4 ;
    static  int par[] = new int[n] ;
    static  int rank[] = new int[n] ;



    public static void init () {
        for(int i =0; i<n ;i++){
            par[i] = i ;
        }
    } 
    public static int find(int x ) {
        if(x == par[x]){
        return  x ;
        }
        return  par[x]=find(par[x]) ;
    }
    public static  void union (int a ,int b) {
        int parA = find(a) ;
        int parB = find(b) ;

        if(rank[parA] == rank[parB]) {
            par[parB] = parA ;
            rank[parA]++ ;
        }
        else if (rank[parA] <rank[parB]) {
            par[parA] = parB ;
        }
        else {
            par[parB] = parA ;
        }
    }

      static  class Edge  implements Comparable<Edge>{
        int src; 
        int dest ;
        int weight ;

            //constructor
        public Edge (int s , int d ,int w) {
            this.src = s ;
            this.dest = d ;
            this.weight = w ;
        }

        @Override 
        public int compareTo (Edge e2) {
            return  this.weight - e2.weight ;
        }
      }
    public static void main(String[] args) {
        int V = 7 ;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        // Add edges to the graph (undirected)
        graph.get(0).add(1);
        graph.get(1).add(0);
        graph.get(1).add(2);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(4).add(3);

        if (detectcycle(graph, V)) {
            System.out.println("Cycle detected in the graph.");
        } else {
            System.out.println("No cycle detected.");
        }
    }


    
    static void createGraph (ArrayList <Edge> edges ) {
        //edges
        edges.add(new Edge(0, 1, 10)) ;
        edges.add(new Edge(0, 2, 15)) ;
        edges.add(new Edge(0, 3, 30)) ;
        edges.add(new Edge(1, 3, 40)) ;
        edges.add(new Edge(1, 3, 50)) ;

    }
    public static void KrushkalAlgo(ArrayList <Edge> edges ,int v) {
        init();
        Collections.sort(edges) ; // o(E log E)
        int mstCost =  0; 
        int count =0  ;

        for(int i  = 0; count <v-1; i++)  {
            Edge e = edges.get(i) ;

            //src , dest ,wt

            int parA = find(e.src) ;
            int parB = find(e.dest) ;

            //no cycle
            if(parA != parB) {
                union(e.src, e.dest);
                mstCost += e.weight ;
                count++;
            }
        }
        System.out.println(mstCost);
    }
    public static int motherVertex (List<List <Integer> > graph ,int v ) {
        //vis array
        boolean vis[] = new boolean[v] ;
        int lastfinished = -1 ; //default

        //find the last finished
        for(int i = 0 ; i<v ;i++){
            if(! vis[i]){
                dfs(graph,i,vis) ;
               lastfinished = i; 
            }
         
        }

        // check wheather the lastf is mother vertex or not
         Arrays.fill(vis, false); //restore 
         dfs(graph,lastfinished,vis) ;
         for(boolean b : vis){
             if(!b) {
                return -1 ; //we are not reaching every vertex
             }
         }

        return  lastfinished ;
    }
    static void dfs(List<List<Integer>> graph ,int curr ,boolean vis[]){
        //mark visited
        vis[curr] = true ;
        //neighbour call
        for(int nbr : graph.get(curr)){
            if(!vis[nbr]){
                dfs(graph, nbr, vis);      
            }
        }
    }

    // cycle detect through Union find(DSU)
    static boolean  detectcycle(List<List<Integer>> graph ,int v){
         // loop over the graph 
        for(int i  = 0; i<v; i++){
            for(int u : graph.get(i)){
                if(find(i) == find(u)){
                    return true ; // graph content cycle
                }
                union(u, i); //union component
            }
        }
        return false ; //no cycle detected
    }    
}