package joce.practice.algo;

public class LongesSubStr {
    public static void main(String[] args) {
        int len = lengthOfLongestSubstring("  a");
        System.out.println(len);

    }

    /**
     * 2019.11.19 right answer but not quite good
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() ==0){
            return 0;
        }
//        String str = s.replaceAll("\\s*","");
//        if(str.length()==0){
//            return 0;
//        }

        char[] chars = s.toCharArray();
        int result = 1;
        int length = chars.length;

       f: for (int start = 0; start < length - 1; start++) {
          s:   for (int end = start + 1; end < length; end++) {
                char charEnd = chars[end];
                for (int n = start; n < end; n++) {
                    if (chars[n] == charEnd) {
                        if ((n - start+1) > result) {
                            result = (n - start+1);
                        }
                        continue f;
                    }
                }
                if((end-start+1)>result){
                    result = end - start+1;
                }

            }
        }
        return result;
    }
}
