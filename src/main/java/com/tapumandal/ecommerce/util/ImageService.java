package com.tapumandal.ecommerce.util;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.entity.ImageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

     String FOLDER_PATH = "";

    @Value("${app.upload.dir:${user.home}}")
     String appFolderPath = "";

    public List<ImageModel> store(String  folderPath, MultipartFile[] images){
        List<ImageModel> imageModels = new ArrayList<>();

        for (MultipartFile file: images) {
            ImageModel tmp = this.store(folderPath, file);
            if (tmp != null){
                imageModels.add(tmp);
            }
        }
        return imageModels;
    }
    public ImageModel store(String  folderPath, MultipartFile image){



//        this.FOLDER_PATH = folderPath;
        this.FOLDER_PATH = appFolderPath+"/public/images/product/";
        try {
            return storeTheFile(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ImageModel storeTheFile(MultipartFile file) throws IOException {

        ImageModel imageModel = new ImageModel();

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();

            String extension = getExtension(file);
            String newFileName = String.valueOf(Instant.now().getEpochSecond())+"."+extension;

            File serverFile = new File(this.FOLDER_PATH + newFileName);

            if(!serverFile.getParentFile().exists()){
                serverFile.getParentFile().mkdirs();
            }

            if (!serverFile.exists()) {
                serverFile.createNewFile();
            }

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(this.FOLDER_PATH + "/")
                    .path(newFileName)
                    .toUriString();

            imageModel.setName(newFileName);
            imageModel.setUrl(fileDownloadUri);
            imageModel.setSize(file.getSize());

            System.out.println("XXXXXXXXXXXXXX");
            System.out.println(new Gson().toJson(imageModel));

            return imageModel;
        }

        return null;
    }

    private String getExtension(MultipartFile file) {
        int i = file.getOriginalFilename().lastIndexOf('.');
        if (i > 0) {
            return file.getOriginalFilename().substring(i+1);
        }
        return "";
    }
}
