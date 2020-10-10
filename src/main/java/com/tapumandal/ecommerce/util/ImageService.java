package com.tapumandal.ecommerce.util;

import com.google.gson.Gson;
import com.tapumandal.ecommerce.entity.ImageModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

        this.FOLDER_PATH = folderPath;
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
            String timeStamp = String.valueOf(Instant.now().getEpochSecond());
            byte[] bytes = file.getBytes();

            String extension = "";
            int i = file.getOriginalFilename().lastIndexOf('.');
            if (i > 0) {
                extension = file.getOriginalFilename().substring(i+1);
            }


//            Path path = Paths.get(this.FOLDER_PATH + "/" + timeStamp+"."+extension);
//            Files.write(path, bytes);

            System.out.println("XXXXXXXXXX");
            System.out.println(this.FOLDER_PATH + "/" + timeStamp+"."+extension);
//            File serverFile = new File(String.valueOf(path));
            File serverFile = new File(this.FOLDER_PATH + "/" + timeStamp+"."+extension);
//            System.out.println(serverFile.);
            if(!serverFile.getParentFile().exists()){
                System.out.println("FOLDER not existed"+serverFile.getAbsolutePath());
                System.out.println("FOLDER not existed"+serverFile.getCanonicalPath());
                serverFile.getParentFile().mkdirs();
            }

            if (!serverFile.exists()) {
                serverFile.createNewFile();
            }

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();


            imageModel.setName(timeStamp+"."+extension);
            imageModel.setUrl(this.FOLDER_PATH + "/" + timeStamp+"."+extension);
            imageModel.setSize(file.getSize());

            System.out.println(new Gson().toJson(imageModel));

            return imageModel;
        }
        return null;
    }
}
