/**
 * <p>Title: Test</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-20 14:33
 */
public class Test {
    public static void main(String[] args) {
        String str="/organization/treeGrid";
       int index=  str.lastIndexOf("/");
        System.out.println(str.substring(0,index)+":"+str.substring(index+1));
    }
}
