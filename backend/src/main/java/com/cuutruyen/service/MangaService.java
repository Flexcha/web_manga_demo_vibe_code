package com.cuutruyen.service;

import com.cuutruyen.entity.Series;
import com.cuutruyen.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MangaService {
    private final SeriesRepository seriesRepository;
    private static final String UPLOAD_DIR = "uploads";

    public List<Series> getLatestManga(int limit) {
        return seriesRepository.findAll(PageRequest.of(0, limit, Sort.by("createdAt").descending())).getContent();
    }

    public List<Series> getTopRatedManga(int limit) {
        return seriesRepository.findAll(PageRequest.of(0, limit, Sort.by("averageRating").descending())).getContent();
    }

    public Page<Series> getAllManga(int page, int size) {
        return seriesRepository.findAll(PageRequest.of(page, size));
    }

    public Series getMangaById(Integer id) {
        return seriesRepository.findById(id).orElseThrow(() -> new RuntimeException("Manga not found"));
    }

    public Series createSeries(Series series) {
        series.setCreatedAt(LocalDateTime.now());
        series.setUpdatedAt(LocalDateTime.now());
        return seriesRepository.save(series);
    }

    public Series createSeriesWithCover(String title, String alternativeTitle, String description, String seriesType,
            MultipartFile coverFile) {
        Series series = new Series();
        series.setTitle(title);
        series.setAlternativeTitle(alternativeTitle);
        series.setDescription(description);
        series.setSeriesType(Series.SeriesType.valueOf(seriesType));
        series.setCreatedAt(LocalDateTime.now());
        series.setUpdatedAt(LocalDateTime.now());

        // Handle cover image upload
        if (coverFile != null && !coverFile.isEmpty()) {
            try {
                String coverUrl = uploadCoverImage(coverFile);
                series.setCoverUrl(coverUrl);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload cover image: " + e.getMessage(), e);
            }
        }

        return seriesRepository.save(series);
    }

    private String uploadCoverImage(MultipartFile file) throws IOException {
        // Create uploads directory if it doesn't exist
        File uploadsDir = new File(UPLOAD_DIR);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdirs();
        }

        // Generate unique filename
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID() + fileExtension;

        // Save file
        Path filePath = Paths.get(UPLOAD_DIR, "covers", uniqueFileName);
        Files.createDirectories(filePath.getParent());
        file.transferTo(filePath.toFile());

        // Return relative path for database
        return "/uploads/covers/" + uniqueFileName;
    }
}
