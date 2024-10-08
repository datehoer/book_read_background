package com.datehoer.bookapi.model;

import lombok.Data;

@Data
public class BookStatistics {
    private int totalBooks;
    private int likedBooks;
    private int totalPlatforms;
    private int completedBooks;
}
