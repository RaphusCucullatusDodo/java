package cn.raphuscucullatus.java.web.foundational;

//import org.jcp.xml.dsig.internal.SignerOutputStream;


/**
 * @author raphuscucullatus 993275475@qq.com
 * @version 2021/7/2621:32
 * @since JDK8
 */
public class StringTest {
    public static void main(String[] args) {

        String dataBaseColumnName = "id";
        String[] columnNameSplit = dataBaseColumnName.split("_");
        String columnName = columnNameSplit[0];
        for (int i = 1; i < columnNameSplit.length; i++) {
//            sql命名规范 -> java命名规范
            columnName = columnName.concat(Character.toString(columnNameSplit[i].charAt(0)).toUpperCase()).
                    concat(columnNameSplit[i].substring(1));
        }
//        方法名 -> 属性名
        String methodName = "setId";
        String substring = methodName.substring(4);
        String fieldName = Character.toString(methodName.charAt(3)).toLowerCase().concat(substring);
        System.out.println(fieldName.equals(columnName));


    }

    /**
     * 比较 属性 与 列名 是否相等
     *
     * @param dataBaseColumnName 列名
     * @param methodName         方法名
     * @return
     */
    private boolean compareDataBaseColumnNameFieldName(String dataBaseColumnName, String methodName) {
        String[] columnNameSplit = dataBaseColumnName.split("_");
        String columnName = columnNameSplit[0];
        for (int i = 1; i < columnNameSplit.length; i++) {
//            sql命名规范 -> java命名规范
            columnName = columnName.concat(Character.toString(columnNameSplit[i].charAt(0)).toUpperCase()).
                    concat(columnNameSplit[i].substring(1));
        }
//        方法名 -> 属性名
        String substring = methodName.substring(4);
        String fieldName = Character.toString(methodName.charAt(3)).toLowerCase().concat(substring);
        return fieldName.equals(columnName);
    }
}
