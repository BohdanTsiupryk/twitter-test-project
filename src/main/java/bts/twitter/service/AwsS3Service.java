package bts.twitter.service;

import bts.twitter.config.AwsProperties;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static com.amazonaws.services.s3.model.GroupGrantee.AllUsers;

@Service
public class AwsS3Service {

    @Autowired
    private AwsProperties awsProperties;

    private AmazonS3 s3client;

    @PostConstruct
    public void init() {
        AWSCredentials credentials = new BasicAWSCredentials(awsProperties.getAccess(), awsProperties.getSecret());

        System.out.println(awsProperties.getSecret());
        System.out.println(awsProperties.getAccess());

        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        if (!s3client.doesBucketExist(awsProperties.getBucket())) {
            System.out.println("Bucket : " + awsProperties.getBucket() + " not exist! Creating it");
            s3client.createBucket(awsProperties.getBucket());

            AccessControlList bucketAcl = s3client.getBucketAcl(awsProperties.getBucket());
            bucketAcl.grantPermission(AllUsers, Permission.Read);
        }
    }

    public String saveFile(MultipartFile file) {

        String fileName = UUID.randomUUID() + "." + file.getOriginalFilename();

        s3client.listBuckets();

        File convertedFile = convertFile(file);

        PutObjectRequest request = new PutObjectRequest(
                awsProperties.getBucket(),
                fileName,
                convertedFile
        ).withCannedAcl(CannedAccessControlList.PublicRead);

//        s3client.putObject(request);

        convertedFile.delete();

        return awsProperties.getUrl() + fileName;
    }

    public void deleteFile(String url) {
        String fileName = url.replace(awsProperties.getUrl(), "");

//        s3client.deleteObject(new DeleteObjectRequest(awsProperties.getBucket(), fileName));
    }

    private File convertFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(convFile);
            outputStream.write(file.getBytes());
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return convFile;
    }
}
