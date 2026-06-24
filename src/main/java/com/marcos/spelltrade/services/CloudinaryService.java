package com.marcos.spelltrade.services;

import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.marcos.spelltrade.dto.CloudinaryUploadResultDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryUploadResultDto upload(MultipartFile file) {
        try {
            Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.emptyMap()
            );

            return new CloudinaryUploadResultDto(
                result.get("secure_url").toString(),
                result.get("public_id").toString()
            );

        } catch(IOException exception) {
            throw new RuntimeException("Error when uploading");
        }
    }
}
