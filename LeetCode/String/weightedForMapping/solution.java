package String.weightedForMapping;

class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        Map<Character, Integer> map = new HashMap<>(weights.length);
        for(int i = 0; i< 26 ; i++){
            char ch = (char) (97+i);
            map.put(ch,weights[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(String str : words){
            int sum = 0;
            for(int i = 0; i<str.length() ; i++){
                int num = map.get(str.charAt(i));
                sum += num;
            }
            int rem = sum%26;
            char ch2 = (char)(122-rem);
            sb.append(ch2+"");
        }
        return sb.toString();
    }
}
