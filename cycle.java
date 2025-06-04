import java.util.ArrayList;

public class cycle {
     public static void main(String[] args) {   
        int  v = 3 ;
        ArrayList <Edge> graph []  = new ArrayList[v] ;
        createGraph(graph);
   
        System.out.println(detectCycle(graph));

    }


    static  class  Edge {
        int s,d ;
        public Edge (int s , int d) {
           this.s = s ;
           this.d = d ;
        }
      }
      

    public static boolean detectCycle(ArrayList<Edge>[]graph) {
        boolean vis [] = new boolean[graph.length] ;
        for(int i =0 ;i<graph.length ;i++){
            if (! vis[i]) {
                //for starting point there is no parent so par -> -1
                    if (detectCycleUtil(graph ,vis,i,-1) ) {
                        return true ;
                        //cycle exist is one of the parts
                    }
            }
        }
          return  false ;
        }
    public static boolean detectCycleUtil(ArrayList<Edge>[]graph,boolean vis[],int curr ,int parent){
             vis[curr] = true ;
             for(int  i = 0; i<graph[curr].size() ;i++){
                Edge e = graph[curr].get(i) ;
    
    
                //c-3
                if ( ! vis[e.d] ) {
                    if (detectCycleUtil(graph, vis, e.d, curr)) {
                           return true ;
                    }
                 
                }
                //c-1    
                else if ( vis[e.d] && parent != e.d) {
                    
                    return  true ;
                }
                //case ->2   neighbour is  visited and parent == neighbour 
             }     
             return false ; 
        }

        static void createGraph(ArrayList<Edge>[] graph){
           
            for(int i = 0 ; i < graph.length ; i++){
                graph[i] = new ArrayList<>();
            }
            //0 > vertex
            graph[0].add(new Edge(0, 1)) ;
            //graph[0].add(new Edge(0, 2)) ;

            //1 -> 
            graph[1].add(new Edge(1, 0)) ;
            graph[1].add(new Edge(1, 2)) ;

            //2->
            graph[2].add(new Edge(2, 1)) ;
           // graph[2].add(new Edge(2, 0)) ;
           

            


    }
}
