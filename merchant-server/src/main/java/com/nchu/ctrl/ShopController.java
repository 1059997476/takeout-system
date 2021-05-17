package com.nchu.ctrl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nchu.bean.Code;
import com.nchu.bean.Result;
import com.nchu.bean.Store;
import com.nchu.service.impl.ShopServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/store")
@RestController
public class ShopController {
    @Resource
    private ShopServiceImpl shopService;
    /**
     * 增加店铺
     * @param store
     * @return
     */
    @PostMapping("/addStore")
    public Result saveStore(@RequestBody Store store, @RequestHeader("username")String username) throws IOException {
        store.setMerchantId(username);
        return shopService.insertStore(store);
    }

    /**
     * 删除店铺
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteStore(@PathVariable String id) throws IOException {
        return shopService.deleteStore(id);
    }

    /**
     * 根据商家Id查询店铺
     * @param merchant_id
     * @return
     */
    @GetMapping("/findByMerchant")
    public Result findStoresByMerchantId(@RequestHeader("username")String merchant_id){
        Map<String,Object> map = new HashMap<>();
        map.put("merchant_id",merchant_id);
        List<Store> stores = shopService.listByMap(map);
        return new Result<>(stores,"查询成功",Code.success.getCode());
    }
    @GetMapping("/list")
    public Result findStores(Page page){
        return shopService.findStores(page);
    }
}
