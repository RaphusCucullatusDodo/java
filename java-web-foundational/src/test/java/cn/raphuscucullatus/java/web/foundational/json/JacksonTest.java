package cn.raphuscucullatus.java.web.foundational.json;

import cn.raphuscucullatus.java.web.foundational.bean.entity.User;

import cn.raphuscucullatus.java.web.foundational.bean.response.ResponseBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Jackson的使用
 *
 * @author raphus cucullatus
 * @version 2021/8/2010:20
 * @since JDK8
 */
public class JacksonTest {
    /**
     * 将user对象转换为JSON字符串
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testUserToJSONString() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setName("raphuscucullatus");
        user.setPassword("1234");
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());

//        POJO --> JSON
        ObjectMapper objectMapper = new ObjectMapper();
//        注册Java8 LocalDateTime模块
        objectMapper.findAndRegisterModules();
        String userJSONString = objectMapper.writeValueAsString(user);
        System.out.println("user对象装换为JSON字符转结果:" + userJSONString);
    }

    /**
     * 将List集合装换为JSON字符串
     */
    @Test
    public void testUserListToJSONString() throws JsonProcessingException {
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

        //        POJOList --> JSON
        ObjectMapper objectMapper = new ObjectMapper();
//        注册Java8 LocalDateTime模块
        objectMapper.findAndRegisterModules();
        String userListJSONString = objectMapper.writeValueAsString(userArrayList);
        System.out.println("userList对象装换为JSON字符转结果:" + userListJSONString);
    }

    /**
     * 将ResponseBean装换为JSONString
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testResponseBean2JSONString() throws JsonProcessingException {
        ResponseBean<Boolean> responseBean = new ResponseBean<>();
        responseBean.setData(Boolean.TRUE);
        responseBean.setFlag(true);
        ObjectMapper objectMapper = new ObjectMapper();
//        注册Java8 LocalDateTime模块
        objectMapper.findAndRegisterModules();
        String responseBeanJSONString = objectMapper.writeValueAsString(responseBean);
        System.out.println("responseBean装换为JSONString:" + responseBeanJSONString);
    }

    /**
     * 将ResponseBean装换为JSONString
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testResponseBeanUser2JSONString() throws JsonProcessingException {
        ResponseBean<User> responseBeanUser = new ResponseBean<>(true);
        User user = new User();
        user.setId(1);
        user.setName("raphuscucullatus");
        user.setPassword("1234");
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        responseBeanUser.setData(user);

        ObjectMapper objectMapper = new ObjectMapper();
//        注册Java8 LocalDateTime模块
        objectMapper.findAndRegisterModules();
        String responseBeanJSONString = objectMapper.writeValueAsString(responseBeanUser);

        System.out.println("responseBeanUser装换为JSONString:" + responseBeanJSONString);
    }

    /**
     * 将ResponseBean装换为JSONString
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testResponseBeanUserList2JSONString() throws JsonProcessingException {
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
        ObjectMapper objectMapper = new ObjectMapper();
//        注册Java8 LocalDateTime模块
        objectMapper.findAndRegisterModules();
        String responseBeanJSONString = objectMapper.writeValueAsString(responseBeanUserList);
        System.out.println("responseBeanUserList装换为JSONString:" + responseBeanJSONString);
    }

    /**
     * 将JSONString转换为User对象
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testJSONString2User() throws JsonProcessingException {
        String JSONString = "{\"id\":1,\"name\":\"raphuscucullatus\",\"password\":\"1234\",\"createDate\":\"2021-08-20 21:10:41\",\"updateDate\":\"2021-08-20 21:10:41\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        User user = objectMapper.readValue(JSONString, User.class);
        System.out.println("JSONString装换为User对象:" + user);
    }

    /**
     * 将JSONString转换为List对象
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testJSONString2UserList() throws JsonProcessingException {
        String JSONString = "[{\"id\":1,\"name\":\"raphuscucullatus\",\"password\":\"1234\"" +
                ",\"createDate\":\"2021-08-20 21:10:41\",\"updateDate\":\"2021-08-20 21:10:41\"}" +
                ",{\"id\":2,\"name\":\"tom\",\"password\":\"123456\"" +
                ",\"createDate\":\"2021-08-20 21:10:41\",\"updateDate\":\"2021-08-20 21:10:41\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        List<User> userList = objectMapper.readValue(JSONString, new TypeReference<List<User>>() {
        });
        System.out.println("JSONString装换为List对象:" + userList);
    }

    /**
     * 将JSONString装换为ResponseBean
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testJSONString2ResponseBeanUserList() throws JsonProcessingException {
        String jsonString = "{\"flag\":true,\"data\":[{\"id\":1,\"name\":\"raphuscucullatus\"" +
                ",\"password\":\"1234\",\"createDate\":\"2021-08-20 21:10:41\"" +
                ",\"updateDate\":\"2021-08-20 21:10:41\"},{\"id\":2,\"name\":\"tom\"" +
                ",\"password\":\"123456\",\"createDate\":\"2021-08-20 21:10:41\"" +
                ",\"updateDate\":\"2021-08-20 21:10:41\"}],\"errorMessage\":null}";

        ObjectMapper objectMapper = new ObjectMapper();
//        注册Java8 LocalDateTime模块
        objectMapper.findAndRegisterModules();

        ResponseBean responseBean = objectMapper.readValue(jsonString, new TypeReference<ResponseBean<List<User>>>() {
        });

        System.out.println("responseBeanUserList装换为JSONString=" + responseBean);
        System.out.println("responseBean.getFlag()=" + responseBean.getFlag());
        System.out.println("responseBean.getData()=" + responseBean.getData());
        System.out.println("responseBean.getErrorMessage()=" + responseBean.getErrorMessage());
        List<User> responseBeanData = (List<User>) responseBean.getData();
        for (User user : responseBeanData) {
            System.out.println(user);
        }
    }

}
