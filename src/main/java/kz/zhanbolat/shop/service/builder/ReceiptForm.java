package kz.zhanbolat.shop.service.builder;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface ReceiptForm {

    ReceiptType value();

    enum ReceiptType {
        JSON
    }
}
