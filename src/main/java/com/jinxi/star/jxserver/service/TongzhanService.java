package com.jinxi.star.jxserver.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinxi.star.jxserver.dao.TongzhanDao;
import com.jinxi.star.jxserver.entity.Tongzhan;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TongzhanService {
    @Autowired
    private TongzhanDao tongzhanDao;

    public Page<Tongzhan> listAllTongzhans(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return tongzhanDao.listAllTongzhans();
    }

    public Tongzhan getTongzhanContent(String tongzhanId) {

        Tongzhan tongzhan = tongzhanDao.getTongzhanContent(tongzhanId);
        String img = tongzhan.getImg();
        String[] img1 = img.split("\\|");
        int l =img1.length;
        int[] imgId = new int[l];
        for(int i=0; i<l; i++){
            imgId[i] = Integer.parseInt(img1[i]);
        }
        tongzhan.setImgId(imgId);
        return tongzhan;
    }

   public void releaseTongzhan(String tongzhanName,String communityId,int[] imgId){
       Tongzhan tongzhan = new Tongzhan();
       tongzhan.setTongZhanName(tongzhanName);
       tongzhan.setCommunityId(communityId);
       int k = imgId.length;
       String img = "";
       for(int i = 0; i < k; i++ ){
           img = img + imgId[i] + "|";
       }
       tongzhan.setImg(img);
       tongzhanDao.releaseTongzhan(tongzhan);
    }

    public void deleteTongzhan(String tongzhanId){
        tongzhanDao.deleteTongzhan(tongzhanId);
    }

}
