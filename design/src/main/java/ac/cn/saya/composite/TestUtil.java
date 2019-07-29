package ac.cn.saya.composite;

/**
 * @Title: TestUtil
 * @ProjectName java-utils
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-07-24 21:34
 * @Description:
 */

public class TestUtil {

    public static void main(String[] args) {
        OrganizationComponent university = new University("xx大学", "C7");

        OrganizationComponent college1 = new College("医学院","国家级重点");
        OrganizationComponent college2 = new College("计算机与信息工程学院","省级重点");

        college1.add(new Department("临床医学", "国家级重点"));
        college1.add(new Department("口腔医学", "国家级重点"));

        college2.add(new Department("计算机科学与技术", "省级重点"));
        college2.add(new Department("软件工程", "校级重点"));

        university.add(college1);
        university.add(college2);

        university.print();
    }

}
