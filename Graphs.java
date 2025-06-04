
import java.util.*;

public class Graphs {
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


         
          graph[0].add(new edge(0, 1 ,10)) ;
          graph[0].add(new edge(0, 2,15)) ; 
          graph[0].add(new edge(0, 3 ,30)) ;

          
        
          graph[1].add(new edge(1, 0 ,10)) ;
          //graph[1].add(new edge(1, 3 ,7)) ;
          graph[1].add(new edge(1, 3, 40)) ;


          graph[2].add(new edge(2, 0, 15)) ;
          graph[2].add(new edge(2, 3, 50)) ;
        //   graph[3].add(new edge(3, 1, 1)) ;
          graph[3].add(new edge(3, 0, 30)) ;
          graph[3].add(new edge(3, 1, 40)) ; 
          graph[3].add(new edge(3, 2, 50)) ;
          //graph[4].add(new edge(4, 5, 5)) ;

        //   graph[5].add(new edge(5, 0, 1)) ;
        //   graph[5].add(new edge(5, 2, 1)) ;
          
             
            


    }
     static void bfsUtil(ArrayList<edge>[] graph , boolean  vis[] ){ //tc - > O(v + e) , sc -> O(v)
       Queue<Integer> que = new LinkedList<>();
       //boolean[] vis = new boolean[graph.length];
       //source -> lets take 0 

       que.add(0);
       while (!que.isEmpty()) {//q contains the vertex
             
           int curr = que.remove(); //pick the vertex
           if(! vis[curr]) { //not visited
               //step 1 -> print
             System.out.print(curr +" ") ;
               //visited -> true
             vis[curr] = true;

               //push the nearest neighubour into the queue
                for(edge e : graph[curr]){
                    if(!vis[e.dest]){
                        que.add(e.dest);
                    }
                }

           }
           
        }
       }
     static void dfs(ArrayList<edge> graph[]){
        boolean vis []= new boolean[graph.length] ;
        for(int i =0; i<graph.length ;i++){
            dfsutil(graph,i,vis);
        }
    }
     static void dfsutil(ArrayList<edge>graph[],int curr ,boolean[] vis){ //tc - > O(v + e) , sc -> O(v)
               //visited array
           System.out.print(curr +" ");
           vis[curr] = true;

           //recursion for the neighbours 
           //who are not visited
           for(int i =0 ;i<graph[curr].size();i++){
             edge e = graph[curr].get(i);
               if(!vis[e.dest]){
                   dfsutil(graph,e.dest,vis);
               }
           }

       }
     static boolean haspath(ArrayList<edge>[] graph ,int src ,int dest ,boolean[] vis){
          //1 st case 
          if (src == dest) return true;
          //2nd  case
            vis[src] = true;
            //3rd case
            for(edge e : graph[src]){ // the unvisited neighbours and check if they have path to the destination
                if(!vis[e.dest]){
                    boolean res = haspath(graph,e.dest,dest,vis);
                    if(res) return true;
                }
            }
            return false;

    }
     static void bfs(ArrayList <edge >[] graph){
        boolean vis[] = new boolean[graph.length] ;
        for(int i =0; i<graph.length ;i++){
            if (! vis[i]) {
                bfsUtil(graph,vis);
            }
        }
    }

     static boolean  bipitate(ArrayList <edge> graph[] ){
        int col[] = new int[graph.length] ;
        for(int i =0 ;i<col.length ;i++){
            col[i]= -1 ; //default color  / no colour
        }
         
        //colours 
               // -1 means no colour
               //  0 means yellow colour
               //  1 means blue
        Queue <Integer> q = new LinkedList<>() ;
        for(int i = 0 ;i<graph.length ;i++){
            if (col[i]== -1) {
                q.add(i)  ;
                col[i] = 0 ; //yellow
                while (!q.isEmpty()) {
                    int curr = q.remove() ;
                    for(int j =0 ;j<graph[curr].size(); j++){
                        //neighbour - > e.dest
                        edge e = graph[curr].get(j) ;

                        //case -01 
                        if (col[e.dest] == -1) {
                            int nextCol = col[curr] == 0 ? 1 : 0 ; //this means if curr - 0 then next col 1 otherwise  0
                            col[e.dest] = nextCol ;

                        }else if (col[e.dest]  == col[curr]) {
                            return false ; //not bipatite
                        }
                    }
                }
            }
        }
        return false ;

    }
     static boolean  Cycleindirected(ArrayList <edge > g[]) {
        boolean vis[]= new boolean[g.length] ;
        boolean stack[]= new boolean[g.length] ;

        for(int i =0 ; i<g.length ;i++){
            if (! vis [i]) {
                if ( isCycleDirected (g ,i,vis ,stack ) ) {
                    return true ;
                }
            }
        }
        return false ;
    }
     static boolean isCycleDirected(ArrayList<edge> graph[] , int curr ,boolean  vis [] ,boolean stack []) {
         vis[curr] = true ;
         stack [curr] = true ;
        

         for(edge e : graph[curr]) {
             if (! vis [e.dest] && isCycleDirected(graph, e.dest, vis, stack)) {
                 return true ;
             }
             if (stack[e.dest]) {
                return true ; //cycle exist 
             }
         }
          
         stack[curr] = false ; //removing the ele 
         return false ;

    }

   
    static void topologicalsorting(ArrayList<edge> graph []) { 
            boolean vis []  = new boolean[graph.length] ;
            Stack<Integer> s = new Stack<>() ;


            //call the util function for not visited nodes
            for(int i =0; i <graph.length ;i++){
                if(! vis [i]) {
                    tsUtil(graph , i ,vis ,s) ;
                }
            }

            while (! s.isEmpty()) {
                System.out.print (s.pop()+" ");
            }
        
        }
    static void tsUtil(ArrayList <edge > g [] ,int curr , boolean vis [] ,Stack <Integer > s ) {
            //mark as visited
            vis[curr] = true ; 
            //neighbour call
            for(edge e : g[curr]) {
                if(! vis [e.dest]) {
                    tsUtil(g, e.dest, vis, s);
                }
            }
            s.push(curr) ; //push the ele into the  stack
        }
    static void topSort(ArrayList <edge> graph []) {
        int indeg []  = new int[graph.length] ;
        calIndeg(graph, indeg);
        Queue <Integer > q = new  LinkedList<>() ;

        for(int i =0;  i<indeg.length ;i++){
          if(indeg [i] == 0) {
              q.add(i) ;
          }
        }


        //bfs 
        while (! q.isEmpty()) { //top sort
          int curr = q.remove() ;
          System.out.print(curr +" ");

          for(edge e : graph[curr]) { //for printed ele decrease their
                                      //neighbour's indegree by 1
              indeg[e.dest] -- ;
              if (indeg[e.dest] == 0) {
                  q.add(e.dest) ;
              }
          }
        }

  }
    static void calIndeg(ArrayList< edge > graph[] ,int indeg []) {
        for(int i =0; i<graph.length ;i++){
        for(edge e : graph[i]) {
            indeg[e.dest]++ ;
        }
        }
    }
    static void Allpath(ArrayList<edge> list[] , int src ,int des ,String path){
     //base case 
     if(src == des) {
        System.out.println(path +"->"+ des);
        return;
     }
     

     for(edge e : list[src]) {
        Allpath(list, e.dest, des, path +"->"+ src);
     }

  }
 
    


     static class Pair  implements Comparable<Pair>{
        int n ;
        int path ;

        public Pair(int n ,int path) {
            this.n = n ;
            this.path = path ;
        }


        @Override 
        public int compareTo(Pair p2) {
            return  this.path-p2.path ; //path base sorting
        }
     }
     static void dijkstra(ArrayList <edge > graph[] ,int src) {
          int dist [] = new int[graph.length] ; //dist[i]  ->src -i 
          for(int i= 0; i<graph.length ;i++) {
            if( i != src){
                dist[i] = Integer.MAX_VALUE  ; //infinite
            }
          }   
        boolean vis[] = new boolean[graph.length];
        PriorityQueue <Pair> p =  new PriorityQueue<>() ;
        p.add(new Pair(src, 0)) ;
    
        //loop
        while (!p.isEmpty()) {
            Pair curr = p.remove() ;
            if ( ! vis[curr.n]) {
                vis[curr.n] = true ;


                //neighbours
                for(edge e :graph[curr.n]) {
                    int u = e.src ;
                    int v = e.dest ;
                    int wt = e.weight ;


                     //comparision

                     if(dist[u]+wt < dist[v]){ //update distance of src to V
                         dist[v] = dist[u] + wt ;
                         p.add(new Pair(v, dist[v])) ;
                     }

                }
            }
        }



        //print all the source to vertices shortest dist
        for(int i =0 ;i< graph.length ;i++){
            System.out.print(dist[i]+" ");
        }
        System.out.println();








     }

     static void BelmenFord(ArrayList <edge > graph ,int src ,int V) {
        int dist [] = new int[V] ;
        for(int i   =0; i<dist.length ;i++){
            if( i != src ){
                dist[i] = Integer.MAX_VALUE ;
            }
        }

        //algo
        //O(VE)
        for(int i = 0; i<V-1;i++){
            //edge - O(E)
            for(int j =0; j<graph.size();j++){
                 edge e = graph.get(j);
                 //u,v,wt
                 int u = e.src ;
                 int v = e.dest ;
                 int wt = e.weight ;
                 
                 //relaxation
                 if(dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]){
                    dist[v] = dist[u]+ wt ;
                 } 
            }
        }
        //print
        for(int i =0; i<dist.length ;i++){
           System.out.print(dist[i]+ " ");
        }
    }
    
     static void createGraph2(ArrayList <edge> graph) {
        
        graph.add(new edge(0, 1 ,2)) ; 
        graph.add(new edge(0, 2 ,4)) ;

        
      
        graph.add(new edge(1, 2 ,-4)) ;
        //graph[1].add(new edge(1, 3 ,7)) ;


        graph.add(new edge(2, 3, 2)) ;
      //   graph[3].add(new edge(3, 1, 1)) ;

        graph.add(new edge(3, 4, 4)) ; 

        graph.add(new edge(4, 1, -1)) ;
     }
     static void prims(ArrayList <edge> graph[]){
        boolean vis[] = new boolean[graph.length] ;
        PriorityQueue <Pair>  pq = new PriorityQueue<>() ;
        pq.add(new Pair(0, 0)) ;
        int finalcost = 0; //mst cost
        
        while(!pq.isEmpty()){
            Pair curr = pq.remove() ;
            if(! vis[curr.n]) {
                vis[curr.n] = true ;
                finalcost += curr.path ;
                for(edge e :graph[curr.n]) {
                pq.add(new Pair(e.dest, e.weight)) ;
            }
        } 
    }
      System.out.println("final cost of mst is  : "+ finalcost);
    
    }

    static  class Edge {
        int src ;
        int dest ;
        int weight ;

        public Edge(int s ,int d ,int wt){
            this.src = s ;
            this.dest = d ;
            this.weight = wt ;

        }
    }  
     public static void main(String[] args) {   
       int n =  4;
       int flights[][]= {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}} ;
       int src = 0 ;
       int dst = 3 ;
       int k = 1 ;
       
       System.out.println( CheapestFlights(n, flights, src, dst, k));
    }

    public static int CheapestFlights(int n , int flights[][],int src ,int dest,int k){
         //graph creation
      
         ArrayList <Edge> graph[] = new ArrayList[n] ;
         createGraph3(graph,flights);

         int dist [] = new int[n] ;
         Arrays.fill(dist, Integer.MAX_VALUE);
         dist[src]= 0;

        Queue <Info> q = new LinkedList<>() ;
        q.add(new Info(src, 0, 0)) ;

        while(! q.isEmpty()){
             Info curr = q.remove() ;
             if(curr.stops > k){
                break; 
             }

             for(Edge e :graph[curr.vertex]){
                 int u = e.src ;
                 int v=  e.dest ;
                 int wt = e.weight ;
                
                  //relaxation
                 if( curr.cost + wt < dist[v] && curr.stops<=k) {
                     dist[v] = curr.cost+ wt ;
                    q.add(new Info(v, dist[v], curr.stops+1));
                      //dist [v]  = cost 
                 }
             }


       }
                
                
                return dist[dest] == Integer.MAX_VALUE  ? -1 : dist[dest] ;
    }
    static  void createGraph3 ( ArrayList <Edge> graph[] ,int flights[][]) {
         for(int i =0; i<graph.length ;i++){
            graph[i] = new ArrayList<>() ;
        }
        for(int i =0; i<flights.length ;i++){
            int s = flights[i][0] ;
            int d = flights[i][1] ;
            int w = flights[i][2] ;

            Edge e = new Edge(s, d, w) ;
            graph[s].add(e) ;

        }

    }
    static  class Info {
        int vertex ;
        int cost ;
        int stops ;
         
        public Info(int v ,int c ,int s){
             this.vertex = v ;
             this.cost = c ;
             this.stops = s ;
        }
    }
    // static void createGraph3(int flights[][],ArrayList <Edge> graph[]) {
           
    // }

    static  class  edge2 implements  Comparable<edge2> {
        int dest ;
        int cost ;
        public edge2 (int d ,int c) {
            this.dest = dest ;
            this.cost = cost ;
        }
        @Override
        public  int compareTo(edge2 e2) {
            return  this.cost - e2.cost ;
        }
    }

    public static int connectCities (int cities[][]) {
        PriorityQueue <edge2> pq = new PriorityQueue<>() ;
        boolean vis[] = new boolean[cities.length] ;
        //source
        pq.add(new edge2(0, 0));
        int finalcost=  0; 
          
        while(! pq.isEmpty()){
            edge2 curr = pq.remove() ;
            //mark as visited
            if(! vis[curr.dest]){
                vis[curr.dest] = true ;
                finalcost+= curr.cost ;

                     //neighbour
                for(int i = 0; i<cities[curr.dest].length ;i++) {
                    if(cities[curr.dest][i] != 0){
                        pq.add(new edge2(i, cities[curr.dest][i])) ;
                    }
                } 
            }
        }
        return finalcost ;

}
       static int[][] floodfill (int image[][] ,int sr ,int sc , int color) {
         if(image[sr][sc] == color) {
            return  image ;
         }


         // vis array
         boolean vis[][] = new boolean[image.length][image[0].length];
         helper(image, sr, sc, vis, image[sr][sc], color);
         return image;
       }  

       static  void helper(int image[][] ,int sr ,int sc ,boolean  vis[][] ,int orgCol ,int col){
            

        //base case
         if(sr < 0 ||sc < 0 || sr >=image.length || sc >= image[0].length || vis[sr][sc] || image[sr][sc]  != orgCol)
         {return; }

        //work
         vis[sr][sc] = true ;
         image[sr][sc] = col ;


        //recursion

           //top
            helper(image, sr-1, sc, vis, orgCol, col);
           //bottom
            helper(image, sr+1, sc, vis, orgCol, col);
           //left
            helper(image, sr, sc-1, vis, orgCol, col);
           //right
            helper(image, sr, sc+1, vis, orgCol, col);
       }

}
