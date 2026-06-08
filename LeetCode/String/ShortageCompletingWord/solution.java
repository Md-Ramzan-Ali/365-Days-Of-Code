package String.ShortageCompletingWord;

class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int [] count = new int [26];
        for(char ch : licensePlate.toLowerCase().toCharArray()){
            if(Character.isLetter(ch)){
                count[ch-'a']++;
            }
        }
        String result = "";
        for(String word : words){
            if(!result.equals("") && word.length()>=result.length()){
                continue;
            }
            if(isCompleteWord(word, count)){
                result = word;
            }
        }
        return result;
    }
    private boolean isCompleteWord(String word, int[] targetCounts) {
        int[] wordCounts = new int[26];
        for (char c : word.toCharArray()) {
            wordCounts[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (wordCounts[i] < targetCounts[i]) {
                return false;
            }
        }
        return true;
    }
}
