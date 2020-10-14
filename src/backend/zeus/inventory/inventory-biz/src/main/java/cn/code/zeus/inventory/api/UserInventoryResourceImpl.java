package cn.code.zeus.inventory.api;

import cn.code.zeus.common.api.pojo.Result;
import cn.code.zeus.inventory.request.InventoryUpdateRequest;
import cn.code.zeus.inventory.request.RequestQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserInventoryResourceImpl implements UserInventoryResource{

    Logger logger = LoggerFactory.getLogger(UserInventoryResourceImpl.class);
    @Resource
    private RequestQueue queue ;

    @Override
    public Result<Integer> getProductInventory(String productId) throws InterruptedException {
        queue.put(new InventoryUpdateRequest());
        return new Result(110);
    }

    @Override
    public Result<Integer> updateProductInventory(String productId, Integer changeSum, String inventoryInfo) {
        return null;
    }
}
