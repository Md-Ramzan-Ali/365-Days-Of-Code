# 🔠 Word Weights Mapper

## 📝 Problem Description

You are given an array of strings `words`, where each string represents a word containing lowercase English letters.
You are also given an integer array `weights` of length 26, where `weights[i]` represents the weight of the $i$-th lowercase English letter.

The weight of a word is defined as the sum of the weights of its characters.

### Rules:
1. For each word, take its total weight modulo 26 (`% 26`).
2. Map the result to a lowercase English letter using **reverse alphabetical order**:
    - $0 \rightarrow \text{'z'}$
    - $1 \rightarrow \text{'y'}$
    - $2 \rightarrow \text{'x'}$
    - $\dots$
    - $25 \rightarrow \text{'a'}$
3. Return a string formed by concatenating the mapped characters for all words in order.

---

## 🚀 Examples

### **Example 1:**
- **Input:**
    - `words` = `["abcd","def","xyz"]`
    - `weights` = `[5,3,12,14,1,2,3,2,10,6,6,9,7,8,7,10,8,9,6,9,9,8,3,7,7,2]`
- **Output:** `"rij"`
- **Explanation:**
    - The weight of `"abcd"` is $5 + 3 + 12 + 14 = 34$. The result modulo 26 is $34 \% 26 = 8$, which maps to `'r'`.
    - The weight of `"def"` is $14 + 1 + 2 = 17$. The result modulo 26 is $17 \% 26 = 17$, which maps to `'i'`.
    - The weight of `"xyz"` is $7 + 7 + 2 = 16$. The result modulo 26 is $16 \% 26 = 16$, which maps to `'j'`.
    - Combining them gives `"rij"`.

### **Example 2:**
- **Input:**
    - `words` = `["a","b","c"]`
    - `weights` = `[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]`
- **Output:** `"yyy"`

---

## 🛠️ Solution (Java)

This solution utilizes a **HashMap** for $O(1)$ weight lookups and a **StringBuilder** for efficient string accumulation.

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        // 1. Map each character to its respective weight
        Map<Character, Integer> map = new HashMap<>(26);
        for(int i = 0; i < 26; i++){
            char ch = (char) (97 + i); // 97 is the ASCII value of 'a'
            map.put(ch, weights[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        
        // 2. Process each word
        for(String str : words){
            int sum = 0;
            for(int i = 0; i < str.length(); i++){
                int num = map.get(str.charAt(i)); // Retrieve character weight
                sum += num;
            }
            
            // 3. Apply modulo and perform reverse alphabetical mapping
            int rem = sum % 26;
            char ch2 = (char)(122 - rem); // 122 is the ASCII value of 'z'
            
            sb.append(ch2);
        }
        
        return sb.toString();
    }
}