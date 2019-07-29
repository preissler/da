package com.da.persistance.helper;




import com.da.common.model.json.ProductJSON;


import com.da.persistance.model.db.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductHelper {
    public Product copyValues(Product product, ProductJSON origin) throws Exception{
        if(product == null){
            //exception
        }
        if (origin == null ){
            //exception
        }
        return product;
    }
}
