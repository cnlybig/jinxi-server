package com.jinxi.star.jxserver.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinxi.star.jxserver.dao.MinorityDao;
import com.jinxi.star.jxserver.entity.Minority;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinorityService {
    @Autowired
    private MinorityDao minorityDao;

    public Page<Minority> listAllMinoritys(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return minorityDao.listAllMinoritys();
    }

    public Minority getMinorityContent(String minorityId) {

        Minority minority = minorityDao.getMinorityContent(minorityId);
        String img = minority.getImg();
        String[] img1 = img.split("\\|");
        int l =img1.length;
        int[] imgId = new int[l];
        for(int i=0; i<l; i++){
            imgId[i] = Integer.parseInt(img1[i]);
        }
        minority.setImgId(imgId);
        return minority;
    }

   public void releaseMinority(String minorityName,String communityId,int[] imgId){
       Minority minority = new Minority();
       minority.setMinorityName(minorityName);
       minority.setCommunityId(communityId);
       int k = imgId.length;
       String img = "";
       for(int i = 0; i < k; i++ ){
           img = img + imgId[i] + "|";
       }
       minority.setImg(img);
       minorityDao.releaseMinority(minority);
    }

    public void deleteMinority(String minorityId){
        minorityDao.deleteMinority(minorityId);
    }

}
