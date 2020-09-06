package com.jinxi.star.jxserver.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinxi.star.jxserver.dao.BulletinDao;
import com.jinxi.star.jxserver.entity.Bulletin;
import com.jinxi.star.jxserver.model.common.ApiRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulletinService {
    @Autowired
    private BulletinDao bulletinDao;

    public Page<Bulletin> listAllBulletins(ApiRequest request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        return bulletinDao.listAllBulletins();
    }

    public Bulletin getBulletinContent(String bulletinId) {

        Bulletin bulletin = bulletinDao.getBulletinContent(bulletinId);
        String img = bulletin.getImg();
        String[] img1 = img.split("\\|");
        int l =img1.length;
        int[] imgId = new int[l];
        for(int i=0; i<l; i++){
            imgId[i] = Integer.parseInt(img1[i]);
        }
        bulletin.setImgId(imgId);
        return bulletin;
    }

   public void releaseBulletin(String bulletinName,String communityName,int[] imgId){
       Bulletin bulletin = new Bulletin();
       bulletin.setBulletinName(bulletinName);
       bulletin.setCommunityName(communityName);
       int k = imgId.length;
       String img = "";
       for(int i = 0; i < k; i++ ){
           img = img + imgId[i] + "|";
       }
       bulletin.setImg(img);
       bulletinDao.releaseBulletin(bulletin);
    }

    public void deleteBulletin(String bulletinId){
        bulletinDao.deleteBulletin(bulletinId);
    }

}
