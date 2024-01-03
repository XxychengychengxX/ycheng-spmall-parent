package com.project.ychengspmall.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.project.ychengspmall.manager.properties.MinioProperties;
import com.project.ychengspmall.manager.service.FileUploadService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private MinioProperties minioProperties;

    @Override
    public String fileUpload(MultipartFile multipartFile) {
        try {
            // 创建一个Minio的客户端对象
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioProperties.getEndpointUrl())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                    .build();

            // 判断桶是否存在
            boolean found = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {
                // 如果不存在，那么此时就创建一个新的桶
                log.warn("Bucket ’{}‘ not exists , will create one ",
                        minioProperties.getBucketName());
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {  // 如果存在打印信息
                log.warn("Bucket '{}' already exists.", minioProperties.getBucketName());
            }

            // 设置存储对象名称
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replace("-", "");
            //20230801/443e1e772bef482c95be28704bec58a901.jpg
            String fileName = dateDir + "/" + uuid + multipartFile.getOriginalFilename();
            System.out.println(fileName);

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .object(fileName)
                    .build();
            minioClient.putObject(putObjectArgs);
            String filePath = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + "/" + fileName;
            log.info("The Final filePath is: \n {}", filePath);
            return filePath;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
