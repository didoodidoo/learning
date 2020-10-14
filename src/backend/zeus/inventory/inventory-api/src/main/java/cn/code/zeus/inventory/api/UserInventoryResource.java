package cn.code.zeus.inventory.api;

import cn.code.zeus.common.api.pojo.Result;
import feign.Headers;
import org.springframework.web.bind.annotation.*;

@Headers(value = {"Content-Type: application/json", "Accept: application/json"})
@RequestMapping("/user/inventory")
public interface UserInventoryResource {



    @GetMapping("/{productId}")
    Result<Integer> getProductInventory(
            @PathVariable("productId")
                    String productId
    ) throws InterruptedException;


    @PostMapping("/{productId}")
    Result<Integer> updateProductInventory(
            @PathVariable("productId")
                    String productId,
            @RequestParam("changeSum")
                    Integer changeSum,
            @RequestBody
                    String inventoryInfo
    );

}
