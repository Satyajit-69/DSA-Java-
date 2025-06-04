
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class assignments {
    static class edge {
        int src ;
        int dest;
        int weight;

        public edge(int src , int dest ,int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    static void createGraph(ArrayList<edge>[] graph){
           
            for(int i = 0 ; i < graph.length ; i++){
                graph[i] = new ArrayList<>();
            }
            
           // graph[1].add(new edge(1, 5, 1)) ;
            graph[1].add(new edge(1, 9, 1)) ;


            graph[2].add(new edge(2, 4, 1)) ;
            graph[2].add(new edge(2, 5, 1)) ;

            graph[4].add(new edge(4, 2, 1)) ;

            graph[5].add(new edge(5, 9, 1)) ;
            graph[5].add(new edge(5, 2, 1)) ;
            

            graph[9].add(new edge(9, 5, 1)) ;
            graph[9].add(new edge(9, 1, 1)) ;


         

    }
    public static void main(String[] args) {
        int v = 10 ;
        ArrayList<edge> graph[]= new ArrayList[v] ;
        
        createGraph(graph) ;

        if(Cycledetection(graph, v)) {
            System.out.println("true");
        }else{
            System.out.println("false");
        }
    }
    static boolean Cycledetection(ArrayList <edge> list[] ,int v) {
       boolean vis [] = new boolean[list.length] ;

       for(int i = 0; i< list.length ;i++){
        if( ! vis[i]) {
            if(util(list,vis,i ,v)){
                return true ;
            }
        }
       }

       return false ;
    } 
    static boolean util(ArrayList<edge> graph [] ,boolean vis[],int src ,int v) {
        //mark as visited
         vis[src] = true ;


         //parent array
         int parent[] = new int[graph.length];
         Arrays.fill(parent, -1);

         Queue <Integer > q = new LinkedList<>() ;
         q.add(src) ;


         //bfs
         while (! q.isEmpty()) {
            int curr = q.remove() ;
            for(edge e  : graph[curr]) {
                //if unvisited
                if(!vis[e.dest]){
                vis[e.dest] = true;
                parent[e.dest] = curr ;
                q.add(e.dest);
                }

                else if ( parent[e.dest] != curr) {
                    return true ;
                }
               
            }
            
         }
            return  false ;
        
    }



    
    }

