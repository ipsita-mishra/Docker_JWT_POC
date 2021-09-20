package com.learning.zoro.concepts.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
public class FileInfo {
    private String name;
    private String url;
    public FileInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
