
import java.util.*;

public class graph6 {
   public static void main(String[] args) {
    int v = 5 ;
    ArrayList <Edge> graph[] = new ArrayList[v] ;
    createGraph(graph) ;
    // getBridge(graph, v);
       getArticulation(graph, v);
    //kosaraju(graph, v);
   }
   static class  Edge {
    int src ;
    int dest ;
    int weight ;
     
     public Edge(int s ,int d,int w) {
        this.src = s ;
        this.dest = d ;
        this.weight = w ;
     }
   }
   public static void createGraph(ArrayList <Edge> graph[]) {
       
      for(int i = 0;  i<graph.length ;i++){
        graph[i] = new ArrayList<Edge>() ;
      }

      // graph[0].add(new Edge(0, 2, 0)) ;
      // graph[0].add(new Edge(0, 3, 0)) ;
      // graph[1].add(new Edge(1, 0, 0)) ;
      // graph[2].add(new Edge(2, 1, 0)) ;
      // graph[3].add(new Edge(3, 4, 0)) ;
        

      graph[0].add(new Edge(0, 1, 0)) ;
      graph[0].add(new Edge(0, 2, 0)) ;
      graph[0].add(new Edge(0, 3, 0)) ;


      graph[1].add(new Edge(1, 0, 0)) ;
      graph[1].add(new Edge(1, 2, 0)) ;


      graph[2].add(new Edge(2, 0, 0)) ;
      graph[2].add(new Edge(2, 1, 0)) ;


      graph[3].add(new Edge(3, 0, 0)) ;
      graph[3].add(new Edge(3, 4, 0)) ;

      graph[4].add(new Edge(4, 3, 0)) ;
      //graph[0].add(new Edge(0, 2, 0)) ;

   }
   public static void kosaraju (ArrayList <Edge> graph[] , int v) {
    //steps-1  topsort
    Stack <Integer> s = new Stack<>() ;
    boolean vis[] = new boolean[v] ;
    for(int i = 0; i<v ;i++){
        if(!vis[i]) {
            topSort(graph, i, vis, s);
        }
    }
    
    //steps-2  transpose graph
    ArrayList <Edge> transpose[] = new ArrayList[v] ;
    for(int i = 0;  i<transpose.length ;i++){
        vis[i] = false ;//reinitialize
        transpose[i] = new ArrayList<Edge>() ;
      }
    for(int i = 0 ; i<v ;i++){    //get the neighbour idx
      for( Edge e : graph[i]) {
       // dest -> src (reverse)
          transpose[e.dest].add(new Edge(e.dest, e.src, 0)) ; 
      }
    }    


    //steps-3 normal dfs on reverted graph
    while (! s.isEmpty()) {
        int curr = s.pop() ;
        if(! vis[curr]) {
            System.out.print("SEC -> ");
            dfs(transpose, curr, vis);
            System.out.println();
        }
    }
}
   public static void topSort(ArrayList <Edge> graph[] ,int curr ,boolean vis[] ,Stack <Integer> stack) {
      vis[curr] = true ; //visited
      for(Edge e : graph[curr]){ //for neighbour
        if(!vis[e.dest])  {
            topSort(graph, e.dest, vis, stack);
        }
      }

      stack.push(curr) ; //backtrack
   }
   public static void dfs(ArrayList <Edge> graph[] ,int curr ,boolean vis[] ) {
     vis[curr] = true ;

     System.out.print(curr + " ");
     for(Edge e : graph[curr]){
        if(!vis[e.dest])  {
            dfs(graph, e.dest, vis);
        }
      }
   }
   public static void getBridge(ArrayList <Edge> graph[] ,int v) {
    int dt[] = new int[v] ; //discovery time
    int low[] = new int[v] ; // low discovery time
    int time = 0 ;
    boolean vis[] = new boolean[v] ;

    for(int i =0; i<v ;i++){
      if( ! vis[i]) {
         bridgeDfs(graph,i, -1, dt, low, vis, time);
      }
    }

   }
   public static void bridgeDfs(ArrayList<Edge> graph[] , int curr ,int par ,int dt[] ,int low[] ,
                                              boolean vis[] , int time) {
   
         vis[curr] = true ;
         dt[curr] = low[curr] = ++time ;
         
         for(Edge e :graph[curr]) {
          int neigh = e.dest ;

          //case -1 
              if(neigh == par) {
                continue ; //ignore 
              }
         //case - 2
              else if(! vis[neigh]) {
                bridgeDfs(graph, neigh, curr, dt, low, vis, time);
                low[curr] = Math.min(low[curr], low[neigh]) ;

                 //bridge condition
                 if(dt[curr] < low[neigh]) {
                  System.out.println("Bridge : " + curr + " ------ " + neigh);
                 }
              }
         //case - 3
              else {
                low[curr] = Math.min(low[curr], dt[neigh]) ;
              }
         }
  }
   public static void getArticulation(ArrayList<Edge> graph[] , int v) {
    int dt[] = new int[v] ; //discovery time
    int low[] = new int[v] ; // low discovery time
    int time = 0 ;
    boolean vis[] = new boolean[v] ;

    for(int i =0; i<v ;i++){
      if( ! vis[i]) {
         articulationDfs(graph,i, -1, dt, low, time, vis);
      }
    }
    
   }
   public static void articulationDfs(ArrayList<Edge> graph[],int curr ,int par ,
                                   int dt[],int low[],int time , boolean vis[]){
                                  
            //normal dfs                               
            vis[curr] = true ;
            dt[curr] = low[curr] = ++time ; 
            int children = 0 ; 
            
            
            for(Edge e : graph[curr]) {
              int neigh = e.dest ;
                  //case -1
                  if(neigh == par) {
                    continue ; //ignore
                  }
                  else if(! vis[neigh]) {//case -2
                    articulationDfs(graph, neigh, curr, dt, low, time, vis) ;
                    low[curr] = Math.min(low[curr], low[neigh]) ;
                    children++ ;
                    //articulation condition
                    if(par != -1 && dt[curr] <= low[neigh]) {
                      System.out.println("Articulation Point : " + curr);
                    }
                  }
                  else { //case -3
                      low[curr] = Math.min(low[curr], dt[neigh]) ;
                 }
            }
            //special case for root node
            if(par == -1 && children > 1) {
              System.out.println("Articulation Point : " + curr);
            }
    
                                 
     }
  }
    
    
