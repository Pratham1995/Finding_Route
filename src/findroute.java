import java.io.*;
import java.util.*;

public class findroute{
	static List<String> set= new ArrayList<String>();//Arraylist to get input 
	static List<String> visited= new ArrayList<String>();//Arraylist to keep track of visisted list
	static List<String> via= new ArrayList<String>();////Arraylist to get route
	static List<String> heuristic= new ArrayList<String>();
	static Boolean ifheur =false;//to check heuristic needed or not
	static int sum=0,destcost=0,newcost=0;
	static int v=0,k,reached=0,rh=0;
	static String start,dest;
	static int m=0;
	
	//Class inputnode contains the node, parentnode and distance and uses 
	static class inputnode implements Comparable{
		String city1;
		String city2;
		Integer dist;
		inputnode next;
		
		inputnode(String pl1,String pl2,Integer d){
			this.city1=pl1;
			this.city2=pl2;
			this.dist=d;
		}
		public Integer getdistance(){
			return dist;
		}		
		@Override
		public int compareTo(Object node){
				return this.getdistance().compareTo(((inputnode) node).getdistance());
		}
		
	}

	
	static LinkedList <inputnode> fringes=new LinkedList<inputnode>();//ArrayList to get the fringes
	static LinkedList <inputnode> closed=new LinkedList<inputnode>();//Arraylist to get the nodes which are closed and its successors are in finges
	
	//Finding the route from source to destination with distance
	public static void route(String nod,String parentnod,int dist){
		//paths and nodes are stored in Arraylist via
		if(!ifheur){
			for(int p=0;p<closed.size();p++){
				if(closed.get(p).city1.equals(nod)){
					newcost=destcost-closed.get(p).dist;
					if(closed.get(p).city2.equals(start)){
						via.add(closed.get(p).city2);
						via.add(closed.get(p).city1);
						via.add(Integer.toString(newcost));		
						//System.out.println(closed.get(p).place2+"\tto\t"+closed.get(p).place1+","+closed.get(p).dist);
						return;
					}
					else{
						via.add(closed.get(p).city2);
						via.add(closed.get(p).city1);		
						via.add(Integer.toString(newcost));
						//System.out.println(closed.get(p).place2+"\tto\t"+closed.get(p).place1+","+closed.get(p).dist);
						route(closed.get(p).city2,closed.get(p).city1,newcost);
					}

				}
			}
		}
		//for the heuristic 
		else{
			for(int p=0;p<closed.size();p++){
				if(closed.get(p).city1.equals(nod)){
					//The heuristic of nodes in the route are calculated and subtracted
					String heur=heuristic.get((heuristic.indexOf(closed.get(p).city1)+1));
					newcost=destcost-closed.get(p).dist-Integer.parseInt(heur);
					if(closed.get(p).city2.equals(start)){
						via.add(closed.get(p).city2);
						via.add(closed.get(p).city1);
						via.add(Integer.toString(newcost));		
						return;
					}
					else{
						via.add(closed.get(p).city2);
						via.add(closed.get(p).city1);		
						via.add(Integer.toString(newcost));
						route(closed.get(p).city2,closed.get(p).city1,newcost);
					}

				}
			}	
		}			
	}

	//Uninformed search is implemented using uniform cost search technique.
	//Elements are added to the fringes list.
	
