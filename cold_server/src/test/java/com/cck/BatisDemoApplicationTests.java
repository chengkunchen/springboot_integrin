package com.cck;

import com.cck.common.Utils.JacksonUtil;
import com.cck.common.Utils.MD5Encode;
import com.cck.common.Utils.RandomUtils;
import com.cck.common.Utils.RedisUtil;
import com.cck.model.system.SysUsers;
import com.cck.service.system.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatisDemoApplicationTests {

    @Autowired
    private UserService userService;


    @Autowired

    RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }


    @Test
    public void addUser() throws UnsupportedEncodingException {
        SysUsers users = new SysUsers();
        users.setId(String.valueOf(System.currentTimeMillis()));
        users.setUser_id(RandomUtils.getRandomString(6));
        users.setEmail(RandomUtils.getRandomEmail());
        users.setName(RandomUtils.getRandomStringOnlyLetter(6));
        users.setPassword(MD5Encode.encryption("infopower168"));
        int inRe = userService.insertSelective(users);
    }


    @Test
    public void insertListUser() throws UnsupportedEncodingException {
        List<SysUsers> userList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            SysUsers users = new SysUsers();
            users.setId(String.valueOf(System.currentTimeMillis()) + i);
            users.setUser_id(RandomUtils.getRandomStringOnlyLetter(6) + i);
            users.setEmail(RandomUtils.getRandomEmail());
            users.setName(RandomUtils.getRandomStringOnlyLetter(6));
            // users.setMobile("136"+RandomUtils.getRandomInteger(8));
            users.setPassword(MD5Encode.encryption("infopower168"));
            userList.add(users);
        }

        int inRe = userService.insertBatch(userList);
    }

    @Test
    public void selectByPrimaryKey() {
        SysUsers users = userService.selectByPrimaryKey("15223148676960");
        System.out.print(users);
    }


    private void doTask(String material, Consumer<String> consumer) {
        consumer.accept(material);
    }

    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n))
                System.out.print(n + " ");
        }
    }

    public static void main(String[] args) {
        BatisDemoApplicationTests tests = new BatisDemoApplicationTests();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("Print all numbers:");

        evaluate(list, n -> n % 2 == 1);

        // cookingDemo.doTask("zane",n -> System.out.print("haha"));
        tests.doTask("肉食", material -> System.out.print(material));
      /*  cookingDemo.doTask("蔬菜", material -> System.out.println(material + "切片"));
        cookingDemo.doTask("食用油", material -> System.out.println(material + "烧热"));
        cookingDemo.doTask("", material -> System.out.println("炒菜"));*/
    }



    @Test
    public  void testRedis() throws IOException {

        SysUsers sysUsers = new SysUsers();

        sysUsers.setId("1");

        sysUsers.setName("cck");

        sysUsers.setPassword("sfsfksahfha");
        sysUsers.setMobile("173847398478");


     String xx =   JacksonUtil.ToJson(sysUsers);

        System.out.println(xx);


      //  redisUtil.setRedisTemplate(redisTemplate);


        redisUtil.set("sysUsers:hh:cck",sysUsers);

        redisUtil.expire("sysUsers:hh:cck",90);



      //  RedisUtil redisUtil = new RedisUtil(new RedisTemplate<>());


       // redisUtil.set("test",JacksonUtil.ToJson(sysUsers));
    }

}
