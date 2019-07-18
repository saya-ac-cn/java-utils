package ac.cn.saya.prototype.deepclone;

/**
 * @Title: Client
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-16 22:56
 * @Description:
 */

public class Client {

    public static void main(String[] args) {
        try {
            SheepEntity sheepEntity1 = new SheepEntity();
            sheepEntity1.setName("name1");
            sheepEntity1.setType(new TypeEntity(120));

//            SheepEntity sheepEntity2 = (SheepEntity)sheepEntity1.clone();
//            System.out.println("sheepEntity1.getType()"+sheepEntity1.getType());
//            System.out.println("sheepEntity2.getType()"+sheepEntity2.getType());
            SheepEntity sheepEntity2 = (SheepEntity)sheepEntity1.deepClone();
            System.out.println("sheepEntity1.getType()"+sheepEntity1.getType());
            System.out.println("sheepEntity2.getType()"+sheepEntity2.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