	public static void addlist(int m){
		int flag=0;
		//Uninformed Search (Uniform Cost search technique)
		if(!ifheur){
			//checking if first node is destination or not
			//else expanding it to reach the destination
			if(!fringes.get(0).city1.equals(dest)){
				for(int n=0;n<set.size();n+=3){
					int ts=0;
					//For the first iteration with start as fringes first element
					if(m==0){
						//Each source destination names in the input file is considered bidirectional.
						if(start.equals(set.get(n))){
							fringes.add(new inputnode(set.get(n+1),set.get(n),Integer.parseInt(set.get(n+2))));
							if(set.get(n+1).equals(dest)){
								reached=1;
								rh++;
							}
						}
						if(start.equals(set.get(k))){
							fringes.add(new inputnode(set.get(k-1),set.get(k),Integer.parseInt(set.get(k+1))));
							if(set.get(k-1).equals(dest)){
								reached=1;
								rh++;
							}
						}
					k+=3;	
					}
					else{
						//Each source destination names in the input file is considered bidirectional.
						if(set.get(n).equals(fringes.get(0).city1)){
							if(!visited.contains(set.get(n+1))){
								//Cumulative sum is calculated and fed to the fringe 
								ts=Integer.parseInt(set.get(n+2))+fringes.get(0).dist;
								fringes.add(new inputnode(set.get(n+1),set.get(n),ts));
								if(set.get(n+1).equals(dest)){
									reached=1;
									rh++;
								}
							}	
						}
						if(set.get(k).equals(fringes.get(0).city1)){
							if(!visited.contains(set.get(k-1))){
								//Cumulative sum is calculated and fed to the fringe 
								ts=Integer.parseInt(set.get(k+1))+fringes.get(0).dist;
								fringes.add(new inputnode(set.get(k-1),set.get(k),ts));
								if(set.get(k-1).equals(dest)){
									reached=1;
									rh++;
								}
							}
						}	
					k+=3;
					}
				}
				//Nodes are added to the fringe arraylist
				if(!visited.contains(fringes.get(0).city1)){
					visited.add(fringes.get(0).city1);
					flag=1;
				}
			}
			else{
				flag=1;
			}
			//nodes are added to closedlist
			if(flag==1 || m==0){
				
				closed.add(new inputnode(fringes.get(0).city1,fringes.get(0).city2,fringes.get(0).dist));
			}
		}
	
		else{
			//Checking if the first element of the fringe is destination or not, if yes not expanding.
			//Else expanding the nodes accessible from the node and adding to the fringes list
			if(!fringes.get(0).city1.equals(dest)){
				for(int n=0;n<set.size();n+=3){
					int ts=0;
					//For the first iteration with start as fringes first element
					if(m==0){
						//Each source destination names in the input file is considered bidirectional.
						if(start.equals(set.get(n))){
							//The heuristic of nodes in the route are calculated and added to cumulative sum.
							String heur=heuristic.get((heuristic.indexOf(set.get(n)))+1);
							ts=Integer.parseInt(set.get(n+2))+Integer.parseInt(heur);
							fringes.add(new inputnode(set.get(n+1),set.get(n),Integer.parseInt(set.get(n+2))));
							if(set.get(n+1).equals(dest)){
								reached=1;
								rh++;
							}
						}
						if(start.equals(set.get(k))){
							//The heuristic of nodes in the route are calculated and added to cumulative sum.
							String heur=heuristic.get((heuristic.indexOf(set.get(k)))+1);
							ts=Integer.parseInt(set.get(n+2))+Integer.parseInt(heur);
							fringes.add(new inputnode(set.get(k-1),set.get(k),Integer.parseInt(set.get(k+1))));
							if(set.get(k-1).equals(dest)){
								reached=1;
								rh++;
							}
						}
					k+=3;	
					}
					else{
						//Each source destination names in the input file is considered bidirectional.
						if(set.get(n).equals(fringes.get(0).city1)){
							if(!visited.contains(set.get(n+1))){
								//The heuristic of nodes in the route are calculated and added to cumulative sum.
								String heur=heuristic.get((heuristic.indexOf(set.get(n)))+1);
								ts=Integer.parseInt(set.get(n+2))+fringes.get(0).dist+Integer.parseInt(heur);
								fringes.add(new inputnode(set.get(n+1),set.get(n),ts));
								if(set.get(n+1).equals(dest)){
									reached=1;
									rh++;
								}
							}	
						}
						if(set.get(k).equals(fringes.get(0).city1)){
							if(!visited.contains(set.get(k-1))){
								//The heuristic of nodes in the route are calculated and added to cumulative sum.
								String heur=heuristic.get((heuristic.indexOf(set.get(k)))+1);
								ts=Integer.parseInt(set.get(k+1))+fringes.get(0).dist+Integer.parseInt(heur);;
								fringes.add(new inputnode(set.get(k-1),set.get(k),ts));
								if(set.get(k-1).equals(dest)){
									reached=1;
									rh++;
								}						
							}
						}	
					k+=3;
					}
				}
				//Elements expanded are added to the visited list
				if(!visited.contains(fringes.get(0).city1)){
					visited.add(fringes.get(0).city1);
					flag=1;
				}
			}
			else{
				flag=1;
			}
			//Elements added to the closed list
			if(flag==1 || m==0){
				
				closed.add(new inputnode(fringes.get(0).city1,fringes.get(0).city2,fringes.get(0).dist));
			}

		}
	}
	public static void main(String[] args) {
		if(args[0]!=null && args[1]!=null &&args[2]!=null&&args[3]!=null)
			{
				try{
					
					BufferedReader bf=new BufferedReader(new FileReader(args[1]));
					String line=new String();
					String arr[]=new String[3];
					
					
					while((line=bf.readLine())!=null){
						if(!line.equals("END OF INPUT")){
							arr=line.split(" ");
							if(arr.length-1>0){
								set.add(arr[0]);
								set.add(arr[1]);
								set.add(arr[2]);
							}
						}
					}
					//For the heuristic
					if(args[0].equals("inf") && args[4]!=null){
						
						BufferedReader hbf=new BufferedReader(new FileReader(args[4]));
						String h=new String();
						String harr[]=new String[2];
						while((h=hbf.readLine())!=null){
							if(!h.equals("END OF INPUT")){
							harr=h.split(" ");
							if(harr.length-1>0) {
							heuristic.add(harr[0]);
							heuristic.add(harr[1]);
							}}
						}
						ifheur=true;
					}
					else if(!args[0].equals("uninf")){
						return;
					}
					start=args[2];
					dest=args[3];
					if(set.contains(start) &&set.contains(dest)){
						//storing the starting place and distance to the fringe
						//Add the start to the fringes linkedlist.
						fringes.add(new inputnode(start,start,0));
					} 
					//If start or destination not found in the input list, no route 
					else
					{	System.out.println("Distance:Infinity");
						System.out.println("Nodes Expanded"+" "+visited.size());
						
					    System.out.println("Route:");
						System.out.println("NO ROUTE\n");
						
						return ;
					}
					int ss;
					//Each source destination names in the input file is considered bidirectional.
					for(m=0;m<set.size()/2;m++){
						k=1; 
						//Calling the addlist method, expanding the nodes
						addlist(m);
						//If first value of the fringes is destination break out of the loop
						if(fringes.get(0).city1.equals(dest)){
							break;
						}
						//First element of the fringes is removed for the next iteration
						fringes.remove(0);
						if(fringes.size()==0){
							break;
						}
						//Sorting the fringes list, by overriding compareTo method 
						Collections.sort(fringes);
					}
					//If the destination reached ,rh flag will be more than rh
					//Display the route,including the distance between nodes in the path
					if(rh>0){
						//If uninformed search, displaying the route from the closed list
						if(!ifheur){
							for(int p=0;p<closed.size();p++){
								if(closed.get(p).city1.equals(dest)){
									destcost=closed.get(p).dist;
									System.out.println("distance:"+closed.get(p).dist+"km");
									System.out.println("route:");
									route(dest,closed.get(p).city2,closed.get(p).dist);
								
									int vc=0,vsum=0;
									//Vl is the number of node-node pairs in the path from start to destination
									//via list stores the route data from start to destination(node,node,distance)
									int vl=via.size()/3;
									Integer nodedistance[]=new Integer[vl];
									int nodedistan=0;
									//Calculating and displaying the route distances between nodes
									for(int ind=via.size();ind>0;ind-=3){ 
										vsum=0;
										
										for(int i=0;i<vc;i++){
											vsum+=nodedistance[i];
										}
										//Calculating each node-node distance form the total destination distance and the sum calculated
										for(int t=vc;t<=vc;t++){
											nodedistance[t]=destcost-(Integer.parseInt(via.get(ind-1))+vsum);
											nodedistan=nodedistance[t];
										}
										vc++;
										//Displaying the route and distance
										System.out.println(via.get(ind-3)+"\tto\t"+via.get(ind-2)+","+nodedistan+"km");
										}
									System.out.println("Nodes Expanded"+via.size()*vl);
										break;
									}
								}
						}
						//If informed search, displaying the route from the closed list
						else{
							for(int p=0;p<closed.size();p++){
								if(closed.get(p).city1.equals(dest)){
									destcost=closed.get(p).dist;
									//Finding the route, calling route method
									route(dest,closed.get(p).city2,closed.get(p).dist);
									int vc=0,vsum=0;
									//Vl is the number of node-node pairs in the path from start to destination
									//via list stores the route data from start to destination(node,node,distance)
									int vl=via.size()/3;
									int h_sum=0;
									//Calculating the heuristic values of the nodes in the path from start to dest, stored in via list
									for(int ind=via.size();ind>0;ind-=3){
										h_sum=h_sum+Integer.parseInt(heuristic.get((heuristic.indexOf(via.get(ind-2)))+1));
									}
									System.out.println("distance:"+(destcost-h_sum)+"km");
									System.out.println("route:");
									Integer nodedistance[]=new Integer[vl];
									int nodedistan=0;
									//Calculating and displaying the route distances between nodes
									for(int ind=via.size();ind>0;ind-=3){ 
										vsum=0;
										for(int i=0;i<vc;i++){
											vsum+=nodedistance[i];
										}
										//Calculating each node-node distance form the total destination distance and the sum calculated
										for(int t=vc;t<=vc;t++){
											nodedistance[t]=destcost-(Integer.parseInt(via.get(ind-1))+vsum);
											nodedistan=nodedistance[t];
										}
										vc++;
									
										//Displaying the route and distance(subtracting the heuristic sum of the each node)
										System.out.println(via.get(ind-3)+"\tto\t"+via.get(ind-2)+","+(nodedistan-Integer.parseInt(heuristic.get((heuristic.indexOf(via.get(ind-2)))+1)))+"km");
										
									}
									System.out.println("Nodes Expanded"+ " " +via.size()/vl);
									break;
								
							}	
						}
						}}
					//if destination not reached, display message
					else{
						System.out.println("Distance: \tinfinty");
						
						System.out.println("Route:");
						System.out.println("none");
						return;
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}

			}	
		else{
			return;
		}
	}

}