package com.m4coding.mallforeground;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallforeground.dto.HomeCommonItemResult;
import com.m4coding.mallforeground.dto.HomeProductListQueryParam;
import com.m4coding.mallforeground.dto.childitem.HomeProductCardChildItem;
import com.m4coding.mallmbg.mbg.mapper.*;
import com.m4coding.mallmbg.mbg.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MallForegroundApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HomeProductServiceTest {

    @Autowired
    private PmsSpuMapper pmsSpuMapper;
    @Autowired
    private PmsSkuMapper pmsSkuMapper;
    @Autowired
    private PmsSpuSkuAttrMapper pmsSpuSkuAttrMapper;

    @Test
    public void main() {

        HomeProductListQueryParam homeProductListQueryParam = new HomeProductListQueryParam();
        homeProductListQueryParam.setKeyword("");
        homeProductListQueryParam.setPageNum(1);
        homeProductListQueryParam.setPageSize(3);
        pageListInfo(homeProductListQueryParam);
    }


    private CommonPage<HomeCommonItemResult> pageListInfo(HomeProductListQueryParam homeProductListQueryParam) {
        Page page = new Page(homeProductListQueryParam.getPageNum(), homeProductListQueryParam.getPageSize()); //创建一个默认的page

        List<HomeCommonItemResult> itemList = new ArrayList<>();

        PmsSpuExample pmsSpuExample = new PmsSpuExample();
        PmsSpuExample.Criteria criteria = pmsSpuExample.createCriteria();

        String keyWord = StrUtil.isEmpty(homeProductListQueryParam.getKeyword()) ? "" : homeProductListQueryParam.getKeyword();
        criteria.andProductNameLike("%" + keyWord + "%");
        List<PmsSpu> spuList = pmsSpuMapper.selectByExampleWithBLOBs(pmsSpuExample);
        for (PmsSpu pmsSpu : spuList) {
            PmsSkuExample pmsSkuExample = new PmsSkuExample();
            pmsSkuExample.createCriteria().andSpuIdEqualTo(pmsSpu.getProductId());
            page = PageHelper.startPage(homeProductListQueryParam.getPageNum(), homeProductListQueryParam.getPageSize()); //对sku进行分页
            List<PmsSku> skuList = pmsSkuMapper.selectByExampleWithBLOBs(pmsSkuExample);
            for (PmsSku pmsSku : skuList) {

                HomeProductCardChildItem.Child productCardChildItem = new HomeProductCardChildItem.Child();

                //市场价、销售价
                productCardChildItem.setProductOrgPrice(pmsSku.getMarketPrice() + "");
                productCardChildItem.setProductPrice(pmsSku.getSalePrice() + "");

                //商品图
                List<String> urlList = StrUtil.splitTrim(pmsSku.getBannerUrl(), ",");
                if (!CollectionUtil.isEmpty(urlList)) {
                    productCardChildItem.setImageUrl(urlList.get(0));
                }

                //商品名称
                StringBuilder productNameBuilder = new StringBuilder(pmsSpu.getProductName());
                PmsSpuSkuAttrExample pmsSpuSkuAttrExample = new PmsSpuSkuAttrExample();
                pmsSpuSkuAttrExample.createCriteria().andSkuIdEqualTo(pmsSku.getId());
                List<PmsSpuSkuAttr> pmsSpuSkuAttrList = pmsSpuSkuAttrMapper.selectByExample(pmsSpuSkuAttrExample);
                if (!CollectionUtil.isEmpty(pmsSpuSkuAttrList)) {
                    for (PmsSpuSkuAttr pmsSpuSkuAttr : pmsSpuSkuAttrList) {
                        productNameBuilder.append(" ")
                                .append(pmsSpuSkuAttr.getAttrValueName());
                    }
                }
                productCardChildItem.setProductName(productNameBuilder.toString());

                //商品id
                productCardChildItem.setProductSkuId(pmsSku.getId());
                //spu Id
                productCardChildItem.setProductSpuId(pmsSpu.getProductId());

                //加入list
                HomeCommonItemResult<HomeProductCardChildItem> commonItemResult = HomeProductCardChildItem.createItem();
                HomeProductCardChildItem body = commonItemResult.getBody();
                if (CollectionUtil.isEmpty(body.getItems())) {
                    body.setItems(new ArrayList<>());
                }
                body.getItems().add(productCardChildItem);

                itemList.add(commonItemResult);
            }
        }

        return CommonPage.restPage(page, itemList);
    }
}
