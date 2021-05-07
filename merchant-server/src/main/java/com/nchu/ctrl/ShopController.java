package com.nchu.ctrl;

import com.nchu.bean.Code;
import com.nchu.bean.Group;
import com.nchu.bean.Result;
import com.nchu.bean.Store;
import com.nchu.dao.GroupDao;
import com.nchu.dao.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("/store")
@RestController
public class ShopController {
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private GroupDao groupDao;
    /**
     * 增加店铺
     * @param store
     * @return
     */
    @PostMapping("/stores")
    public Result saveStore(Store store){
        storeDao.insert(store);
        return new Result("新增成功", Code.success.getCode());
    }
}
