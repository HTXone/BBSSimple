package com.fc.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Upload {

    public static String fileUpload(MultipartFile file, String picPath) throws IOException {
//        System.out.println("tttt");
//        HashMap<String,String > map = new HashMap<>();
        if(file==null){
            return null;
        }

        String orignalName = file.getOriginalFilename();

        if(file==null || orignalName==null || orignalName.length()==0){
            return null;
        }

        String suffix = orignalName.substring(orignalName.lastIndexOf(".")+1);
        String newSuffix="";
        boolean r=false;
        String[] legal={"jpg","png","gif","bmp"};
        System.out.println(suffix);
        for (String w:legal
        ) {
            if(w.equalsIgnoreCase(suffix)){
                r=true;
                newSuffix=w;
            }
        }

        if(!r){
            return null;
        }


//        String picPath=request.getServletContext().getRealPath("/")+"upload\\";

        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYYMMdd");
        String dir=picPath+dateFormat.format(date);

        File newFileDir = new File(dir);

        if(!newFileDir.exists()){
            newFileDir.mkdirs();
        }

        String newfileName=picPath+dateFormat.format(date)+"/"+ System.currentTimeMillis()+"."+newSuffix;
        File newFile = new File(newfileName);

        file.transferTo(newFile);

        return "/upload/" +dateFormat.format(date)+'/'+newFile.getName();
    }
}
