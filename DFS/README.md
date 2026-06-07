# 📌 Depth-First Search (DFS) Implementation in C++

এই প্রোজেক্টে সি++ (C++) ল্যাঙ্গুয়েজ এবং স্ট্যান্ডার্ড স্ট্যাক (`std::stack`) ব্যবহার করে **Depth-First Search (DFS)** অ্যালগরিদম ইমপ্লিমেন্ট করা হয়েছে। এখানে গ্রাফের ডেটা স্টোর করার জন্য কোনো এক্সট্রা ডাইনামিক লাইব্রেরি (যেমন `vector`) ব্যবহার না করে, ট্র্যাডিশনাল **Adjacency Matrix** (২ডি অ্যারে) ব্যবহার করে সম্পূর্ণ ইউজারের ইনপুট অনুযায়ী গ্রাফ তৈরি করা হয়েছে।

## 🚀 অ্যালগরিদম কীভাবে কাজ করে?
DFS মূলত গ্রাফের গভীরতা (Depth) অনুযায়ী কাজ করে। একটি সোর্স নোড থেকে শুরু করে এটি যেকোনো একটি পথ ধরে একদম শেষ মাথা (Dead-end) পর্যন্ত যায়, তারপর ব্যাকট্র্যাক (Backtrack) করে অন্য প্রতিবেশীদের ভিজিট করে।

এই কোডে:
1. ইউজার থেকে মোট নোড এবং এজের সংখ্যা ইনপুট নেওয়া হয়।
2. প্রতি জোড়া এজের সংযোগ অনুসারে **Undirected Graph** তৈরি করা হয়।
3. একটি **Stack (LIFO)** ব্যবহার করে স্তরভিত্তিক না গিয়ে সরাসরি গভীরতম নোডগুলো আগে ভিজিট করা হয়।
4. কোনো নোড যেন একাধিকবার প্রসেস না হয়, সেজন্য একটি `isVisited[]` ট্র্যাকিং অ্যারে ব্যবহার করা হয়েছে।

---

## 💻 কোডের প্রধান লজিক (Snippet)

```cpp
void dfs(int graph[max][max], int nodes, int startNode){
    bool isVisited[max] = {false};
    stack<int> st;
    st.push(startNode);
    
    while(!st.empty()){
        int currentNode = st.top();
        st.pop();
        
        if(!isVisited[currentNode]){
            cout << currentNode << " ";
            isVisited[currentNode] = true;
        }
        
        for(int i = 0; i <= nodes-1; i++){ 
            if(graph[currentNode][i] == 1 && !isVisited[i]){
                st.push(i);
            }
        }
    }
}