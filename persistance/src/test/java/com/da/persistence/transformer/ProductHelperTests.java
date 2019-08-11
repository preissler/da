package com.da.persistence.transformer;

import com.da.common.model.json.ProductJSON;
import com.da.persistence.common.model.db.Product;
import com.da.persistence.exception.ErrorCopyingValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductHelperTests {
    @Autowired
    ProductHelper productHelper;

    @Test(expected = ErrorCopyingValues.class)
    public void testCopyValuesErrorJSONNull() throws ErrorCopyingValues{
        productHelper.copyValues(new Product(), null );
    }

    @Test(expected = ErrorCopyingValues.class)
    public void testCopyValuesErrorProductNull() throws ErrorCopyingValues{
        productHelper.copyValues(null, new ProductJSON());
    }
}
