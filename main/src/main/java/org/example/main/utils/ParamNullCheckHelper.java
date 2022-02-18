package org.example.main.utils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 空参检测工具类，参考：https://blog.csdn.net/qq920581171/article/details/102685694
 * 用法参考：
 *      try {
 *          ParamNullCheckHelper.check(person::getName, person::getAge);
 *      } catch (Exception e) {
 *          e.printStackTrace();
 *      }
 */
public class ParamNullCheckHelper {

    public static void check(GetMethod... methods) throws Exception {
        List<String> list = Arrays.stream(methods)
                .filter(item -> item.get() == null)
                .map(item -> item.getMethodName().substring(3, 4).toLowerCase() + item.getMethodName().substring(4))
                .collect(Collectors.toList());
        if (!list.isEmpty())
            throw new Exception("缺少参数：" + list);
    }

    @FunctionalInterface
    public interface GetMethod extends Serializable {

        Object get();

        default SerializedLambda getSerializedLambda() throws Exception {
            Method method = this.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            return (SerializedLambda) method.invoke(this);
        }

        default String getClassName() {
            try {
                return getSerializedLambda().getImplClass();
            } catch (Exception e) {
                return null;
            }
        }

        default String getMethodName() {
            try {
                return getSerializedLambda().getImplMethodName();
            } catch (Exception e) {
                return null;
            }
        }

    }

}
