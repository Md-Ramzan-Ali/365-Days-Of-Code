#include<iostream>
#include<queue>
#define max 100
using namespace std;

void bfs(int graph[max][max], int nodes, int startNode){
    bool isVisited[max] = {false};
    queue<int> q;
    q.push(startNode);
    isVisited[startNode] = true;

    while(!q.empty()){
        int currentNode = q.front();
        q.pop();
        cout<<currentNode<<" ";

        for(int i = 0; i< nodes; i++){
            if( !isVisited[i] && graph[currentNode][i] == 1){
                q.push(i);
                isVisited[i]= true;
            }
        }
    }
    cout<<"END"<<endl;
}

int main(){
    int graph[max][max] = {0};
    int startNode, nodes, edges;

    cout<<"Enter total number of nodes & edges : ";
    cin>>nodes>>edges;

    for(int i = 0; i< edges ; i++) {
        int u, v;
        cout<<"Enter the starting point and ending point : ";
        cin>>u>>v;
        graph[u][v] = 1;

    }
    cout<<"Enter the starting point : ";
    cin>>startNode;

    bfs(graph , nodes, startNode);
}