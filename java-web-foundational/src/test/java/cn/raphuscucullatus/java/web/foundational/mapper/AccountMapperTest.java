package cn.raphuscucullatus.java.web.foundational.mapper;

import cn.raphuscucullatus.java.web.foundational.bean.entity.Account;
import cn.raphuscucullatus.java.web.foundational.bean.vo.QueryVO;
import cn.raphuscucullatus.java.web.foundational.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 账号管理-MyBatis实现的测试用例
 *
 * @author raphus cucullatus
 * @version 2021/8/3016:07
 * @since JDK8
 */
public class AccountMapperTest {
//    /**
//     * SqlSession在initSqlSession中初始化
//     */
//    SqlSession sqlSession=SqlSessionUtil.getSqlSession();

//    @BeforeMethod
//    public void initSqlSession() {
//        sqlSession = SqlSessionUtil.getSqlSession();
//    }

    @Test
    public void testAccountFindById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.selectById(1L);
        System.out.println("根据账号id查找账号:" + account);
        SqlSessionUtil.commitAndClose(sqlSession);
    }

    @Test
    public void testAccountFindById2() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        AccountMapper2 accountMapper2 = sqlSession.getMapper(AccountMapper2.class);
        Account account = accountMapper2.findById(1L);
        System.out.println("根据账号id查找账号:" + account);

        SqlSessionUtil.commitAndClose(sqlSession);
    }

    @Test
    public void testAccountInsert() {

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = new Account("唐三藏", new BigDecimal(10000), 1);
        account.setUserId(1L);
        int insertResult = accountMapper.insert(account);
        System.out.println("获取新增账号的id:" + account.getId());
        Assert.assertEquals(insertResult, 1);
        SqlSessionUtil.commitAndClose(sqlSession);

    }

    @Test
    public void testAccountUpdate() throws IOException {
        //注册驱动等步骤
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        //类似于获得连接
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //动态代理
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = new Account(13L, "唐玄奘", new BigDecimal(1320000), 0);
        int update = accountMapper.update(account);
        //        提交事务(MyBatis增删改都是手动提交事务)
        sqlSession.commit();
        Assert.assertEquals(update, 1);
    }

    @Test
    public void testAccountDeleteById() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        //类似于获得连接
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        int deleteByIdResult = mapper.deleteById(6L);
        Assert.assertEquals(deleteByIdResult, 1);
        sqlSession.commit();

    }

    @Test
    public void testSelectAccountByPage() {
        Map<String, Long> map = new HashMap<>();
        Long pageNo = 1L;
        Long pageSize = 2L;
        Long startIndex = (pageNo - 1) * pageSize;
        map.put("startIndex", startIndex);
        map.put("pageSize", pageSize);
        map.put("status", 0L);

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accountList = mapper.selectAccountByPage(map);

        for (Account account : accountList) {
            System.out.println("分页账号列表:" + account);

        }

        SqlSessionUtil.commitAndClose(sqlSession);

    }

    @Test
    public void testSelectAccountByQueryVO() {
        QueryVO queryVO = new QueryVO<Account>();
        Account accountCondition = new Account();
        accountCondition.setStatus(0);
        queryVO.setCondition(accountCondition);
        queryVO.setPageNo(1L);
        queryVO.setPageSize(2L);

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        //动态代理生成accountMapper实现类
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accountList = mapper.selectAccountByQueryVO(queryVO);

        for (Account account : accountList) {
            System.out.println("分页账号列表:" + account);
        }

        SqlSessionUtil.commitAndClose(sqlSession);

    }

    @Test
    public void testSelectAccountListByStatus() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accountList = mapper.selectAccountListByStatus(null);
        System.out.println("满足条件的account数量" + accountList.size());

        SqlSessionUtil.commitAndClose(sqlSession);


    }

    @Test
    public void testSelectAccountListByStatusAndName() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        Account account = new Account("猪八戒", 0);
//        account = new Account("猪八戒");
//        account = new Account(0);
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accountList = mapper.selectAccountListByStatusAndName(account);
        System.out.println("根据姓名与状态查询账号列表:" + accountList);

        SqlSessionUtil.commitAndClose(sqlSession);
    }

    @Test
    public void testAccountDeleteByIds() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
/*        参数为 List<Long>时
        int i = mapper.deleteByIds(Arrays.asList(30L, 31L, 32L));
        int i = mapper.deleteByIds();          */
        int i = mapper.deleteByIds(43L, 46L);
        System.out.println("受影响行数:" + i);
        SqlSessionUtil.commitAndClose(sqlSession);

    }

    @Test
    public void testSelectAccountUserInfo() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        Account account = accountMapper.selectAccountUserInfo(7L);
        System.out.println("查询到指定账号信息为:" + account);
    }

    @Test
    public void testSelect() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        List<Account> accountList = accountMapper.selectAccountListByUserId(1L);
        System.out.println(accountList);
    }

}
