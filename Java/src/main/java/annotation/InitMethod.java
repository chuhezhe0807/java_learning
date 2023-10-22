package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: InitDemo
 * Package: com.chuhezhe.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/3 17:43
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InitMethod {

}
