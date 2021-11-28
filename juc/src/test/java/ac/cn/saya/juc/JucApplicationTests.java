package ac.cn.saya.juc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class JucApplicationTests {

    @Test
    public void contextLoads() {
    }

    public void printA(String a){
        a = "6666";
    }

    @Test
    public void main(){
        String a = "abc";
        printA(a);
        System.out.println(a);
    }

}
