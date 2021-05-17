package com.nchu.ctrl;

import com.nchu.bean.Address;
import com.nchu.bean.Code;
import com.nchu.bean.Result;
import com.nchu.dao.ReceiveAddressDao;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private ReceiveAddressDao addressDao;
    @PostMapping("/add")
    public Result addAddress(@RequestBody Address address){
        if(address.getId()==null||address.getId().equals(""))
            addressDao.insert(address);
        else
            addressDao.updateById(address);
        return new Result("操作成功", Code.success.getCode());
    }
    @GetMapping("/{uid}")
    public Result<List> getAddress(@PathVariable String uid){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id",uid);
        List<Address> addresses = addressDao.selectByMap(map);
        return new Result<>(addresses,"查询成功",Code.success.getCode());
    }
    @DeleteMapping("/{id}")
    public Result delAddress(@PathVariable String id){
        addressDao.deleteById(id);
        return new Result<>("删除成功",Code.success.getCode());
    }
}
