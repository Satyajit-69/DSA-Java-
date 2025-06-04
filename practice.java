
import java.util.*;
public  class practice {

    static  class edge {
        int s ; int d ;int wt ;
        public edge (int s ,int d ,int wt) {
            this.s = s ;
            this.d = d ;
            this.wt = wt ;
        } 
    }


    public static boolean detectCycle(ArrayList <edge > g[]) {
        boolean vis[] = new boolean[g.length] ;
        for(int i =0; i<g.length ;i++) {
            
            if (!vis[i]) {
                return detectcycleUtil(g ,vis ,i,-1) ;
            }
        } 
        return false ;
    }

    public static boolean detectcycleUtil(ArrayList <edge> g[] ,boolean v[] ,int curr ,int par){
            //mark as visited
            v[curr] = true ;

            //loop
            for(edge e : g[curr]){
            //case - 03 
            if ( !v[e.d]) {
                if (detectcycleUtil(g, v, e.d, curr)) {
                    return true ;
                }
            } 

            //case -01
            if (v[e.d] && par != curr) {
                return true ;
            }

            //case -02 : here nothing to do
            }

            return false ;
           

    }
    static void indeg(ArrayList <edge> graph[] ,int  arr[]) {
        for(int i= 0; i<graph.length ;i++) {
           for(edge e : graph[i]){
            arr[e.d] ++ ;
           }
        }
    }
    static void KahnAlgo (ArrayList <edge> graph[]) {
        //cal indeg
        int inDeg [] = new int[graph.length] ;
        indeg(graph, inDeg); //cal indeg

        //Queue
        Queue <Integer> q = new LinkedList<>() ;
        
        //loop on indeg
        for(int i =0; i<inDeg.length ;i++){
            if (inDeg[i] == 0) {
                q.add(i) ;
            }
        }

        //bfs
        while(!q.isEmpty()) {
            int curr = q.remove() ;
            System.out.print(curr +"  ");
            for(edge e : graph[curr]) {
                inDeg[e.d] -- ; //decrease indeg of neighbours by 1
                if (inDeg[e.d] == 0) {
                    q.add(e.d) ;
                }
            }
        }
    }
    static void allpath(ArrayList <edge > graph [] , int src , int dest ,String ans) {
        //base case
        if(src == dest) {
            System.out.println(ans + " " +dest);
            return;
        }

        //neighbour call
        for(int i =0; i<graph.length ;i++){
            for(edge e :graph[i]) {
                allpath(graph, e.d, dest, ans + " "+ src);
            }
        }
    }
    

    static class Pair implements Comparable<Pair> {
        int n ; //element
        int path ; //path
        
        public Pair(int n ,int path) {
            this.n = n ;
            this.path = path ;
        }
        @Override  //sorting base on path val 
        public int compareTo(Pair p2) {
            return this.path - p2.path ;
        }

    }
    
    static void djkarstra(ArrayList <edge > graph [] ,int src ,int dest) {
        //distance array
        int dist [] = new int[graph.length] ;
        for(int i =0; i<graph.length ;i++) {
            if( i != src) {
                dist[i] = Integer.MAX_VALUE ;
            }
        }
         
        //vis array
        boolean vis [] = new boolean[graph.length] ;
        PriorityQueue  < Pair > q = new PriorityQueue<>() ;

        q.add(new Pair(src, 0)) ;

        //loop
        while (!q.isEmpty()) {
            Pair curr = q.remove() ;
            //call for unvisited
            if(!vis [curr.n]) {
                for(edge e : graph[curr.n]) {
                    int u = e.s ;
                    int v = e.d ;
                    int w = e.wt ;

                    //comparision  condition
                    if(dist[u] + w < dist[v]) {
                        int newDist = dist[u]+ w ;
                        q.add(new Pair(v, newDist)) ;
                    }
                }
            }
            
        }

        for(int i =0 ;i<dist.length ;i++){
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }


}M