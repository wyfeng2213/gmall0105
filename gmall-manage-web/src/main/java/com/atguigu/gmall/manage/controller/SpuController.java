package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.bean.PmsProductSaleAttr;
import com.atguigu.gmall.bean.PmsProductSaleAttrValue;
import com.atguigu.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping(value = "saveSpuInfo",method = RequestMethod.POST)
    @ResponseBody
    public String saveSpuInfo(PmsProductInfo spuInfo){
//        spuService.saveSpuInfo(spuInfo);
        return  "success";
    }

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String catalog3Id){
        List<PmsProductInfo> PmsProductInfo = spuService.spuList(catalog3Id);
        return PmsProductInfo;
    }

    @RequestMapping("spuSaleAttr")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttr(String productId){
        List<PmsProductSaleAttr> PmsProductSaleAttr = spuService.spuSaleAttr(productId);
        return PmsProductSaleAttr;
    }

//    @RequestMapping("spuSaleAttrValue")
//    @ResponseBody
//    public List<PmsProductSaleAttrValue> spuSaleAttrValue(PmsProductSaleAttr pmsProductSaleAttr){
//        List<PmsProductSaleAttrValue> PmsProductSaleAttrValue = spuService.spuSaleAttrValue(pmsProductSaleAttr);
//        return PmsProductSaleAttrValue;
//    }
//    @RequestMapping(value ="spuImageList" ,method = RequestMethod.GET)
//    @ResponseBody
//    public  List<SpuImage> getSpuImageList(@RequestParam Map<String,String> map){
//        String spuId = map.get("spuId");
//        List<SpuImage> spuImageList = spuManageService.getSpuImageList(spuId);
//        return spuImageList;
//    }
//
//    @RequestMapping("spuSaleAttrList")
//    @ResponseBody
//    public List<SpuSaleAttr> getSpuSaleAttrList(HttpServletRequest httpServletRequest){
//        String spuId = httpServletRequest.getParameter("spuId");
//        List<SpuSaleAttr> spuSaleAttrList = spuManageService.getSpuSaleAttrList(spuId);
//
//        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
//            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
//            Map map=new HashMap();
//            map.put("total",spuSaleAttrValueList.size());
//            map.put("rows",spuSaleAttrValueList);
//            // String spuSaleAttrValueJson = JSON.toJSONString(map);
//            spuSaleAttr.setSpuSaleAttrValueJson(map);
//        }
//
//
//        return spuSaleAttrList;
//
//    }
//
//    @RequestMapping("spuSaleAttrValueList")
//    @ResponseBody
//    public List<SpuSaleAttrValue> getSpuSaleAttrValueList(HttpServletRequest httpServletRequest){
//        String spuId = httpServletRequest.getParameter("spuId");
//        String saleAttrId = httpServletRequest.getParameter("saleAttrId");
//        SpuSaleAttrValue spuSaleAttrValue=new SpuSaleAttrValue();
//        spuSaleAttrValue.setSpuId(spuId);
//        spuSaleAttrValue.setSaleAttrId(saleAttrId);
//        List<SpuSaleAttrValue> spuSaleAttrValueList = spuManageService.getSpuSaleAttrValueList(spuSaleAttrValue);
//        return spuSaleAttrValueList;
//
//    }




}
