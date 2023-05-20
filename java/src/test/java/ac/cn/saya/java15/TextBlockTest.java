package ac.cn.saya.java15;

/**
 * @author saya
 * @title: TextBlockTest
 * @projectName java-utils
 * @description: TODO
 * @date: 2023/5/20 11:33
 * @description:
 * 文本块（Text Blocks）
 * 文本块可以理解为对格式化字符串的一个优化操作，从Java15允许编写一个跨越多行的字符串作为常规文本。相
 *
 * 比于在Java15之前，跨多行需要使用”+”号进行多行字符串拼接，会更加的方便并且可读性更高。
 *
 */

public class TextBlockTest {

   public static void main(String[] args) {
      System.out.println(
              "<!DOCTYPE html>\n" +
                      "<html>\n" +
                      "     <head>\n" +
                      "        <title>Example</title>\n" +
                      "    </head>\n" +
                      "    <body>\n" +
                      "        <p>This is an example of a simple HTML " +
                      "		page with one paragraph.</p>\n" +
                      "    </body>\n" +
                      "</html>\n");

      System.out.println(
              """
              <!DOCTYPE html>
              <html>
                  <head>
                      <title>Example</title>
                  </head>
                  <body>
                      <p>This is an example of a simple HTML
                      page with one paragraph.</p>
                  </body>
              </html>      
              """
      );
   }

}
