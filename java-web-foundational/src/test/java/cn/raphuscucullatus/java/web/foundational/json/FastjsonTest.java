package cn.raphuscucullatus.java.web.foundational.json;

import cn.raphuscucullatus.java.web.foundational.bean.entity.User;
import cn.raphuscucullatus.java.web.foundational.bean.response.ResponseBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Fastjson
 *
 * @author raphus cucullatus
 * @version 2021/8/2020:34
 * @since JDK8
 */
public class FastjsonTest {
    /**
     * 将user对象转换为JSON字符串
     */
    @Test
    public void testUserToJSONString() {
        User user = new User();
        user.setId(1);
        user.setName("raphuscucullatus");
        user.setPassword("1234");
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());

        String userJSONString = JSON.toJSONString(user, SerializerFeature.WriteDateUseDateFormat);
        System.out.println("user对象装换为JSON字符转结果:" + userJSONString);

    }

    /**
     * 将List集合装换为JSON字符串
     */
    @Test
    public void testUserListToJSONString() {
        User tom = new User();
        tom.setId(1);
        tom.setName("raphuscucullatus");
        tom.setPassword("1234");
        tom.setCreateDate(LocalDateTime.now());
        tom.setUpdateDate(LocalDateTime.now());
        User user = new User();
        user.setId(2);
        user.setName("tom");
        user.setPassword("123456");
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(tom);
        userArrayList.add(user);

        String userListJSONString = JSON.toJSONString(userArrayList, SerializerFeature.WriteDateUseDateFormat);
        System.out.println("userList对象装换为JSON字符转结果:" + userListJSONString);
    }

    /**
     * 将ResponseBean装换为JSONString
     */
    @Test
    public void testResponseBean2JSONString() {
        ResponseBean<Boolean> responseBean = new ResponseBean<>();
        responseBean.setData(Boolean.TRUE);
        responseBean.setFlag(true);

        String responseBeanJSONString = JSON.toJSONString(responseBean);

        System.out.println("responseBean装换为JSONString:" + responseBeanJSONString);
    }

    /**
     * 将ResponseBean装换为JSONString
     */
    @Test
    public void testResponseBeanUser2JSONString() {
        ResponseBean<User> responseBeanUser = new ResponseBean<>(true);
        User user = new User();
        user.setId(1);
        user.setName("raphuscucullatus");
        user.setPassword("1234");
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        responseBeanUser.setData(user);

        String responseBeanJSONString = JSON.toJSONString(responseBeanUser, SerializerFeature.WriteDateUseDateFormat);

        System.out.println("responseBeanUser装换为JSONString:" + responseBeanJSONString);
    }

    /**
     * 将ResponseBean装换为JSONString
     */
    @Test
    public void testResponseBeanUserList2JSONString() {
        ResponseBean<ArrayList<User>> responseBeanUserList = new ResponseBean<>(true);
        User tom = new User();
        tom.setId(1);
        tom.setName("raphuscucullatus");
        tom.setPassword("1234");
        tom.setCreateDate(LocalDateTime.now());
        tom.setUpdateDate(LocalDateTime.now());
        User user = new User();
        user.setId(2);
        user.setName("tom");
        user.setPassword("123456");
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        ArrayList<User> userArrayList = new ArrayList<>();
        userArrayList.add(tom);
        userArrayList.add(user);
        responseBeanUserList.setData(userArrayList);

        String responseBeanJSONString = JSON.toJSONString(userArrayList, SerializerFeature.WriteDateUseDateFormat);

        System.out.println("responseBeanUserList装换为JSONString:" + responseBeanJSONString);
    }

    /**
     * 将JSONString转换为User对象
     */
    @Test
    public void testJSONString2User() {
        String JSONString = "{\"createDate\":\"2021-08-20 21:10:41\",\"id\":1,\"name\":\"raphuscucullatus\",\"password\":\"1234\",\"updateDate\":\"2021-08-20 21:10:41\"}";
        User user = JSON.parseObject(JSONString, User.class);
        System.out.println("JSONString装换为User对象:" + user);
    }

    /**
     * 将JSONString转换为List对象
     */
    @Test
    public void testJSONString2UserList() {
        String JSONString = "[{\"id\":1,\"name\":\"raphuscucullatus\",\"password\":\"1234\"" +
                ",\"createDate\":\"2021-08-20 21:10:41\",\"updateDate\":\"2021-08-20 21:10:41\"}" +
                ",{\"id\":2,\"name\":\"tom\",\"password\":\"123456\"" +
                ",\"createDate\":\"2021-08-20 21:10:41\",\"updateDate\":\"2021-08-20 21:10:41\"}]";

        List<User> userList = JSON.parseArray(JSONString, User.class);
        System.out.println("JSONString装换为List对象:" + userList);
    }

    /**
     * 将JSONString装换为ResponseBean
     */
    @Test
    public void testJSONString2ResponseBeanUserList() {
        String jsonString = "{\"flag\":true,\"data\":[{\"id\":1,\"name\":\"raphuscucullatus\"" +
                ",\"password\":\"1234\",\"createDate\":\"2021-08-20 21:10:41\"" +
                ",\"updateDate\":\"2021-08-20 21:10:41\"},{\"id\":2,\"name\":\"tom\"" +
                ",\"password\":\"123456\",\"createDate\":\"2021-08-20 21:10:41\"" +
                ",\"updateDate\":\"2021-08-20 21:10:41\"}],\"errorMessage\":null}";

        ResponseBean<List<User>> responseBean = JSON.parseObject(jsonString, new TypeReference<ResponseBean<List<User>>>() {
        }, Feature.AllowISO8601DateFormat);
        System.out.println("responseBeanUserList装换为JSONString=" + responseBean);
    }


}
