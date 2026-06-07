#include<iostream>
#include<stack>

using namespace std;
#define max 100

void dfs(int graph[max][max], int nodes, int startNode){
    bool isVisited[max] = {false};
    stack<int> st ;
    st.push(startNode);
    while(!st.empty()){
        int currentNode = st.top();
        st.pop();
        if(!isVisited[currentNode]){
            cout<<currentNode<<"->";
            isVisited[currentNode] = true;
        }
        for(int i = 0; i<= nodes-1; i++){
            if(graph[currentNode][i] == 1 && !isVisited[i]){
                st.push(i);
            }
        }
    }

}
int main(){
    int nodes, edges, startNode;
    int graph[max][max]={0};
    cout<< "Enter total number of nodes & edges : ";
    cin>>nodes>>edges;

    for(int i = 0; i< edges ; i++){
        int u, v;
        cout<<"Enter starting and ending point : ";
        cin>>u>>v;
        graph[u][v] = 1;
        graph[v][u] = 1;
    }
    cout<<"Enter the starting point : ";
    cin>>startNode;

    dfs(graph,nodes, startNode);
}
