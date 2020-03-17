package com.m4coding.mallmanager;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallmanager.dto.*;
import com.m4coding.mallmanager.service.impl.PmsProductServiceImpl;
import com.m4coding.mallmbg.mbg.mapper.JdProductItemsMapper;
import com.m4coding.mallmbg.mbg.mapper.PmsBrandMapper;
import com.m4coding.mallmbg.mbg.mapper.PmsCategoryMapper;
import com.m4coding.mallmbg.mbg.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallManagerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PmsProductServiceTest {
    @Autowired
    private PmsProductServiceImpl pmsProductService;
    @Autowired
    private JdProductItemsMapper jdProductItemsMapper;
    @Autowired
    private PmsBrandMapper pmsBrandMapper;
    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    @Test
    public void main() {
        JdProductItemsExample jdProductItemsExample = new JdProductItemsExample();
        JdProductItemsExample.Criteria criteria = jdProductItemsExample.createCriteria();
        criteria.andTitleLike("%");
        List<JdProductItems> list = jdProductItemsMapper.selectByExample(jdProductItemsExample);

        for (JdProductItems jdProductItems : list) {
            PmsProductQueryParam pmsProductQueryParam = new PmsProductQueryParam();
            pmsProductQueryParam.setKeyword(jdProductItems.getTitle());
            pmsProductQueryParam.setPageNum(1);
            pmsProductQueryParam.setPageSize(10);
            CommonPage<ListProductResult> commonPage =  pmsProductService.getProductList(pmsProductQueryParam);

            //图片
            String imgUrl = jdProductItems.getImg();
            if (!imgUrl.startsWith("https:")) {
                imgUrl = "https:" + imgUrl;
            }
            List<String> bannerList = new ArrayList<>();
            bannerList.add(imgUrl);
            List<String> mainPicList = new ArrayList<>();
            mainPicList.add(imgUrl);

            //存在，则不添加了
            if (!CollectionUtil.isEmpty(commonPage.getList())) {
                for (ListProductResult listProductResult : commonPage.getList()) {
                    PmsProductUpdateParam pmsProductUpdateParam = new PmsProductUpdateParam();
                    pmsProductUpdateParam.setProductId(listProductResult.getProductSpuId());
                    pmsProductUpdateParam.setSkuId(listProductResult.getProductSkuId());
                    pmsProductUpdateParam.setMarketPrice(jdProductItems.getPrice());
                    pmsProductUpdateParam.setSalePrice(jdProductItems.getPrice());
                    pmsProductUpdateParam.setMainPicList(mainPicList);
                    pmsProductUpdateParam.setBannerPicList(bannerList);
                    pmsProductService.update(pmsProductUpdateParam);
                }
                continue;
            }

            PmsProductParam pmsProductParam = new PmsProductParam();
            pmsProductParam.setSalePrice(jdProductItems.getPrice());
            pmsProductParam.setMarketPrice(jdProductItems.getPrice());
            pmsProductParam.setProductName(jdProductItems.getTitle());

            pmsProductParam.setBannerPicList(bannerList);
            pmsProductParam.setMainPicList(mainPicList);

            pmsProductParam.setStock(1000L); //库存
            //分类
            Long cateGoryId = -1L;
            if (jdProductItems.getKeyword().contains("女装") || jdProductItems.getTitle().contains("女装")) {
                cateGoryId = getCategoryId("女装");
            } else if (jdProductItems.getKeyword().contains("男装") || jdProductItems.getTitle().contains("男装")) {
                cateGoryId = getCategoryId("男装");
            } else if (jdProductItems.getKeyword().contains("手机") || jdProductItems.getTitle().contains("手机")) {
                cateGoryId = getCategoryId("手机");
            } else if (jdProductItems.getKeyword().contains("家电") || jdProductItems.getTitle().contains("家电")) {
                cateGoryId = getCategoryId("家电");
            } else if (jdProductItems.getKeyword().contains("医药") || jdProductItems.getTitle().contains("医药")) {
                cateGoryId = getCategoryId("医药");
            }
            if (cateGoryId == -1) {
                continue;
            }
            pmsProductParam.setCategoryId(cateGoryId);

            //品牌
            Long brandId = -1L;
            if (jdProductItems.getTitle().contains("以纯")) {
                brandId = getBandId("以纯");
            } else if (jdProductItems.getTitle().contains("小米")) {
                brandId = getBandId("小米");
            } else if (jdProductItems.getTitle().contains("华为")) {
                brandId = getBandId("华为");
            } else if (jdProductItems.getTitle().contains("oppo")) {
                brandId = getBandId("oppo");
            } else if (jdProductItems.getTitle().contains("vivo")) {
                brandId = getBandId("vivo");
            }
//            if (brandId == -1) {
//                continue;
//            }
            pmsProductParam.setBrandId(brandId); //品牌

            try {
                pmsProductService.create(pmsProductParam);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private Long getCategoryId(String keyword) {
        PmsProductCategoryQueryParam pmsProductCategoryQueryParam = new PmsProductCategoryQueryParam();
        pmsProductCategoryQueryParam.setKeyword(keyword);
        pmsProductCategoryQueryParam.setPageNum(1);
        pmsProductCategoryQueryParam.setPageSize(10);
        pmsProductCategoryQueryParam.setIsRootCategory(false);

        List<ListProductCategoryResult> resultList = new ArrayList<>();

        PmsCategoryExample pmsCategoryExample = new PmsCategoryExample();
        PmsCategoryExample.Criteria criteria = pmsCategoryExample.createCriteria();

        //名称模糊搜索
        String keyWord = StrUtil.isEmpty(pmsProductCategoryQueryParam.getKeyword()) ? "" : pmsProductCategoryQueryParam.getKeyword();
        criteria.andCategoryNameLike("%" + keyWord + "%");
        if (pmsProductCategoryQueryParam.getIsRootCategory()) {
            criteria.andPidEqualTo(0L);
        }
        Page page = PageHelper.startPage(pmsProductCategoryQueryParam.getPageNum(), pmsProductCategoryQueryParam.getPageSize());
        List<PmsCategory> categoryList = pmsCategoryMapper.selectByExample(pmsCategoryExample);
        for (PmsCategory pmsCategory : categoryList) {
            //加入list
            resultList.add(getChildCategory(pmsCategory));
        }

        if (!CollectionUtil.isEmpty(resultList)) {
            return resultList.get(0).getCategoryId();
        } else {
            return (long) -1;
        }
    }

    private ListProductCategoryResult getChildCategory(PmsCategory pmsCategory) {
        ListProductCategoryResult listProductCategoryResult = new ListProductCategoryResult();
        listProductCategoryResult.setName(pmsCategory.getCategoryName());
        listProductCategoryResult.setCategoryId(pmsCategory.getId());

        List<String> childIdList = StrUtil.splitTrim(pmsCategory.getChildId(), ",");
        if (!CollectionUtil.isEmpty(childIdList)) {
            List<Long> childIdLongList = new ArrayList<>();
            for (String childIdStr : childIdList) {
                childIdLongList.add(Long.parseLong(childIdStr));
            }
            PmsCategoryExample pmsCategoryExample = new PmsCategoryExample();
            PmsCategoryExample.Criteria criteria = pmsCategoryExample.createCriteria();
            criteria.andIdIn(childIdLongList);

            List<PmsCategory> list = pmsCategoryMapper.selectByExample(pmsCategoryExample);
            if (!CollectionUtil.isEmpty(list)) {
                listProductCategoryResult.setChildCategoryList(new ArrayList<>());
                for (PmsCategory pmsCategory1 : list) {
                    ListProductCategoryResult temp = getChildCategory(pmsCategory1);
                    listProductCategoryResult.getChildCategoryList().add(temp);
                }
            }
        }

        return listProductCategoryResult;
    }

    private Long getBandId(String keyword) {
        List<ListProductCategoryResult> resultList = new ArrayList<>();

        PmsBrandExample pmsBrandExample = new PmsBrandExample();
        PmsBrandExample.Criteria criteria = pmsBrandExample.createCriteria();

        //名称模糊搜索
        String keyWord = StrUtil.isEmpty(keyword) ? "" : keyword;
        criteria.andBrandNameLike("%" + keyWord + "%");
        List<PmsBrand> brandList = pmsBrandMapper.selectByExample(pmsBrandExample);

        if (!CollectionUtil.isEmpty(brandList)) {
            return brandList.get(0).getId();
        } else {
            return (long) -1;
        }
    }
}
