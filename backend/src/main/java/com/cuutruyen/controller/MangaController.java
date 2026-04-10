package com.cuutruyen.controller;

import com.cuutruyen.entity.Series;
import com.cuutruyen.service.MangaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/manga")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MangaController {
    private final MangaService mangaService;

    @GetMapping("/latest")
    public ResponseEntity<List<Series>> getLatest(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(mangaService.getLatestManga(limit));
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Series>> getTopRated(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(mangaService.getTopRatedManga(limit));
    }

    @GetMapping
    public ResponseEntity<Page<Series>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(mangaService.getAllManga(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Series> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(mangaService.getMangaById(id));
    }

    @PostMapping
    public ResponseEntity<Series> create(
            @RequestParam("title") String title,
            @RequestParam("alternativeTitle") String alternativeTitle,
            @RequestParam("description") String description,
            @RequestParam("seriesType") String seriesType,
            @RequestParam(value = "cover", required = false) MultipartFile coverFile) {
        return ResponseEntity
                .ok(mangaService.createSeriesWithCover(title, alternativeTitle, description, seriesType, coverFile));
    }
}
